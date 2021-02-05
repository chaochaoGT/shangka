package com.geek.shengka.common.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geek.shengka.common.constant.SummaryKey;

/**
 * 视频数据按分类统计
 * 
 * @author jiangxinqiang
 *
 */
public class MediaSummary {
	private static final Logger logger = LoggerFactory.getLogger(MediaSummary.class);
	
	//默认每页大小
	private static final int DEFAULT_PAGE_SIZE = 20;

	/** 总数 **/
	public static Long total(Integer watchMode) {
		Long ret = 50000L;//兜底默认值
		try {
			String value =  CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_TOTAL, watchMode) );
			if(value != null) {
				ret = Long.parseLong(value);
			}
		} catch (Exception e) {
			//兜底默认值
			ret = 50000L;
			logger.error(e.getMessage(), e);
		}

		//logger.info("[get-media-summary][watchMode="+watchMode+"][total="+ret+"]");
		return ret;
	}
	
	
	/** 总页数 **/
	public static Long totalPage(Integer watchMode, Integer pageSize) {
		Long ret = null;
		try {
			String totalPage = CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_TOTAL_PAGE, watchMode,  pageSize));
			if(StringUtils.isEmpty(totalPage)) {
				totalPage = CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_TOTAL_PAGE, watchMode,  DEFAULT_PAGE_SIZE));
			}
			ret = Long.parseLong(totalPage);
		} catch (Exception e) {
			//兜底默认值
			ret = 500L;
			logger.error(e.getMessage(), e);
		}

		//logger.info("[get-media-summary][watchMode="+watchMode+"][pageSize="+pageSize+"][totalPage="+ret+"]");
		//留buffer ，防止末尾页剩下数据很少
		return ret > 5 ? ret - 5 : ret;
	}
	
	
	/** 一级分类总页数 **/
	public static Long totalCategoryPage(Integer watchMode, String category, Integer pageSize) {
		Long ret = null;
		try {
			String totalPage = CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_CATEGORY_PAGE, watchMode, category, pageSize));
			if(StringUtils.isEmpty(totalPage)) {
				totalPage = CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_CATEGORY_PAGE, watchMode , category, DEFAULT_PAGE_SIZE));
			}
			ret = Long.parseLong(totalPage);
		} catch (Exception e) {
			//兜底默认值
			ret = 500L;
			logger.error(e.getMessage(), e);
		}

		//logger.info("[get-media-summary][watchMode="+watchMode+"][category="+category+"][pageSize="+pageSize+"][totalCategoryPage="+ret+"]");
		//留buffer ，防止末尾页剩下数据很少
		return ret > 5 ? ret - 5 : ret;
	}
	
	
	
	

	/** 一级分类 总数 **/
	public static long totalCategory(Integer watchMode, String category) {
		Long ret = null;
		try {
			if (StringUtils.isNotBlank(category)) {
				ret = Long.parseLong(CacheProvider.get(String.format(SummaryKey.SUMMARY_KEY_CATEGORY, watchMode, category.trim())));
			}
		} catch (Exception e) {
			//兜底默认值
			ret = 30000L;
			logger.error(e.getMessage(), e);
		}
		//logger.info("[get-media-summary][watchMode="+watchMode+"][category="+category+"][cts="+ret+"]");
		return ret;
	}

}
