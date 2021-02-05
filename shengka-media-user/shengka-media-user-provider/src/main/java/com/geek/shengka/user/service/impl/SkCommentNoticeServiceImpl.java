package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.user.entity.vo.SkCommentNoticeVo;
import com.geek.shengka.user.mapper.SkCommentNoticeDAO;
import com.geek.shengka.user.reponsecode.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户互粉
 *
 * @author: yunfei
 * @create: 2019-08-01 11:26
 **/

@Service
public class SkCommentNoticeServiceImpl {

    @Autowired
    private SkCommentNoticeDAO skCommentNoticeDAO;
    
    public BaseResponse<List<SkCommentNoticeVo>> noticeList(int pageIndex, int pageSize) {
    	BaseResponse<List<SkCommentNoticeVo>> response =BaseResponse.newSuccess(null);
        String userId = BaseContextHandler.getCurrentUId();
        if(StringUtils.isBlank(userId)||Long.valueOf(userId)<=0L) {
    		response= BaseResponse.newFailure(ResponseCode.USER_ERROR);
    		response.setTimestamp(System.currentTimeMillis());
    		return response;
        }
//    	PageHelper.startPage(pageIndex,pageSize);
        if(pageIndex<=0){
            pageIndex=1;
        }
        List<SkCommentNoticeVo> fansNoticelist = skCommentNoticeDAO.findCommentNoticesByUserId(Long.valueOf(userId),(pageIndex-1)*pageSize, pageSize);
        fansNoticelist.forEach(f->{
            f.setVideoUrl(CdnUrlUtils.transferCdn(f.getVideoUrl()));
            f.setVoiceUrl(CdnUrlUtils.transferCdn(f.getVoiceUrl()));

        });
        response.setData(fansNoticelist);
        return response;
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void delete(String ids) {
    	if(StringUtils.isNotBlank(ids)) {
    		List<Long> deleteIds=new ArrayList<Long>();
    		List<String> idList=Arrays.asList(ids.split(","));
    		for(String id:idList) {
    			deleteIds.add(Long.valueOf(id));
    		}
        	skCommentNoticeDAO.deleteInIds(deleteIds);
    	}
    }
    
}
