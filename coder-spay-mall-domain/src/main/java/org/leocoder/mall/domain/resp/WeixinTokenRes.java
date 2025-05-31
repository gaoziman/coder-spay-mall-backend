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

    /* access_token 凭证 */
    private String access_token;


    /* 凭证有效时间，单位：秒 */
    private int expires_in;


    /* 错误码 */
    private String errcode;


    /* 错误信息 */
    private String errmsg;

}
