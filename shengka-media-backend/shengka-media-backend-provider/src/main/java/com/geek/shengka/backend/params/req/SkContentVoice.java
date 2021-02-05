package com.geek.shengka.backend.params.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/20 17:43
 */
@Data
public class SkContentVoice implements Serializable {
    /**
     * 语音ID
     */
    private String audioId;
    /**
     * 视频id
     */
    private String videoId;
    /**
     * 语音地址
     */
    private String url;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 时长
     */
    private int duration;
    /**
     * 地理位置
     */
    private String geographic;
}
