package com.geek.shengka.common.constant;

/**
 * 视频统计总数key设置
 * 存储于redis
 * @author jiangxinqiang
 */
public class SummaryKey {

	////总数  传入 watchMode
	public static final String SUMMARY_KEY_TOTAL = "summary_key_total_watchmode_%s";// 全部  watchMode
	//分类总数  传入watchMode+  一级分类ID 
	public static final String SUMMARY_KEY_CATEGORY = "summary_key_watchmode_%s_category_%s";// 用  watchMode, 具体category id占位替代
	
	
	//页码总数  传入 watchMode  每页大小值
	public static final String SUMMARY_KEY_TOTAL_PAGE = "summary_key_total_watchmode_%s_pagesize_%s";//   watchMode, pageSize

	//分类页码总数  传入watchMode+  一级分类ID + 每页大小值
	public static final String SUMMARY_KEY_CATEGORY_PAGE = "summary_key_watchmode%s_category_%s_pagesize_%s";// watchMode,  category id  , pageSize
	
	
	
	

}
