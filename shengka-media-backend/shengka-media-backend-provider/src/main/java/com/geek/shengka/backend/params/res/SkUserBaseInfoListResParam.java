package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkUserBaseInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/20 16:31
 */
@Data
@ApiModel
public class SkUserBaseInfoListResParam extends SkUserBaseInfo implements Serializable {
    private static final long serialVersionUID = -2943540031049541314L;

}
