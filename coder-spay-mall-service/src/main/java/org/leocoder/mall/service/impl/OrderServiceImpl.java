package org.leocoder.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.leocoder.mall.common.enums.OrderStatusEnum;
import org.leocoder.mall.dao.IOrderDao;
import org.leocoder.mall.domain.po.PayOrder;
import org.leocoder.mall.domain.req.ShopCartReq;
import org.leocoder.mall.domain.resp.PayOrderRes;
import org.leocoder.mall.domain.vo.ProductVO;
import org.leocoder.mall.service.IOrderService;
import org.leocoder.mall.service.rpc.ProductRPC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:43
 * @description : 订单服务实现类
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDao orderDao;

    @Resource
    private ProductRPC productRPC;

    /**
     * 创建订单
     *
     * @param shopCartReq 购物车请求
     * @return PayOrderRes 支付订单响应
     * @throws Exception 异常
     */
    @Override
    public PayOrderRes createProductOrder(ShopCartReq shopCartReq) throws Exception {
        // 1. 查询当前用户是否存在未支付订单或掉单订单
        PayOrder payOrder = PayOrder.builder()
                .userId(shopCartReq.getUserId())
                .productId(shopCartReq.getProductId())
                .build();

        PayOrder unpaidOrder = orderDao.queryUnPayOrder(payOrder);

        if (null != unpaidOrder && OrderStatusEnum.PAY_WAIT.getCode().equals(unpaidOrder.getStatus())) {
            log.info("创建订单-存在，已存在未支付订单。userId:{} productId:{} orderId:{}", shopCartReq.getUserId(), shopCartReq.getProductId(), unpaidOrder.getOrderId());
            return PayOrderRes.builder()
                    .orderId(unpaidOrder.getOrderId())
                    .payUrl(unpaidOrder.getPayUrl())
                    .build();
        } else if (null != unpaidOrder && OrderStatusEnum.CREATE.getCode().equals(unpaidOrder.getStatus())) {
            // todo  订单已创建，等待支付
        }


        // 2. 查询商品 & 创建订单
        ProductVO productVO = productRPC.queryProductByProductId(shopCartReq.getProductId());
        String orderId = RandomStringUtils.randomNumeric(16);
        orderDao.insert(PayOrder.builder()
                .userId(shopCartReq.getUserId())
                .productId(shopCartReq.getProductId())
                .productName(productVO.getProductName())
                .orderId(orderId)
                .totalAmount(productVO.getPrice())
                .orderTime(new Date())
                .status(OrderStatusEnum.CREATE.getCode())
                .build());

        // 3. 创建支付单 todo 调用支付接口

        return PayOrderRes.builder()
                .orderId(orderId)
                .payUrl("暂无")
                .build();
    }
}
