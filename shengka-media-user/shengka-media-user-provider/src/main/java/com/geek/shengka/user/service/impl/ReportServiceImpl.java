package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.entity.SkReport;
import com.geek.shengka.user.mapper.SkReportDAO;
import com.geek.shengka.user.request.UserReportRequest;
import com.geek.shengka.user.service.ReportService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 举报实现
 *
 * @author: xuxuelei
 * @create: 2019-08-07 09:29
 **/
@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private SkReportDAO skReportDAO;
	
	@Override
	public int reportUser(UserReportRequest param) {
		SkReport skReport = new SkReport();
		skReport.setUserId(UserContextHolder.getUserId());
		skReport.setReportUserId(param.getReportUserId());
		skReport.setReportType((byte)2);
		skReport.setReportContent(param.getReason());
		skReport.setCreateTime(new Date());
		
		return skReportDAO.insertSelective(skReport);
	}

  

 
}
