package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.PublishVoice;
import com.geek.shengka.content.request.VoiceAttend;
import com.geek.shengka.content.response.VoiceInfo;
import com.geek.shengka.content.request.ContentDeleteVoiceRequest;
import com.geek.shengka.content.request.ContentVoiceRequest;
import com.geek.shengka.content.service.VoiceService;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 语音
 * 
 * @author xuxuelei
 *
 */
@RestController
@RequestMapping("/v1/app/voice")
public class VoiceController {

	@Autowired
	private VoiceService voiceService;

	/**
	 * 根据视频id获取语音分页（内容中心-过滤被举报）
	 * 
	 * @param param
	 * @return
	 */
	@IgnoreClientToken
	@PostMapping("/getRemoteVoices")
	public BaseResponse<List<VoiceInfo>> getRemoteVoices(@RequestBody @Valid ContentVoiceRequest param) {
		return BaseResponse.newSuccess(voiceService.getRemoteVoices(param));
	}

	/**
	 * 发布语音
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/publishVoice")
	public BaseResponse publishVoice(@RequestBody @Valid PublishVoice param) {
        String version = ContextTools.getRequest().getHeader("version");
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0||StringUtils.isBlank(version)) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
		return BaseResponse.newSuccess(voiceService.publishVoice(param));
	}

	/**
	 * 语音删除
	 * 
	 * @param param
	 * @return
	 */
	@GetMapping("/delVoice")
	public BaseResponse delVoice(String voiceId) {
        String version = ContextTools.getRequest().getHeader("version");
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0||StringUtils.isBlank(voiceId)||StringUtils.isBlank(version)) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
		return BaseResponse.newSuccess(voiceService.delVoice(voiceId));
	}

	/**
	 * 语音@好友
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/attentionFriends")
	public BaseResponse attentionFriends(@RequestBody @Valid VoiceAttend param) {
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
		return BaseResponse.newSuccess(voiceService.attentionFriends(param));
	}

}
