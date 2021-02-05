package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkFansNotice;
import com.geek.shengka.user.entity.vo.SkFansNoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SkFansNoticeDAO继承基类
 */
public interface SkFansNoticeDAO extends MyBatisBaseDao<SkFansNotice, Long> {
	
	public List<SkFansNoticeVo> findFansListByUserId(@Param("userId")Long userId,@Param("startRecordNumb")int startRecordNumb, @Param("pageCount")int pageSize);
}