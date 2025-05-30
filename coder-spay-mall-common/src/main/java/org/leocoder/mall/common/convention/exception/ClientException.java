package org.leocoder.mall.common.convention.exception;


import org.leocoder.mall.common.convention.errorcode.BaseErrorCode;
import org.leocoder.mall.common.convention.errorcode.IErrorCode;

/**
 * @author : Leo
 * @version 1.0
 * @date 2025-05-29 11:20
 * @description : 客户端异常
 */
public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}