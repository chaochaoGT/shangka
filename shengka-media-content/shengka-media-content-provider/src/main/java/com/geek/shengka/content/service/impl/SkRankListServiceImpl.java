package com.geek.shengka.content.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.content.entity.SkRankList;
import com.geek.shengka.content.entity.vo.SkRankListVO;
import com.geek.shengka.content.mapper.SkRankListMapper;
import com.geek.shengka.content.service.SkRankListService;
import com.geek.shengka.content.utils.RedisUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qubianzhong
 * @date 2019/8/17 14:01
 */
@Service
public class SkRankListServiceImpl implements SkRankListService {

    @Autowired
    private SkRankListMapper skRankListMapper;
    @Autowired
    private RedisUtilService redisService;

    private static final String RANK_LIST_REDIS_KEY = "sk:rankList";

    /**
     * 榜单列表数据  不分页
     *
     * @return java.util.List<com.geek.shengka.content.entity.vo.SkRankList>
     * @author qubianzhong
     * @Date 14:09 2019/8/17
     */
    @Override
    public List<SkRankListVO> list() {
        String result = redisService.getString(RANK_LIST_REDIS_KEY);
        if (StringUtils.isEmpty(result)) {
            List<SkRankListVO> data = skRankListMapper.list();
            if (!CollectionUtils.isEmpty(data)) {
                //缓存1小时
                redisService.pushWithTime(RANK_LIST_REDIS_KEY, JSONObject.toJSONString(data), 1 * 60 * 60L, TimeUnit.SECONDS);
            }
            return data;
        }
        return JSONArray.parseArray(result, SkRankListVO.class);
    }
}
