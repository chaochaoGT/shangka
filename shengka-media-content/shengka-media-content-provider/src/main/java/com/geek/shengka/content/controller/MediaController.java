package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.ContentBackRequest;
import com.geek.shengka.content.request.ContentDeleteVideoRequest;
import com.geek.shengka.content.request.PublishMediaRequest;
import com.geek.shengka.content.request.ContentMediaRequest;
import com.geek.shengka.content.response.MediaResponse;
import com.geek.shengka.content.service.MediaService;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频
 * @author xuxuelei
 *
 */
@RestController
@RequestMapping("/v1/app/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;
     
    /**
     * 视频内容分页获取（内容中心-过滤被举报）
     * @param param
     * @return
     */
    @PostMapping("/getRemoteVideos")
    @IgnoreClientToken
    public BaseResponse getRemoteVideos(@Valid @RequestBody ContentMediaRequest param) {
        String version = ContextTools.getRequest().getHeader("version");
    	if(0==param.getPageNumber()||StringUtils.isBlank(version)) {
    		return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
    	}
        return BaseResponse.newSuccess(mediaService.getRemoteVideos(param));
    }
    
    
    
   /**
    * 视频详情接口（内容中心-过滤被举报）
    * @param param
    * @return
    */
   @GetMapping("/getRemoteVideoDetail")
   @IgnoreClientToken
   public BaseResponse getRemoteVideoDetail(String videoId) {
    	if(StringUtils.isBlank(videoId)) {
   		return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
   	}
       return BaseResponse.newSuccess(mediaService.getRemoteVideoDetail(videoId));
   } 

    
    
    /**
	   * 视频发布
	* @param param
	* @return
	*/
	@PostMapping("/publishMedia")
	public BaseResponse publishMedia(@RequestBody @Valid PublishMediaRequest param) {
        long userId = UserContextHolder.getUserIdByHeader();
        String version = ContextTools.getRequest().getHeader("version");
        if (userId <= 0||StringUtils.isBlank(version)) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
		return BaseResponse.newSuccess(mediaService.publishMedia(param));
	}
	
    /**
             * 视频内容删除
     * @param param
     * @return
     */
    @GetMapping("/deleteRemoteVideos")
    public BaseResponse deleteRemoteVideos(String videoId) {
    	String version = ContextTools.getRequest().getHeader("version");
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0||StringUtils.isBlank(videoId)||StringUtils.isBlank(version)) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(mediaService.delVideo(videoId));
    }
	
	
    /**
	   * 内容中心同步视频信息
	* @param param
	* @return
	*/
	@PostMapping("/syncMeidaInfo")
    @IgnoreClientToken
	public BaseResponse syncMeidaInfo(@RequestBody @Valid ContentBackRequest param) {
		return BaseResponse.newSuccess(mediaService.syncMeidaInfo(param));
	}

	
 
 
}
