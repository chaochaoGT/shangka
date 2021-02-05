package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkChannel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "渠道")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkChannelInfoResParam extends SkChannel implements Serializable {

    private static final long serialVersionUID = -4180764681791880288L;
}
