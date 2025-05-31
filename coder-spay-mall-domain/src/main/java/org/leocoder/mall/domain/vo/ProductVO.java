package org.leocoder.mall.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:47
 * @description : 商品VO
 */
@Data
public class ProductVO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 商品价格
     */
    private BigDecimal price;

}
