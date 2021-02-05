package com.geek.shengka.common.basemodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseHeadersRequest extends PageRequest {
    //用户id
    private Long userId;
    //APP版本，如1.3.2
    private String version;
    //设备uuid
    private String uuid;
    //设备号imei
    private String imei;
    //请求时间
    private String timestamp;
}
