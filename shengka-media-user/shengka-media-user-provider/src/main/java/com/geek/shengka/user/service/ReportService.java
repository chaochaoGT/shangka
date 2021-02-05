package com.geek.shengka.user.service;

import com.geek.shengka.user.request.UserReportRequest;

/**
 * @author: xuxuelei
 * @create: 2019-08-07 09:29
 **/
public interface ReportService {

	/** 举报用户 **/
	int reportUser(UserReportRequest param);
 
}
