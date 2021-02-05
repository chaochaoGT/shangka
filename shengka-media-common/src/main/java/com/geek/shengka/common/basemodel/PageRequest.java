package com.geek.shengka.common.basemodel;

import java.io.Serializable;



public class PageRequest  implements Serializable{
	private static final long serialVersionUID = 1L;
    
    protected int pageIndex;// 当前页数
    protected int pageCount;// 每页的数据条数
    protected int startRecordNumb;// 分页开始的数据条数

    public int getPageIndex() {
        if(pageIndex<=0){
            this.pageIndex =1;
        }
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;

    }

    public int getPageCount() {
        //设置每页最小数量为5
        if(pageCount <= 0){
            this.pageCount = 5;
        }
        //设置每页最大数量为30
        if(pageCount > 30){
            this.pageCount = 30;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount=pageCount;
    }

    public int getStartRecordNumb() {
        return (pageIndex-1)*pageCount;
    }

    public void setStartRecordNumb(int startRecordNumb) {
        this.startRecordNumb = startRecordNumb;
    }

}
