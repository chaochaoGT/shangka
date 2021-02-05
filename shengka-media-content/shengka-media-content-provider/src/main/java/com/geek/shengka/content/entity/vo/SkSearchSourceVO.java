package com.geek.shengka.content.entity.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Filename: SkSearchSourceVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/5 ;
 */
@Data
public class SkSearchSourceVO implements Serializable {
    private static final long serialVersionUID = 1154551L;
    /**
     *资源id
     */
    private String sourceId;
    /**
     *资源编号
     */
    private String sourceNo;
    /**
     * 封面地址
     */
    private String sourceCoverUrl;
    /**
     * 资源标识
     */
    private String sourceIconUrl;
    /**
     * 资源url
     */
    private String sourceUrl;
    /**
     * 资源简介
     */
    private String sourceDesc;
    /**
     * 资源名称
     */
    private String sourceName;
    /**
     * 资源Banner
     */
    private String sourceBanner;
    /**
     * 资源发布量
     */
    private String pushNumbs;
    /**
     * 资源粉丝量
     */
    private String fansNums;
    /**
     * 资源观看量
     */
    private String watchTimes;
  /**
     * 资源点赞量
     */
    private String giveThumbsNums;
    /**
     * 资源0 短视频 1小视频
     */
    private Integer watchMode;
    /**
     * 资源排序
     */
    private Integer seq;
    /**
     * 资源类型 类型 0视频 1用户 2话题
     */
    private Integer sourceType;
    /**
     * 创作人名称
     */
    private String createBy;
    /**
     * 创作人图像
     */
    private String avatarUrl;
    /**
     * 创作人id
     */
    private String avatarId;
    /**
     * 资源更新时间
     */
    private Date updateTime;
    /**
     * 资源类型 （1-热门 2-推荐 3-新增）
     */
    private Integer topicTag;
    /**
     * 是否
     * 关注 0未关注 1已关注 2 互相关注
     * 喜欢 0不喜欢 1 喜欢
     */
    private int attentionFlag;
    /**
     * 用户性别，1：男，0：女，2：未知
     */
    private Integer gender;

    /**内容中心分类id*/
    private String contentCategoryCode;
    /**内容中心分类name*/
    private String contentCategoryName;


    public Integer intWatchTimes(){
        if(StringUtils.isNotEmpty(watchTimes)&&watchTimes.endsWith("万")){
            String numb=watchTimes.replace("万","");
            return Integer.valueOf(numb)*10000;
        }
        return Integer.valueOf(watchTimes);
    }
}
