package org.leocoder.mall.domain.resp;

import lombok.Data;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-30 21:41
 * @description : 获取微信登录二维码响应对象
 */
@Data
public class WeixinQrCodeRes {

    private String ticket;
    private Long expire_seconds;
    private String url;

}
