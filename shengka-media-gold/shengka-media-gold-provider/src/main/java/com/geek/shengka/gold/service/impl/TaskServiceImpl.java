package com.geek.shengka.gold.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.gold.api.entity.UserTaskCacheInfo;
import com.geek.shengka.gold.entity.SkTaskConfig;
import com.geek.shengka.gold.entity.SkUserTaskRecord;
import com.geek.shengka.gold.mapper.SkTaskConfigDAO;
import com.geek.shengka.gold.mapper.SkUserTaskRecordDAO;
import com.geek.shengka.gold.rabbitmq.entity.GetTaskCoinEvent;
import com.geek.shengka.gold.rabbitmq.entity.TaskEvent;
import com.geek.shengka.gold.util.DateFormatUtil;
import com.geek.shengka.gold.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class TaskServiceImpl  {

	@Autowired
	private SkTaskConfigDAO skTaskConfigDAO;
	
	@Autowired
	private SkUserTaskRecordDAO skUserTaskRecordDAO;
	
    @Autowired
    private RedisManageServiceImpl redisManageService;
    
    @Autowired
    private TaskRedisService taskRedisService;


    public final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    
    /**
     * 1.根据事件类型查询SkTaskConfig表
	   2.   遍历
	      2.1查询SkUserTaskRecord是否当天已经插入过记录（新手任务为1次，每日任务为5次）
	  	                       否，插入SkUserTaskRecord记录
			                   修改Redis中的值，将状态修改为领取
     * @param taskEvent
     */
	@Transactional
	public void taskEventProcess(TaskEvent taskEvent) {
		Assert.notNull(taskEvent,"taskEvent 不能为空");
		List<SkTaskConfig> taskconfigs=skTaskConfigDAO.selectByTriggerEvent(taskEvent.getType());
		if(!CollectionUtils.isEmpty(taskconfigs)) {
			Date dateNow=new Date();
			List<SkUserTaskRecord> skUserTaskRecords=new ArrayList<SkUserTaskRecord>();
			for(SkTaskConfig skTaskConfig:taskconfigs ) {
				Integer count=0;
				if(SkTaskConfig.TASKTYPE_DAILY.equals(skTaskConfig.getTaskType())) {
					String createTimeStart=DateFormatUtil.startDate(dateNow);
					String createTimeEnd=DateFormatUtil.endDate(dateNow);
			        count=skUserTaskRecordDAO.countByUserIdAndTaskConfigId(skTaskConfig.getId(), taskEvent.getUserId(),createTimeStart,createTimeEnd);
				}else {
				    count=skUserTaskRecordDAO.countByUserIdAndTaskConfigId(skTaskConfig.getId(), taskEvent.getUserId(),null,null);
				}
				if(!isLessThanLimit(skTaskConfig, count)) {
					continue;
				}
				SkUserTaskRecord skUserTaskRecord=new SkUserTaskRecord();
				skUserTaskRecords.add(skUserTaskRecord);
				skUserTaskRecord.setCompleteCount(count==null?1:count+1);
				if(SkTaskConfig.AWARDAMOUNTTYPE_FIXED.equals(skTaskConfig.getAwardAmountType())) {
					skUserTaskRecord.setAward(skTaskConfig.getAwardFixAmount());
				}else {
					skUserTaskRecord.setAward(RandomUtil.getRandom(skTaskConfig.getAwardMin(), skTaskConfig.getAwardMax()));
				}
				skUserTaskRecord.setAwardAmountType(skTaskConfig.getAwardAmountType());
				skUserTaskRecord.setAwardFixAmount(skTaskConfig.getAwardFixAmount());
				skUserTaskRecord.setAwardMax(skTaskConfig.getAwardMax());
				skUserTaskRecord.setAwardMin(skTaskConfig.getAwardMin());
				skUserTaskRecord.setCreateTime(new Date());
				skUserTaskRecord.setHandleState(SkUserTaskRecord.HANDLESTATE_WAITINGPROCESS);
				skUserTaskRecord.setHandleRemark("");
				skUserTaskRecord.setOrderNo(redisManageService.getUserTaskOrderNo());
				skUserTaskRecord.setProcessCts(0);
				skUserTaskRecord.setReceiveLimit(skTaskConfig.getReceiveLimit());
				skUserTaskRecord.setReceiveState(SkUserTaskRecord.RECEIVESTATE_NOT);
				skUserTaskRecord.setScychronizeFlag("");
				skUserTaskRecord.setTaskId(skTaskConfig.getId());
				skUserTaskRecord.setTaskType(skTaskConfig.getTaskType());
				skUserTaskRecord.setTradeNo("");
				skUserTaskRecord.setTriggerEvent(skTaskConfig.getTriggerEvent());
				skUserTaskRecord.setUpdateTime(skUserTaskRecord.getCreateTime() );
				skUserTaskRecord.setUserId(taskEvent.getUserId());
				
			}
			if(!CollectionUtils.isEmpty(skUserTaskRecords)) {
				for(SkUserTaskRecord skUserTaskRecord:skUserTaskRecords) {
					skUserTaskRecordDAO.insertSelective(skUserTaskRecord);
					UserTaskCacheInfo userTaskCacheInfo=new UserTaskCacheInfo();
					userTaskCacheInfo.setCompleteCount(skUserTaskRecord.getCompleteCount());
					userTaskCacheInfo.setTaskId(skUserTaskRecord.getTaskId());
					userTaskCacheInfo.setUserId(taskEvent.getUserId());
					userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_take);
					if(SkTaskConfig.TASKTYPE_DAILY.equals(skUserTaskRecord.getTaskType())) {
						taskRedisService.set(String.format(TaskRedisService.DAILYUSERTASK, taskEvent.getUserId(),skUserTaskRecord.getTaskId(),DateFormatUtil.day(dateNow)), userTaskCacheInfo, TaskRedisService.DAILYUSERTASKTIMEOUT);
					}else {
						taskRedisService.set(String.format(TaskRedisService.NEWMAN_USERTASK,  taskEvent.getUserId(),skUserTaskRecord.getTaskId()), userTaskCacheInfo, TaskRedisService.NEWMAN_USERTASK_TIMEOUT);
					}
				}
			}
		}

	}

	private boolean isLessThanLimit(SkTaskConfig skTaskConfig, Integer count) {
		boolean isInsert =true;
		if(count!=null) { 
			if(SkTaskConfig.TASKTYPE_DAILY.equals(skTaskConfig.getTaskType())) {	//每日任务满足等于或超过限制条数时不插入
				if(count.compareTo(skTaskConfig.getReceiveLimit())>=0) {
					isInsert=false;
				}
			}else {
				if(count.compareTo(1)>=0) {											//新手任务满足等于或超过1时不插入
					isInsert=false;
				}
			}
		}
		return isInsert;
	}
	
	/**
	 * 根据id获取SkTaskConfig记录，判断是新手任务还是每日任务
	   新手任务，修改redis状态为已领取
	   每日任务，根据用户id和taskconfigid查询SkUserTaskRecord记录
				如果未领取数量等于0，并且记录总数<当日限制数，判断事件类型，修改状态 2:去评论，3:去上传，4:去关注
										 记录总数>=当日限制数，修改状态 5：已领取
	 * @param getTaskCoinEvent
	 */
	public void getTaskCoinProcess(GetTaskCoinEvent getTaskCoinEvent) {
		Assert.notNull(getTaskCoinEvent,"GetTaskCoinEvent 不能为空");
		SkTaskConfig skTaskConfig=skTaskConfigDAO.selectByPrimaryKey(getTaskCoinEvent.getTaskConfigId());
		if(SkTaskConfig.TASKTYPE_DAILY.equals(skTaskConfig.getTaskType())) {
			UserTaskCacheInfo userTaskCacheInfo = taskRedisService.get(String.format(TaskRedisService.DAILYUSERTASK,  getTaskCoinEvent.getUserId(),skTaskConfig.getId(),DateFormatUtil.day(getTaskCoinEvent.getTimeInMilliseconds())));
	        if(userTaskCacheInfo==null) {
		    	logger.error("缓存中未找到记录key:{}",String.format(TaskRedisService.DAILYUSERTASK,  getTaskCoinEvent.getUserId(),skTaskConfig.getId(),DateFormatUtil.day(getTaskCoinEvent.getTimeInMilliseconds())));
		    	return;
	        }
			String createTimeStart=DateFormatUtil.startDate(getTaskCoinEvent.getTimeInMilliseconds());
	        String createTimeEnd=DateFormatUtil.endDate(getTaskCoinEvent.getTimeInMilliseconds());
		    List<SkUserTaskRecord> skUserTaskRecords = skUserTaskRecordDAO.selectTaskRecord(skTaskConfig.getId(), getTaskCoinEvent.getUserId(),createTimeStart,createTimeEnd);
		    if(!CollectionUtils.isEmpty(skUserTaskRecords)) {
		        Long getNum = skUserTaskRecords.stream().filter(record -> record.getReceiveState().intValue() == SkUserTaskRecord.RECEIVESTATE_NOT.intValue()).count();
		        if(getNum.compareTo(0L)==0) {
		        	if(skUserTaskRecords.size()<skTaskConfig.getReceiveLimit().intValue()) {
						if(SkTaskConfig.TRIGGEREVENT_ATTENTIONUSER.equals(skTaskConfig.getTriggerEvent())) {
							userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_goAttention);
						}else if(SkTaskConfig.TRIGGEREVENT_PUBLISHVIDEO.equals(skTaskConfig.getTriggerEvent())) {
							userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_goUpload);
						}else if(SkTaskConfig.TRIGGEREVENT_VOICECOMMENT.equals(skTaskConfig.getTriggerEvent())) {
							userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_goComment);
						}
		        	}else {
		        		userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_hadTook);
		        	}
		        	taskRedisService.set(String.format(TaskRedisService.DAILYUSERTASK,  getTaskCoinEvent.getUserId(),skTaskConfig.getId(),DateFormatUtil.day(getTaskCoinEvent.getTimeInMilliseconds())),userTaskCacheInfo,TaskRedisService.DAILYUSERTASKTIMEOUT);
		        }
		        
		    }else {
		    	logger.error("未查找到任何任务金币记录:{}",JSONObject.toJSONString(getTaskCoinEvent));
		    }
		}else {
			UserTaskCacheInfo userTaskCacheInfo = taskRedisService.get(String.format(TaskRedisService.NEWMAN_USERTASK,  getTaskCoinEvent.getUserId(),skTaskConfig.getId()));
	        if(userTaskCacheInfo==null) {
		    	logger.error("缓存中未找到记录key:{}",String.format(TaskRedisService.NEWMAN_USERTASK,  getTaskCoinEvent.getUserId(),skTaskConfig.getId()));
		    	return;
	        }
			userTaskCacheInfo.setStatus(UserTaskCacheInfo.status_hadTook);
			taskRedisService.set(String.format(TaskRedisService.NEWMAN_USERTASK, getTaskCoinEvent.getUserId(),skTaskConfig.getId()), userTaskCacheInfo, TaskRedisService.NEWMAN_USERTASK_TIMEOUT);
		}
	}

}
