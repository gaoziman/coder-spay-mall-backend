package org.leocoder.mall.common.enums;


import org.leocoder.mall.common.convention.errorcode.IErrorCode;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-23 11:37
 * @description :
 */
public enum HttpCodeEnum implements IErrorCode {
    Ticket_Generate_Error("A000200", "生成微信扫码登录 ticket 失败"),

    Login_Check_Error("A000201", "登录校验失败"),

    Not_Login("A000202", "未登录"),
    ;

    private final String code;

    private final String message;

    HttpCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
