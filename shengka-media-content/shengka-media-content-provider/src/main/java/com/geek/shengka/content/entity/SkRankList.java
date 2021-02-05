package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

public class SkRankList implements Serializable {
	/**
	 * 主键
	 */
    private Integer id;

	/**
	 * 榜单logo
	 */
    private String rankLogo;

	/**
	 * 榜单名称
	 */
    private String rankName;

    /**
	 * 榜单名称
	 */
    private String rankBanner;

	/**
	 * 榜单规则
	 */
    private String rankRule;

	/**
	 * 排序
	 */
    private Integer seq;

	/**
	 * 榜单类型（1-视频  2-用户  3-话题）
	 */
    private Byte rankType;
    /**
	 * 0-有效  1-无效
	 */
    private Byte enable;

	/**
	 * 备注
	 */
    private String remark;

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

    private static final long serialVersionUID = 1L;

	/**
	 * 取 主键
	 * @return
	 */
    public Integer getId() {
        return id;
    }

	/**
	 * 赋值  主键
	 * @param id
	 */
    public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * 取 榜单logo
	 * @return
	 */
    public String getRankLogo() {
        return rankLogo;
    }

	/**
	 * 赋值  榜单logo
	 * @param rankLogo
	 */
    public void setRankLogo(String rankLogo) {
        this.rankLogo = rankLogo == null ? null : rankLogo.trim();
    }

	/**
	 * 取 榜单名称
	 * @return
	 */
    public String getRankName() {
        return rankName;
    }

	/**
	 * 赋值  榜单名称
	 * @param rankName
	 */
    public void setRankName(String rankName) {
        this.rankName = rankName == null ? null : rankName.trim();
    }

	/**
	 * 取 榜单规则
	 * @return
	 */
    public String getRankRule() {
        return rankRule;
    }

	/**
	 * 赋值  榜单规则
	 * @param rankRule
	 */
    public void setRankRule(String rankRule) {
        this.rankRule = rankRule == null ? null : rankRule.trim();
    }

	/**
	 * 取 排序
	 * @return
	 */
    public Integer getSeq() {
        return seq;
    }

	/**
	 * 赋值  排序
	 * @param seq
	 */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
	 * 取 备注
	 * @return
	 */
    public String getRemark() {
        return remark;
    }

	/**
	 * 赋值  备注
	 * @param remark
	 */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getRankBanner() {
        return rankBanner;
    }

    public void setRankBanner(String rankBanner) {
        this.rankBanner = rankBanner;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public SkRankList(){}

	public SkRankList(Integer id,String rankLogo,String rankName,String rankRule,Integer seq,Byte rankType,String remark,Date createTime,String createBy,Date updateTime,String updateBy) {
		this.id = id;
		this.rankLogo = rankLogo;
		this.rankName = rankName;
		this.rankRule = rankRule;
		this.seq = seq;
		this.rankType = rankType;
		this.remark = remark;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return String.format(
			"SkRankList [id=%s, rankLogo=%s, rankName=%s, rankRule=%s, seq=%s, rankType=%s, remark=%s, createTime=%s, createBy=%s, updateTime=%s, updateBy=%s]",
			id, rankLogo, rankName, rankRule, seq, rankType, remark, createTime, createBy, updateTime, updateBy);
	}

}
