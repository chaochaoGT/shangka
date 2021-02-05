package com.geek.shengka.gold.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.common.enums.TradeTypeEnum;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.entity.SkTaskConfig;
import com.geek.shengka.gold.entity.SkUserTaskRecord;
import com.geek.shengka.gold.entity.SkWithdrawConfig;
import com.geek.shengka.gold.mapper.SkTaskConfigDAO;
import com.geek.shengka.gold.mapper.SkUserTaskRecordDAO;
import com.geek.shengka.gold.mapper.SkWithdrawConfigDAO;
import com.geek.shengka.gold.proxy.AccountingCenterProxy;
import com.geek.shengka.gold.rabbitmq.UserTaskRecordRabbitmqSender;
import com.geek.shengka.gold.request.BindingIdentityRequest;
import com.geek.shengka.gold.request.UserTaskRecordMqRequest;
import com.geek.shengka.gold.request.UserTaskRecordNoticeMqRequest;
import com.geek.shengka.gold.request.UserTaskRecordRequest;
import com.geek.shengka.gold.response.UserAccountResponse;
import com.geek.shengka.gold.response.UserAccountVo;
import com.geek.shengka.gold.response.UserAccountWithDraw;
import com.geek.shengka.gold.service.SkUserAccountService;
import com.geek.shengka.gold.util.DateFormatUtil;
import com.netflix.discovery.converters.Auto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 提现
 *
 * @author: xuxuelei
 * @create: 2019-08-02 15:07
 **/
@Service
public class SkUserAccountServiceImpl implements SkUserAccountService {
    private final static Logger logger = LoggerFactory.getLogger(SkUserAccountServiceImpl.class); 
    

    @Autowired
    private AccountingCenterProxy accountingCenterProxy;
    @Autowired
    private SkWithdrawConfigDAO skWithdrawConfigDAO;
    @Autowired
    private SkUserTaskRecordDAO skUserTaskRecordDAO;
    
    @Autowired
    private SkTaskConfigDAO skTaskConfigDAO;
    
    @Autowired
    private UserTaskRecordRabbitmqSender userTaskRecordRabbitmqSender;
    
	@Override
	public void getGoldDailyTask(UserTaskRecordRequest param) {
		SkTaskConfig skTaskConfig= skTaskConfigDAO.selectByPrimaryKey(param.getTaskRecordId());
		SkUserTaskRecord skUserTaskRecord =null;
		if(SkTaskConfig.TASKTYPE_DAILY.equals(skTaskConfig.getTaskType())) {
			Date dateNow=new Date();
			String createTimeStart=DateFormatUtil.startDate(dateNow);
			String createTimeEnd=DateFormatUtil.endDate(dateNow);
			skUserTaskRecord = skUserTaskRecordDAO.selectOneNotReceiveRecord(param.getTaskRecordId(),Long.valueOf(BaseContextHandler.getCurrentUId()),createTimeStart,createTimeEnd);
		}else {
			skUserTaskRecord = skUserTaskRecordDAO.selectOneNotReceiveRecord(param.getTaskRecordId(),Long.valueOf(BaseContextHandler.getCurrentUId()),null,null);
		}
		
		if(null!=skUserTaskRecord) {
			skUserTaskRecord.setReceiveState(SkUserTaskRecord.RECEIVESTATE_YES);
            skUserTaskRecordDAO.updateByPrimaryKeySelective(skUserTaskRecord);
			UserTaskRecordMqRequest message = new UserTaskRecordMqRequest();
			if(skUserTaskRecord.getTriggerEvent()==1) {
				message.setTradeTypeCode(TradeTypeEnum.PUBLISH_VIDEO_TASK.getCode());
			}else if(skUserTaskRecord.getTriggerEvent()==2) {
				message.setTradeTypeCode(TradeTypeEnum.PUBLISH_VOICE_TASK.getCode());
			}else if(skUserTaskRecord.getTriggerEvent()==3) {
				message.setTradeTypeCode(TradeTypeEnum.ATTENT_TASK.getCode());
			}else if(skUserTaskRecord.getTriggerEvent()==4) {
				message.setTradeTypeCode(TradeTypeEnum.LOGIN_TASK.getCode());
			}else if(skUserTaskRecord.getTriggerEvent()==5) {
				message.setTradeTypeCode(TradeTypeEnum.FIRST_PUBLISH_VOICE_TASK.getCode());
			}
			message.setUserId(Long.valueOf(BaseContextHandler.getCurrentUId()));
			BigDecimal amountNum = new BigDecimal(skUserTaskRecord.getAward());
			message.setGoldCoin(amountNum);
			message.setOrderNo(skUserTaskRecord.getOrderNo());
 			userTaskRecordRabbitmqSender.sendUserRecordMessage(message);
		}
	}

	@Override
	public UserAccountVo getUserAccount(Long userId) throws Exception {
        UserAccountResponse account = accountingCenterProxy.getUserAccount(userId);
        if(account==null){
            return null;
        }
        
        UserAccountVo accountVo= new UserAccountVo();
        accountVo.setGoldCoinsPerDay(account.getGoldCoinsPerDay());
        accountVo.setTotalGoldCoins(account.getTotalGoldCoins());
        accountVo.setYesterdayAmount(account.getYesterdayAmount());
        accountVo.setTotalAmount(account.getTotalAmount());
        accountVo.setDutyFreeAmount(account.getDutyFreeAmount());
        accountVo.setGoldCoinsScale(account.getExchangeRateFrom());
        accountVo.setAmountScale(account.getExchangeRateTo());
        accountVo.setIdentityAttestation(account.getIdentityAttestation());
        accountVo.setApplyAllMoney(account.getApplyAllMoney());
        return accountVo;
	}

	@Override
	public BaseResponse bingIdentityCard(BindingIdentityRequest parmas) {
        parmas.setUserId(UserContextHolder.getUserId());
        if (StringUtils.isBlank(parmas.getIdentityNo()) || 0 == parmas.getUserId() || StringUtils.isBlank(parmas.getUserName())) {
            return BaseResponse.newFailure(ResponseBeanCode.INVALID_FIELDS.getCode(), ResponseBeanCode.INVALID_FIELDS.getMessage());
        }
        return accountingCenterProxy.bingIdentityCard(parmas);
	}

	@Override
	public List<SkWithdrawConfig> showWithdraws() {
		return skWithdrawConfigDAO.selectByWithDrawRule();
	}

	@Override
	public List<UserAccountWithDraw> showActivityWithdraws() {
		 long userId = UserContextHolder.getUserId();
		 if(userId<=0) {
			 return null;
		 }
		return skWithdrawConfigDAO.showActivityWithdraws(userId);
	}
    
	 
 
 
     
}
