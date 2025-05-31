package org.leocoder.mall.service;

import org.leocoder.mall.domain.req.ShopCartReq;
import org.leocoder.mall.domain.resp.PayOrderRes;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:36
 * @description : 订单服务接口
 */
public interface IOrderService {

    PayOrderRes createProductOrder(ShopCartReq shopCartReq) throws Exception;

}
