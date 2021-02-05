package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCommentNotice;
import com.geek.shengka.user.entity.vo.SkCommentNoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SkCommentNoticeDAO继承基类
 */
public interface SkCommentNoticeDAO extends MyBatisBaseDao<SkCommentNotice, Long> {
	
	public List<SkCommentNoticeVo> findCommentNoticesByUserId(@Param("userId")Long userId,@Param("startRecordNumb")int startRecordNumb, @Param("pageCount")int pageSize);
	
	public void deleteInIds(List<Long> ids);
}