package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkCommentNotice;
import org.springframework.stereotype.Repository;

/**
 * SkCommentNoticeDAO继承基类
 */
@Repository
public interface SkCommentNoticeDAO extends MyBatisBaseDao<SkCommentNotice, Long> {
}