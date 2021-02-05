package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.user.entity.SkFans;
import com.geek.shengka.user.entity.SkFansNotice;
import com.geek.shengka.user.entity.vo.SkFansNoticeVo;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.mapper.SkFansDAO;
import com.geek.shengka.user.mapper.SkFansNoticeDAO;
import com.geek.shengka.user.reponsecode.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户互粉
 *
 * @author: yunfei
 * @create: 2019-08-01 11:26
 **/

@Service
public class SkFansNoticeServiceImpl {

    @Autowired
    private SkFansNoticeDAO skFansNoticeDAO;

    @Autowired
    private SkFansDAO skFansDAO;
    
    @Autowired
    private SkUserInfoServiceImpl skUserInfoServiceImpl;
    
   // @Transactional(rollbackFor = Exception.class)
    public BaseResponse<List<SkFansNoticeVo>> noticeList(int pageIndex, int pageSize) {
    	BaseResponse<List<SkFansNoticeVo>> response =BaseResponse.newSuccess(null);
        String userId = BaseContextHandler.getCurrentUId();
        if(StringUtils.isBlank(userId)||Long.valueOf(userId)<=0L) {
    		response= BaseResponse.newFailure(ResponseCode.USER_ERROR);
    		return response;
        }
//    	PageHelper.startPage(pageIndex,pageSize);
        if(pageIndex<=0){
            pageIndex=1;
        }
        List<SkFansNoticeVo> fansNoticelist = skFansNoticeDAO.findFansListByUserId(Long.valueOf(userId),(pageIndex-1)*pageSize, pageSize);
        if (!CollectionUtils.isEmpty(fansNoticelist)){
            //我关注的用户
            List<String> aids = skFansDAO.selectMyFans(Long.valueOf(userId), fansNoticelist.stream().map(s -> {
                return String.valueOf(s.getUserId());
            }).collect(Collectors.toSet()));

            if(!CollectionUtils.isEmpty(aids)){
                fansNoticelist.forEach(f->{
                    //已关注
                    if (aids.contains(String.valueOf(f.getUserId()))){
                        f.setFansState(1);
                    }
                });
            }
        }
        response.setData(fansNoticelist);
        return response;
    }
    
    public void insertNotice(Long fansId) {
    	Assert.notNull(fansId,"fansId不能为空");
    	SkFans skFans =skFansDAO.selectByPrimaryKey(fansId);
    	if(skFans!=null) {
     		SkUserBaseInfoVO skUserBaseInfoVO=skUserInfoServiceImpl.baseInfo(skFans.getUserId());
     		if(skUserBaseInfoVO!=null) {
        		SkFansNotice skFansNotice=new SkFansNotice();
        		skFansNotice.setAttentionUserId(skFans.getAttentionUserId());
        		skFansNotice.setCreateTime(new Date());
        		skFansNotice.setFansState(skFans.getFansState());
        		skFansNotice.setNickName(skUserBaseInfoVO.getNickName());
        		skFansNotice.setNoticeContent("关注了你");
        		skFansNotice.setNoticeState(skFans.getFansState());
        		skFansNotice.setUpdateTime(skFansNotice.getCreateTime());
        		skFansNotice.setUserIcon(skUserBaseInfoVO.getUserIcon());
        		skFansNotice.setUserId(skUserBaseInfoVO.getUserId());
        		skFansNoticeDAO.insertSelective(skFansNotice);
     		}
    	}
    	
    }
     

}
