package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

public class SkSearchPageConfig implements Serializable {
	/**
	 * 主键
	 */
    private Long id;

	/**
	 * 模块名称
	 */
    private String moduleName;

	/**
	 * 模块编号（hot_search：声咖热搜   popular_rank：人气榜单   hotest_video：最热视频   find_wonder：发现精彩  ）
	 */
    private String moduleCode;

	/**
	 * 类型（1-视频  2-话题）
	 */
    private Byte moduleType;

	/**
	 * 模块简介
	 */
    private String moduleDesc;

	/**
	 * banner图地址
	 */
    private String bannerUrl;

	/**
	 * icon地址
	 */
    private String iconUrl;

	/**
	 * 0-有效  1-无效
	 */
    private Byte enable;

	/**
	 * 排序
	 */
    private Integer seq;

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
	 * 取 模块名称
	 * @return
	 */
    public String getModuleName() {
        return moduleName;
    }

	/**
	 * 赋值  模块名称
	 * @param moduleName
	 */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

	/**
	 * 取 模块编号（hot_search：声咖热搜   popular_rank：人气榜单   hotest_video：最热视频   find_wonder：发现精彩  ）
	 * @return
	 */
    public String getModuleCode() {
        return moduleCode;
    }

	/**
	 * 赋值  模块编号（hot_search：声咖热搜   popular_rank：人气榜单   hotest_video：最热视频   find_wonder：发现精彩  ）
	 * @param moduleCode
	 */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

	/**
	 * 取 类型（1-视频  2-话题）
	 * @return
	 */
    public Byte getModuleType() {
        return moduleType;
    }

	/**
	 * 赋值  类型（1-视频  2-话题）
	 * @param moduleType
	 */
    public void setModuleType(Byte moduleType) {
        this.moduleType = moduleType;
    }

	/**
	 * 取 模块简介
	 * @return
	 */
    public String getModuleDesc() {
        return moduleDesc;
    }

	/**
	 * 赋值  模块简介
	 * @param moduleDesc
	 */
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc == null ? null : moduleDesc.trim();
    }

	/**
	 * 取 banner图地址
	 * @return
	 */
    public String getBannerUrl() {
        return bannerUrl;
    }

	/**
	 * 赋值  banner图地址
	 * @param bannerUrl
	 */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

	/**
	 * 取 icon地址
	 * @return
	 */
    public String getIconUrl() {
        return iconUrl;
    }

	/**
	 * 赋值  icon地址
	 * @param iconUrl
	 */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

	/**
	 * 取 0-有效  1-无效
	 * @return
	 */
    public Byte getEnable() {
        return enable;
    }

	/**
	 * 赋值  0-有效  1-无效
	 * @param enable
	 */
    public void setEnable(Byte enable) {
        this.enable = enable;
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

	public SkSearchPageConfig(){}

	public SkSearchPageConfig(Long id,String moduleName,String moduleCode,Byte moduleType,String moduleDesc,String bannerUrl,String iconUrl,Byte enable,Integer seq,Date createTime,String createBy,Date updateTime,String updateBy) {
		this.id = id;
		this.moduleName = moduleName;
		this.moduleCode = moduleCode;
		this.moduleType = moduleType;
		this.moduleDesc = moduleDesc;
		this.bannerUrl = bannerUrl;
		this.iconUrl = iconUrl;
		this.enable = enable;
		this.seq = seq;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return String.format(
			"SkSearchPageConfig [id=%s, moduleName=%s, moduleCode=%s, moduleType=%s, moduleDesc=%s, bannerUrl=%s, iconUrl=%s, enable=%s, seq=%s, createTime=%s, createBy=%s, updateTime=%s, updateBy=%s]",
			id, moduleName, moduleCode, moduleType, moduleDesc, bannerUrl, iconUrl, enable, seq, createTime, createBy, updateTime, updateBy);
	}

}
