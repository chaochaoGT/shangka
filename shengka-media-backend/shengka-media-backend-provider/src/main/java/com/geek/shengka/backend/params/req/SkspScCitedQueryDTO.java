package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * llsp_sc_cited
 * @author 
 */
@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkspScCitedQueryDTO extends BasePageReqParam implements Serializable {

	private static final long serialVersionUID = 6809864866534385963L;


	/**市场**/
	@ApiModelProperty(value = "市场")
    private String market;

    
    /**开始时间**/
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /** 结束时间*/
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

   


}