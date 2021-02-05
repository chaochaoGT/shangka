package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

public class SkSearchModuleMapping implements Serializable {
	/**
	 * 主键
	 */
    private Long id;

	/**
	 * 配置表id
	 */
    private Long configId;

	/**
	 * 类型（1-视频  2-话题）
	 */
    private Byte moduleType;

	/**
	 * 映射资源id
	 */
    private String sourceId;

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
	 * 取 配置表id
	 * @return
	 */
    public Long getConfigId() {
        return configId;
    }

	/**
	 * 赋值  配置表id
	 * @param configId
	 */
    public void setConfigId(Long configId) {
        this.configId = configId;
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
	 * 取 映射资源id
	 * @return
	 */
    public String getSourceId() {
        return sourceId;
    }

	/**
	 * 赋值  映射资源id
	 * @param sourceId
	 */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
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

	public SkSearchModuleMapping(){}

	public SkSearchModuleMapping(Long id,Long configId,Byte moduleType,String sourceId,Integer seq,Date createTime,String createBy,Date updateTime,String updateBy) {
		this.id = id;
		this.configId = configId;
		this.moduleType = moduleType;
		this.sourceId = sourceId;
		this.seq = seq;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return String.format(
			"SkSearchModuleMapping [id=%s, configId=%s, moduleType=%s, sourceId=%s, seq=%s, createTime=%s, createBy=%s, updateTime=%s, updateBy=%s]",
			id, configId, moduleType, sourceId, seq, createTime, createBy, updateTime, updateBy);
	}

}
