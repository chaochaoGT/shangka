package com.geek.shengka.gold.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.enums.TriggerEventEnum;
import com.geek.shengka.gold.api.entity.UserTaskCacheInfo;
import com.geek.shengka.gold.entity.SkTaskConfig;
import com.geek.shengka.gold.entity.SkUserTaskRecord;
import com.geek.shengka.gold.mapper.SkTaskConfigDAO;
import com.geek.shengka.gold.mapper.SkUserTaskRecordDAO;
import com.geek.shengka.gold.response.DailyConfigResponse;
import com.geek.shengka.gold.response.GlobalConfigResponse;
import com.geek.shengka.gold.response.TaskConfigResponse;
import com.geek.shengka.gold.service.SkTaskConfigService;
import com.geek.shengka.gold.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每日任务实现
 *
 * @author: yunfei
 * @create: 2019-08-02 15:07
 **/
@Service
public class SkTaskConfigServiceImpl implements SkTaskConfigService {

    @Autowired
    private SkTaskConfigDAO skTaskConfigDAO;

    @Autowired
    private SkUserTaskRecordDAO skUserTaskRecordDAO;

    @Autowired
    private TaskRedisService taskRedisService;

    private static final String GLOBAL_CONFIG_COIN="globalConfigcoin";


    @Override
    public BaseResponse dailyConfig(Long userId) {
        System.out.println("###########userId" + userId);
        List<SkTaskConfig> list = skTaskConfigDAO.dailyConfig();
        List<DailyConfigResponse> dayTask = list.stream().filter(skTaskConfig -> 1 == skTaskConfig.getTaskType().intValue()).map(skTaskConfig -> translation(skTaskConfig, userId)).collect(Collectors.toList());
        List<DailyConfigResponse> newTask = list.stream().filter(skTaskConfig -> 2 == skTaskConfig.getTaskType().intValue()).map(skTaskConfig -> translation(skTaskConfig, userId)).collect(Collectors.toList());
        newTask = newTask.stream().filter(dailyConfigResponse -> 5 != dailyConfigResponse.getTaskStatus()).collect(Collectors.toList());
        return BaseResponse.newSuccess(TaskConfigResponse.builder().dailyTask(dayTask).newTask(newTask).build());
    }

    @Override
    public BaseResponse globalConfig() {
        GlobalConfigResponse configResponse = CacheProvider.getObject(GLOBAL_CONFIG_COIN, GlobalConfigResponse.class);
        if(configResponse==null) {
            SkTaskConfig config = skTaskConfigDAO.globalConfig();
            configResponse = new GlobalConfigResponse();
            if (config != null) {
                configResponse.setCoin(config.getAwardFixAmount());
                CacheProvider.setObject(GLOBAL_CONFIG_COIN,configResponse,1000*60*30);
            }
        }
        return BaseResponse.newSuccess(configResponse);
    }

    private DailyConfigResponse translation(SkTaskConfig skTaskConfig, Long userId) {
        DailyConfigResponse configResponse = new DailyConfigResponse();
        configResponse.setTaskId(skTaskConfig.getId());
        configResponse.setTaskName(skTaskConfig.getTaskName());
        configResponse.setTaskIntro(skTaskConfig.getTaskIntro());
        configResponse.setReceiveLimit(skTaskConfig.getReceiveLimit());
        UserTaskCacheInfo cache = getTaskCache(configResponse.getTaskId(), userId, configResponse.getReceiveLimit(), skTaskConfig.getTriggerEvent().intValue(), skTaskConfig.getTaskType().intValue());
        configResponse.setGetNum(cache.getCompleteCount());
        configResponse.setTaskStatus(cache.getStatus());
        configResponse.setAwardAmountType(skTaskConfig.getAwardAmountType());
        configResponse.setAwardFixAmount(skTaskConfig.getAwardFixAmount());
        configResponse.setAwardMin(skTaskConfig.getAwardMin());
        configResponse.setAwardMax(skTaskConfig.getAwardMax());
        return configResponse;
    }

    /**
     * 先取缓存,未命中取db
     *
     * @param taskId       任务ID
     * @param userId       用户ID
     * @param limit        任务领取上限
     * @param triggerEvent 触发类型
     * @return
     */
    public UserTaskCacheInfo getTaskCache(Long taskId, Long userId, Integer limit, int triggerEvent, int taskType) {
        String redisKey = String.format(TaskRedisService.DAILYUSERTASK, userId, taskId, DateFormatUtil.day(new Date()));
        long cacheTime = TaskRedisService.DAILYUSERTASKTIMEOUT;
        if (taskType == 2) {
            redisKey = String.format(TaskRedisService.NEWMAN_USERTASK, userId, taskId);
            cacheTime = TaskRedisService.NEWMAN_USERTASK_TIMEOUT;
        }
        UserTaskCacheInfo cacheInfo = taskRedisService.get(redisKey);
        if (cacheInfo == null) {
            cacheInfo = new UserTaskCacheInfo();
            Long getNum = 0L;
            Integer taskCount = 0;
            String createTimeStart = null;
            String createTimeEnd = null;
            if (userId != null) {
                if (taskType == 1) {
                    Date dateNow = new Date();
                    createTimeStart = DateFormatUtil.startDate(dateNow);
                    createTimeEnd = DateFormatUtil.endDate(dateNow);
                }
                List<SkUserTaskRecord> list = skUserTaskRecordDAO.selectTaskRecord(taskId, userId, createTimeStart, createTimeEnd);
                taskCount = list.size();
                getNum = list.stream().filter(record -> record.getReceiveState().intValue() == 1).count();
            }
            cacheInfo.setCompleteCount(taskCount);

            if (getNum.intValue() == limit) {
                //已完成
                cacheInfo.setStatus(UserTaskCacheInfo.status_hadTook);
            } else if ((taskCount - getNum) > 0) {
                //有待领取
                cacheInfo.setStatus(UserTaskCacheInfo.status_take);
            } else {
                //去做任务
                cacheInfo.setStatus(TriggerEventEnum.getValue(triggerEvent));
            }
            cacheInfo.setTaskId(taskId);
            cacheInfo.setUserId(userId);
            taskRedisService.set(redisKey, cacheInfo, cacheTime);
        }
        return cacheInfo;

    }
}
