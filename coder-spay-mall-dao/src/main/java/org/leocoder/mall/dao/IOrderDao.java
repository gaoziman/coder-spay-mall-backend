package org.leocoder.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.leocoder.mall.domain.po.PayOrder;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:39
 * @description :
 */
@Mapper
public interface IOrderDao {

    /**
     * 插入订单
     *
     * @param payOrder 订单
     */
    void insert(PayOrder payOrder);


    /**
     * 查询未支付的订单
     *
     * @param payOrder 订单
     * @return 未支付的订单
     */
    PayOrder queryUnPayOrder(PayOrder payOrder);

}