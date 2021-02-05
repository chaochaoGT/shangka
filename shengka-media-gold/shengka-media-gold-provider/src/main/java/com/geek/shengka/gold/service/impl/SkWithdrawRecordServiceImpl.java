package com.geek.shengka.gold.service.impl;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.util.JsonTools;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.entity.SkWithdrawRecord;
import com.geek.shengka.gold.mapper.SkWithdrawRecordDAO;
import com.geek.shengka.gold.proxy.AccountingCenterProxy;
import com.geek.shengka.gold.request.TradeAccountRequest;
import com.geek.shengka.gold.request.WithdrawRequest;
import com.geek.shengka.gold.response.UserAccountParam;
import com.geek.shengka.gold.response.WithdrawResponse;
import com.geek.shengka.gold.service.SkWithdrawRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 提现
 *
 * @author: xuxuelei
 * @create: 2019-08-02 15:07
 **/
@Service
public class SkWithdrawRecordServiceImpl implements SkWithdrawRecordService {
    private final static Logger logger = LoggerFactory.getLogger(SkWithdrawRecordServiceImpl.class);
    
	@Autowired
	private AccountingCenterProxy accountingCenterProxy;
	@Autowired
	private SkWithdrawRecordDAO skWithdrawRecordDAO;
	
	/**
	 * 提现操作
	 */
	@Override
	public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
		WithdrawResponse withdrawResponse = new WithdrawResponse();
		TradeAccountRequest tradeAccountRequest = new TradeAccountRequest();
     	Long uid= UserContextHolder.getUserIdByHeader();
//     	Long uid= (long) 413453861;

    	
		if(uid <1L) {
			return new WithdrawResponse();
		}
 		
		tradeAccountRequest.setAccountType(200001);
		tradeAccountRequest.setUserId(uid);
		
		BaseResponse baseResponse= accountingCenterProxy.remoteSelectAccount(tradeAccountRequest);
		BigDecimal usableAmount = new BigDecimal(0);
		if(null!=baseResponse.getData()){
			String  datas = JsonTools.toJSONString(baseResponse.getData());
			UserAccountParam userAccountParam = JsonTools.parseObject(datas, UserAccountParam.class);
			 usableAmount = userAccountParam.getUsableAmount();
			 withdrawResponse.setTaxExemptionAmount(userAccountParam.getTaxExemptionAmount());
			 withdrawResponse.setIdCard(userAccountParam.getIdCard());
		}else {
			//此处要加判断逻辑
			//账户异常
			logger.error("user account not exists with userInfo["+JSON.toJSONString(tradeAccountRequest)+"]");
		}
 		
		List<SkWithdrawRecord> SkWithdrawRecordDb = skWithdrawRecordDAO.selectByHandleState();
		BigDecimal amountNum = new BigDecimal(1);
		if(null!=SkWithdrawRecordDb) {
		
			for(SkWithdrawRecord r:SkWithdrawRecordDb) {
				if(r.getUserId().longValue()==uid.longValue()) {
					BigDecimal  returnedMoney =r.getWithdrawAmount();
					usableAmount = usableAmount.subtract(returnedMoney);
				}
			}
		
		}
		
		//用户可提现金额
		BigDecimal useAmount = usableAmount;
		
		SkWithdrawRecord SkWithdrawRecord = new SkWithdrawRecord();
		BeanUtils.copyProperties(withdrawRequest, SkWithdrawRecord);
		SkWithdrawRecord.setOrderNo(String.valueOf( UUID.randomUUID().toString().replace("-", "")) );
		SkWithdrawRecord.setUserId(uid);
		SkWithdrawRecord.setWithdrawAccountType((byte) 1);
		int draw =  skWithdrawRecordDAO.insertSelective(SkWithdrawRecord);
		
		BeanUtils.copyProperties(withdrawRequest, withdrawResponse);
  
 		withdrawResponse.setAccountCode(baseResponse.getCode());
		withdrawResponse.setUserAmount(useAmount);
		withdrawResponse.setStatus(1);
		return withdrawResponse;
	}
     
 
 
     
}
