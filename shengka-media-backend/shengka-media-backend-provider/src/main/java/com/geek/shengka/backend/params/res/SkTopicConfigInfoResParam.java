package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.enums.SkTopicEnableEnum;
import com.geek.shengka.backend.enums.SkTopicTagEnum;
import com.geek.shengka.backend.enums.SkTopicTypeEnum;
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
@ApiModel(value = "话题")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkTopicConfigInfoResParam extends SkTopicConfig implements Serializable {

    private static final long serialVersionUID = 2255930272683080512L;

    @ApiModelProperty(value = "话题视频映射")
    private List<SkTopicVideoInfoResParam> topicVideos;


    /**
     * 话题类型
     */
    @ApiModelProperty(value = "话题类型")
    private String topicTagName;

    /**
     * 启用,禁用
     */
    @ApiModelProperty(value = "启用,禁用")
    private String enableName;

    /**
     * 话题类型名称
     */
    @ApiModelProperty(value = "话题类型名称")
    private String topicTypeName;

    public String getTopicTagName() {
        return SkTopicTagEnum.getDesc(super.getTopicTag());
    }

    public String getEnableName() {
        return SkTopicEnableEnum.getDesc(super.getEnable());
    }

    public String getTopicTypeName() {
        return SkTopicTypeEnum.getDesc(super.getTopicType());
    }
}
