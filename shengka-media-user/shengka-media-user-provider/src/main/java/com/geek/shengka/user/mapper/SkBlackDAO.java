package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkBlack;
import com.geek.shengka.user.response.SkBlackResponse;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * SkBlackDAO继承基类
 */
@Repository
public interface SkBlackDAO extends MyBatisBaseDao<SkBlack, Long> {
	
	int deleteByBlack(SkBlack skBlack);
	
	List<SkBlack> selectList(SkBlack skBlack);
	
	List<SkBlackResponse> selectByUserList(SkBlack skBlack);
}