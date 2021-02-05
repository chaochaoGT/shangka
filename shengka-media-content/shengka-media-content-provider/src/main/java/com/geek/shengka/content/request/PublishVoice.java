package com.geek.shengka.content.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * sk_voice
 * 
 * @author
 */
@Data
public class PublishVoice implements Serializable {

	private static final long serialVersionUID = -7031112929858628423L;

	/** 视频id	 */
    @NotNull
	private String videoId;
	/** * 语音地址 */
    @NotNull
	private String voiceUrl;
	/** * 播放时长（单位：秒） */
    @NotNull
	private Integer duration;
	/** 地理位置 **/
    @NotNull
	private String geographic;

}