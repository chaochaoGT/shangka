package com.geek.shengka.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.constant.CommonConstant;
import com.geek.shengka.backend.entity.SkVoice;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.backend.mapper.SkVoiceDAO;
import com.geek.shengka.backend.params.req.SkContentVoice;
import com.geek.shengka.backend.params.req.SkVoicePublishReqParam;
import com.geek.shengka.backend.params.res.SkVoiceListResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.service.SkVoiceService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.CommonUtil;
import com.geek.shengka.backend.util.OSSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/20 17:06
 */
@Service
@Slf4j
public class SkVoiceServiceImpl implements SkVoiceService {

    @Autowired
    private SkVoiceDAO skVoiceDAO;
    @Autowired
    private SkContentCenterService skContentCenterService;
    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;

    /**
     * 发布语音
     *
     * @param body
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:11 2019/8/20
     */
    @Override
    public Boolean publish(List<SkVoicePublishReqParam> body) throws BaseException {
        if (CollectionUtils.isEmpty(body)) {
            return false;
        }
        //校验用户是否正常
        Set<Long> userIds = body.stream().map(SkVoicePublishReqParam::getUserId).collect(Collectors.toSet());
        List<Long> existIds = skUserBaseInfoDAO.selectExistIds(userIds);
        if (existIds == null || userIds.size() != existIds.size()) {
            List<Long> notExistIds = userIds.stream().filter(id -> !existIds.contains(id)).collect(Collectors.toList());
            throw new BaseException("用户ID不存在：" + JSONObject.toJSONString(notExistIds));
        }

        String videoId = body.get(0).getVideoId();
        //删除系统上传的历史数据
        skVoiceDAO.deleteByVideoIdOfPgc(videoId);
        //按照顺序，创建时间递增
        int size = body.size();
        for (int i = 0; i < size; i++) {
            body.get(i).setCreateTime(new Date(System.currentTimeMillis() + i * 100));
        }
        //遍历进行操作
        body.parallelStream().forEach(voice -> {
            SkVoice skVoice = BeanMapperUtils.map(voice, SkVoice.class);
            skVoice.setId(UUID.randomUUID().toString().replace("-", ""));
            skVoice.setResource((byte) 1);
            skVoiceDAO.insertSelective(skVoice);

            //维护用户的发音数量加1
            skUserBaseInfoDAO.updateVoiceNumPlusOne(skVoice.getUserId());

            //调用内容审核中心
            SkContentVoice contentVoice = BeanMapperUtils.map(skVoice, SkContentVoice.class);
            contentVoice.setAudioId(skVoice.getId());
            contentVoice.setUrl(skVoice.getVoiceUrl());
            try {
                skContentCenterService.uploadVoice(contentVoice);
            } catch (BaseException e) {
                log.error("内容中心上传音频异常：{}", e.getMessage());
            }
        });

        return true;
    }

    /**
     * 通过视频ID查询所有的系统导入的语音列表
     *
     * @param videoId
     * @return java.lang.Object
     * @author qubianzhong
     * @Date 14:52 2019/8/21
     */
    @Override
    public List<SkVoiceListResParam> listByVideoIdOfPgc(String videoId) {
        List<SkVoiceListResParam> list = skVoiceDAO.listByVideoIdOfPgc(videoId);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(element -> element.setVoiceUrl(CommonUtil.getFullPath(element.getVoiceUrl())));
        }
        return list;
    }
}
