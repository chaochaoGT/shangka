package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkRankList;
import com.geek.shengka.backend.enums.SkRankTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "榜单")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkRankListResParam extends SkRankList implements Serializable {

    private static final long serialVersionUID = -5076204903920280581L;

    /**
     * 榜单类型名称
     */
    @ApiModelProperty(value = "榜单类型")
    private String rankTypeName;

    /**
     * 榜单类型名称
     */
    public String getRankTypeName() {
        return SkRankTypeEnum.getDesc(super.getRankType());
    }
}
