package com.geek.shengka.backend.util;

import com.geek.shengka.backend.params.req.BasePageReqParam;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页工具类
 *
 * @author luoyong
 * @date 2019/4/1 11:56
 */
public final class PageDataUtils {

    private PageDataUtils() {
    }

    /**
     * 封装分页数据
     *
     * @param page 查询分页
     * @param list 查询列表
     * @return 分页数据
     */
    public static <T> PageVO<T> pageData(Page<T> page, List<T> list) {
        PageVO<T> pageData = new PageVO();
        if (page != null) {
            PageVO.PageInfo pageInfo = pageData.new PageInfo();
            pageInfo.setTotalCount(page.getTotal());
            pageInfo.setCurrentPage(page.getPageNum());
            pageInfo.setTotalPage(page.getPages());
            pageInfo.setPageSize(page.getPageSize());
            pageData.setPage(pageInfo);
        }
        pageData.setResult(list);
        return pageData;
    }


    public static <T> PageVO<T> normalData(long total, BasePageReqParam basePageReqParam, List<T> list) {
        int pageSize = basePageReqParam.getPageSize();
        int pageNum = basePageReqParam.getPageNo();
        int pages = (int) (total / pageSize);
        if (total % pageSize != 0) {
            pages++;
        }
        PageVO<T> pageData = new PageVO();
        PageVO.PageInfo pageInfo = pageData.new PageInfo();
        pageInfo.setTotalCount(total);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setTotalPage(pages);
        pageInfo.setPageSize(pageSize);
        pageData.setPage(pageInfo);
        pageData.setResult(list);
        return pageData;
    }

}
