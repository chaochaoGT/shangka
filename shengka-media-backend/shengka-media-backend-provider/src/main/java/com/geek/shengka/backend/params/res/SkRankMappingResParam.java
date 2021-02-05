package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkRankMapping;
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
@ApiModel(value = "榜单视频")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkRankMappingResParam extends SkRankMapping implements Serializable {

    private static final long serialVersionUID = -3990521139345177363L;

    /**
     * 榜单类型
     */
    @ApiModelProperty(value = "榜单类型")
    private String rankTypeName;

    public String getRankTypeName() {
        return SkRankTypeEnum.getDesc(super.getRankType());
    }
}
