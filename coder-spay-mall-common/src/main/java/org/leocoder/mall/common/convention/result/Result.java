package org.leocoder.mall.common.convention.result;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : Leo
 * @version 1.0
 * @date 2025-05-29 11:19
 * @description : 全局统一返回对象
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5679018624509023747L;

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
