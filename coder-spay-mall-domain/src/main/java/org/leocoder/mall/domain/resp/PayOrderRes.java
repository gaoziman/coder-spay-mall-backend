package org.leocoder.mall.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leocoder.mall.common.enums.OrderStatusEnum;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:36
 * @description :  支付订单返回结果
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRes {

    private String userId;

    private String orderId;

    private String payUrl;

    private OrderStatusEnum orderStatusEnum;

}

