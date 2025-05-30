package org.leocoder.mall.service;

import java.io.IOException;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-30 21:45
 * @description : 登录服务接口
 */
public interface ILoginService {
    /**
     * 创建ticket
     * @return 登录码凭证ticket
     */
    String createQrCodeTicket() throws IOException;

    /**
     * 校验是否登录
     * @param ticket 登录码凭证ticket
     * @return openid
     */
    String checkLogin(String ticket);


    /**
     * 保存登录状态
     * @param ticket 登录码凭证ticket
     * @param openid openid
     */
    void saveLoginState(String ticket, String openid) throws IOException;
}
