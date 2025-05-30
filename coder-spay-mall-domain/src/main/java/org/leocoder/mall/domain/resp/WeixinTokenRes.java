package org.leocoder.mall.domain.resp;

import lombok.Data;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-30 21:42
 * @description : 获取 Access token DTO 对象
 */
@Data
public class WeixinTokenRes {

    private String access_token;
    private int expires_in;
    private String errcode;
    private String errmsg;

}
