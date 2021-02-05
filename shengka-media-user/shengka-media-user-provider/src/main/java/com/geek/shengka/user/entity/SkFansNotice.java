package com.geek.shengka.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_fans_notice
 * @author 
 */
public class SkFansNotice implements Serializable {
	
	public static final Byte noticeState_notLook=0;
	
	public static final Byte fansState_single=0;
	
	public static final Byte fansState_double=1;
    /**
	 * 
	 */
	private static final long serialVersionUID = -2837617304473486149L;

	/**
     * 主键
     */
    private Long id;

    /**
     * 用户
     */
    private Long userId;

    /**
     * 被关注用户
     */
    private Long attentionUserId;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 0-单粉，1-互粉
     */
    private Byte fansState;

    /**
     * 通知状态（0-未查看  1-已查看  2-已删除）
     */
    private Byte noticeState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAttentionUserId() {
        return attentionUserId;
    }

    public void setAttentionUserId(Long attentionUserId) {
        this.attentionUserId = attentionUserId;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Byte getFansState() {
        return fansState;
    }

    public void setFansState(Byte fansState) {
        this.fansState = fansState;
    }

    public Byte getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(Byte noticeState) {
        this.noticeState = noticeState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}