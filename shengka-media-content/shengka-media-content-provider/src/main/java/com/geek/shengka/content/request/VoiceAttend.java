package com.geek.shengka.content.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VoiceAttend {

	/**视频id **/
    @NotNull
	private String videoId;
	/**语音id **/
    @NotNull
	private String voiceId;
	/**通知用户id **/
    @NotNull
	private String noticeUserIds;
 
}
