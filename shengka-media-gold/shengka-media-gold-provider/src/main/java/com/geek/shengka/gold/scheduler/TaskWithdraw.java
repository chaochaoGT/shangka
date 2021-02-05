package com.geek.shengka.gold.scheduler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.gold.entity.SkWithdrawRecord;
import com.geek.shengka.gold.mapper.SkWithdrawRecordDAO;
import com.geek.shengka.gold.proxy.AccountingCenterProxy;
import com.geek.shengka.gold.proxy.RemoteIoRecorder;
import com.geek.shengka.gold.request.SyncCoinRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaskWithdraw  extends RemoteIoRecorder implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(TaskWithdraw.class);

	// 当前正在执行job的uuid索引值
	private static Map<String, String> currentUUID = new ConcurrentHashMap<String,String>();


	@Autowired
	private AccountingCenterProxy accountingCenterProxy;
	@Autowired
	private SkWithdrawRecordDAO skWithdrawRecordDAO;

	
 
	
	// 提现
	public void handle() {

		// 限量500条
		List<SkWithdrawRecord> draws = skWithdrawRecordDAO.selectByHandleState();
		if (CollectionUtils.isEmpty(draws)) {
			logger.debug("there is not unchecked withdraw info...");
			return;
		}

		for (SkWithdrawRecord draw : draws) {
			// 当前job唯一码
			String scychronizeFlag = UUID.randomUUID().toString();
			// 当前正在执行job的uuid索引值
			currentUUID.put(scychronizeFlag, scychronizeFlag);

			try {

				// 抢占数据处理锁
				skWithdrawRecordDAO.attachDataLock(scychronizeFlag, draw.getId());
				if (skWithdrawRecordDAO.selectByDataLock(scychronizeFlag, draw.getId()) == null) {
					// 抢占锁失败，此擂台信息 在此job内不处理
					logger.debug("this withdraw record is in process in another job ...");
					continue;
				}

				SyncCoinRequest syncCoinRequest = new SyncCoinRequest();
				syncCoinRequest.setTradeTypeCode(1001);// 提现
				syncCoinRequest.setOrderNo(draw.getOrderNo());
				syncCoinRequest.setGoldCoin(draw.getWithdrawAmount());
				syncCoinRequest.setUserId(draw.getUserId());
				syncCoinRequest.setUserPayAccount(draw.getWithdrawAccount());
				syncCoinRequest.setUserPayName(draw.getWithdrawAccountName());
//					// DOTO加入账户中心余额校验
//					TradeAccountRequest tradeAccountRequest = new TradeAccountRequest();
//					tradeAccountRequest.setAccountType(100001);
//					BaseResponse baseResponseAccount = accountingCenterProxy.remoteSelectAccount(tradeAccountRequest);
//					String usableAmount = baseResponseAccount.getData().toString();

				/** serviceType 是 int 业务类型 1-浏览视频，2-擂台，3-礼包，4-夺宝，5-提现 **/
				syncCoinRequest.setExt("5");

			  
				 //通知账务
				if(! postAccountCenter(draw, syncCoinRequest)) {
					logger.debug("postAccountCenter withDarw failed!!");
				}
				
				
				skWithdrawRecordDAO.updateByOrderNo(draw);
				
				 
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				currentUUID.remove(scychronizeFlag);
				// 根据当前抢占锁，释放抢占锁
				skWithdrawRecordDAO.releaseDataLock(scychronizeFlag, draw.getId());
			}

		}
	}





	/**
	 * 调用账务中心
	 * @param draw
	 * @param syncCoinRequest
	 */
	private boolean postAccountCenter(SkWithdrawRecord draw, SyncCoinRequest syncCoinRequest) {
		BaseResponse baseResponseReapt = accountingCenterProxy.syncGoldCoinFlow(syncCoinRequest);

		//记录IO历史 
//		super.recordIoHistory(draw, GoldBizEnum.提现.getCode(), syncCoinRequest, baseResponseReapt, baseResponseReapt == null ? 999 : baseResponseReapt.getCode());
	
		String tradeCode2 = null;
		if (null != baseResponseReapt && null != baseResponseReapt.getData()) {
			tradeCode2 = baseResponseReapt.getData().toString();
		}else {
			logger.error("[account-center-error][tradeNo-return-null]");
			tradeCode2 = "";
		}
		
		int code2 = baseResponseReapt.getCode();
		if (super.hasFinished(code2)) {
			if(StringUtils.isNotBlank(tradeCode2)) {
				draw.setTradeNo(tradeCode2);
			}
			draw.setHandleState((byte) 1);
			draw.setHandleRemark(baseResponseReapt.getMsg());
			return true;
		}else {
			draw.setHandleState((byte) 2);
			draw.setHandleRemark(JSON.toJSONString(baseResponseReapt));
		}
		
		return false;
	}
	
	
	
	
	
	/**
	 * 注册钩子，程序启动时执行，等待所有线程执行完毕再退出程序
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					
					if(MapUtils.isNotEmpty(currentUUID)) {
						for(String key: currentUUID.keySet()) {
							// 根据当前 currentUUID 重置flag
							TaskWithdraw.this.skWithdrawRecordDAO.handleFinal(key);
						}
					}
					
					
					logger.info("######################################withdraw sheduler executed end!######################################");
				} catch (Exception e) {
					logger.error("######################################withdraw sheduler executed end error ######################################", e);
				}
			}
		});
		logger.info("######################################withdraw sheduler executed ExecutorHook register success!######################################");
		
	}
	
	
}
