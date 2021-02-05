package com.geek.shengka.user.entity.vo;

import com.geek.shengka.user.entity.SkRecommendUserConfig;
import lombok.Data;

/**
 * @Filename: SkRecommendUserConfigVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/12 ;
 */
@Data
public class SkRecommendUserConfigVO extends SkRecommendUserConfig {
    /**是否关注 1 已关注 0未关注 */
    private int attenttionFlag;


}
