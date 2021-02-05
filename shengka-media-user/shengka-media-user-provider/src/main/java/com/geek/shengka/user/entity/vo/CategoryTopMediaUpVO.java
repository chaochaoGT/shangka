package com.geek.shengka.user.entity.vo;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import lombok.Data;

import java.util.List;

/**
 * @Filename: CategoryTopMediaUp
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */
@Data
public class CategoryTopMediaUpVO {
    private List<BaseMediaInfo> topMedia;
    private List<SkRecommendUserConfigVO> upList;
}
