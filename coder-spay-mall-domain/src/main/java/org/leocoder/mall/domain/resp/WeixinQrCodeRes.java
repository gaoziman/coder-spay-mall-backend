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

    /* 二维码ticket */
    private String ticket;

    /* 二维码图片地址 */
    private Long expire_seconds;

    /* 二维码图片地址 */
    private String url;

}
