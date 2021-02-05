package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkCategoryVideoConfig;
import com.geek.shengka.backend.entity.SkPublishVideo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:46
 */
@Data
@ApiModel
public class SkCategoryVideoConfigInfoResParam extends SkCategoryVideoConfig implements Serializable {

    @ApiModelProperty(value = "视频")
    private SkPublishVideo video;
}
