package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkRankList;
import com.geek.shengka.backend.enums.SkRankTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "榜单详情")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkRankInfoResParam extends SkRankList implements Serializable {

    private static final long serialVersionUID = 2333114501207205002L;

    @ApiModelProperty(value = "榜单映射")
    private List<SkRankMappingResParam> mappings;


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
