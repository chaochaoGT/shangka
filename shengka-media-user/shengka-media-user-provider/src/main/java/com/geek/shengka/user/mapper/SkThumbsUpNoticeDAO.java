package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkThumbsUpNotice;
import com.geek.shengka.user.entity.vo.SkThumbsUpNoticeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SkThumbsUpNoticeDAO继承基类
 */
public interface SkThumbsUpNoticeDAO extends MyBatisBaseDao<SkThumbsUpNotice, Long> {
	
	public List<SkThumbsUpNoticeVO> findThumbsUpNoticeListByUserId(@Param("userId")Long userId,@Param("startRecordNumb")int startRecordNumb, @Param("pageCount")int pageSize);
}