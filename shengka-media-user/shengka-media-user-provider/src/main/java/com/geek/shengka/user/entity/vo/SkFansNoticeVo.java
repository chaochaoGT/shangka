package com.geek.shengka.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkFansNoticeVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4676079512650657373L;

	/**
     * 主键
     */
    private Long id;

    /**
     * 关注用户ID
     */
    private Long userId;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 0-单粉，1-互粉
     */
    private Integer fansState;

    /**
     * 通知状态（0-未查看  1-已查看  2-已删除）
     */
    private Integer noticeState;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;


}
