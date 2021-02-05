package com.geek.shengka.content.entity.vo;

import com.geek.shengka.content.entity.SkRankList;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/21 17:26
 */
@Data
public class SkRankListVO extends SkRankList implements Serializable {
    private static final long serialVersionUID = 7278473775111014061L;

    /**
     * 排行第一的内容名称
     */
    private String topOneName;
}
