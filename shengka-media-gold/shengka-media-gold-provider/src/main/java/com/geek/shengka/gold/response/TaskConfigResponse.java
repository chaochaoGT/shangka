package com.geek.shengka.gold.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 任务配置
 *
 * @author: yunfei
 * @create: 2019-08-08 15:27
 **/
@Data
@Builder
public class TaskConfigResponse {


    /**日常任务*/
    private List<DailyConfigResponse> dailyTask;

    /**新手任务*/
    private List<DailyConfigResponse> newTask;
}
