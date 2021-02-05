package com.geek.shengka.backend.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 *
 * @author luoyong
 * @date 2019/4/1 11:56
 */
@Data
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -5873568801050286194L;
    /**
     * 分页参数
     */
    private PageInfo page;

    /**
     * 本页数据集合
     */
    private List<T> result;

    class PageInfo implements Serializable {
        private static final long serialVersionUID = 3448625074713054182L;
        /**
         * 总页数
         */
        private Integer totalPage;
        /**
         * 总条数
         */
        private Long totalCount;

        /**
         * 当前页码
         */
        private Integer currentPage;

        /**
         * 每页大小
         */
        private Integer pageSize;

        public Integer getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Long getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Long totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }


}
