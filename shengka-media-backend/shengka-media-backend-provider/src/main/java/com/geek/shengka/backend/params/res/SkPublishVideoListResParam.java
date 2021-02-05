package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkPublishVideo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/20 15:46
 */
@Data
@ApiModel
public class SkPublishVideoListResParam extends SkPublishVideo implements Serializable {
    private static final long serialVersionUID = -6589433036537098887L;
}
