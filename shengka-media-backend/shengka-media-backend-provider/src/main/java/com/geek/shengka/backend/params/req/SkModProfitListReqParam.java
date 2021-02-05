package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/26 10:39
 */
@Data
@ApiModel
public class SkModProfitListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = 4489632577721577981L;
    /**
     * 收入主体
     */
    @ApiModelProperty(value = "收入主体")
    private String profitMain;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间 yyyy-MM-dd")
    private Date dateBegin;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间 yyyy-MM-dd")
    private Date dateEnd;
}
