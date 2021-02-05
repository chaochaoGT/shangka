package com.geek.shengka.gold.api;

import com.geek.shengka.common.basemodel.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 金币配置 ：利用占位符避免未来整合硬编码
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@FeignClient(name = "${gold.service.name}")
public interface ITreasureBoxApi {

    /**
     * 宝箱配置 返回剩余次数
     *
     * @return
     */
    @GetMapping("/v1/treasureBox/config")
    public BaseResponse config();

}
