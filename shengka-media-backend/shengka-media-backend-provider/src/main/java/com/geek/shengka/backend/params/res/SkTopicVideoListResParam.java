package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkTopicVideo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "话题视频")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkTopicVideoListResParam extends SkTopicVideo implements Serializable {

    private static final long serialVersionUID = -4180764681791880288L;
}
