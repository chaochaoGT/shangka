package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

public class SkRankMapping implements Serializable {
	/**
	 * 主键
	 */
    private Long id;

	/**
	 * 榜单id
	 */
    private Long rankId;

	/**
	 * 榜单类型（1-视频  2-用户  3-话题）
	 */
    private Byte rankType;

	/**
	 * 关联id（视频id/用户id/话题id）
	 */
    private String relId;

	/**
	 * 创建时间
	 */
    private Date createTime;

	/**
	 * 创建人
	 */
    private String createBy;

	/**
	 * 更新时间
	 */
    private Date updateTime;

	/**
	 * 更新人
	 */
    private String updateBy;
    /**
     * 排序
     */
    private Integer seq;

    private static final long serialVersionUID = 1L;

	/**
	 * 取 主键
	 * @return
	 */
    public Long getId() {
        return id;
    }

	/**
	 * 赋值  主键
	 * @param id
	 */
    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * 取 榜单id
	 * @return
	 */
    public Long getRankId() {
        return rankId;
    }

	/**
	 * 赋值  榜单id
	 * @param rankId
	 */
    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

	/**
	 * 取 榜单类型（1-视频  2-用户  3-话题）
	 * @return
	 */
    public Byte getRankType() {
        return rankType;
    }

	/**
	 * 赋值  榜单类型（1-视频  2-用户  3-话题）
	 * @param rankType
	 */
    public void setRankType(Byte rankType) {
        this.rankType = rankType;
    }

	/**
	 * 取 关联id（视频id/用户id/话题id）
	 * @return
	 */
    public String getRelId() {
        return relId;
    }

	/**
	 * 赋值  关联id（视频id/用户id/话题id）
	 * @param relId
	 */
    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

	/**
	 * 取 创建时间
	 * @return
	 */
    public Date getCreateTime() {
        return createTime;
    }

	/**
	 * 赋值  创建时间
	 * @param createTime
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * 取 创建人
	 * @return
	 */
    public String getCreateBy() {
        return createBy;
    }

	/**
	 * 赋值  创建人
	 * @param createBy
	 */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	/**
	 * 取 更新时间
	 * @return
	 */
    public Date getUpdateTime() {
        return updateTime;
    }

	/**
	 * 赋值  更新时间
	 * @param updateTime
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * 取 更新人
	 * @return
	 */
    public String getUpdateBy() {
        return updateBy;
    }

	/**
	 * 赋值  更新人
	 * @param updateBy
	 */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public SkRankMapping(){}

	public SkRankMapping(Long id,Long rankId,Byte rankType,String relId,Date createTime,String createBy,Date updateTime,String updateBy) {
		this.id = id;
		this.rankId = rankId;
		this.rankType = rankType;
		this.relId = relId;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return String.format(
			"SkRankMapping [id=%s, rankId=%s, rankType=%s, relId=%s, createTime=%s, createBy=%s, updateTime=%s, updateBy=%s]",
			id, rankId, rankType, relId, createTime, createBy, updateTime, updateBy);
	}

}
