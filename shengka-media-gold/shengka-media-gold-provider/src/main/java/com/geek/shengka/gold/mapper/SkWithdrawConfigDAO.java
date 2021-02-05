package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkWithdrawConfig;
import com.geek.shengka.gold.response.UserAccountWithDraw;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * SkWithdrawConfigDAO继承基类
 */
@Repository
public interface SkWithdrawConfigDAO extends MyBatisBaseDao<SkWithdrawConfig, Long> {
	
    /** 获取常规配置提现信息 **/
	List<SkWithdrawConfig>  selectByWithDrawRule();
	
	List<UserAccountWithDraw> showActivityWithdraws(Long userId);
	
	
}