package org.leocoder.mall.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:39
 * @description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayOrder {

    private Long id;

    private String userId;

    private String productId;

    private String productName;

    private String orderId;

    private Date orderTime;

    private BigDecimal totalAmount;

    private String status;

    private String payUrl;

    private Date payTime;

    private Date createTime;

    private Date updateTime;

}

