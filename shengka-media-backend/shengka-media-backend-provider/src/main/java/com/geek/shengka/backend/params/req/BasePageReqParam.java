package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:36
 */
@Data
@ApiModel(description = "分页查询之页面")
public class BasePageReqParam implements Serializable {
    private static final long serialVersionUID = -7751317857537484138L;

    @ApiModelProperty(value = "当前第几页")
    @JsonIgnore
    private int pageNo;
    @ApiModelProperty(value = "当前每页的数量")
    @JsonIgnore
    private int pageSize;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private int offset;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段，默认创建时间倒序排序")
    private String orderBy = "create_time desc";

    public Integer getPageNo() {
        return pageNo < 1 ? 1 : pageNo;
    }

    public Integer getPageSize() {
        this.pageSize = this.pageSize < 1 ? 10 : pageSize;
        this.pageSize = this.pageSize > 30 ? 30 : pageSize;
        return pageSize;
    }

    public int getOffset() {
        return (getPageNo() - 1) * getPageSize();
    }
}
