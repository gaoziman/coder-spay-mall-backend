package org.leocoder.mall.service.rpc;

import org.leocoder.mall.domain.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-31 19:47
 * @description : 模拟商品RPC接口
 */
@Service
public class ProductRPC {

    public ProductVO queryProductByProductId(String productId) {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(productId);
        productVO.setProductName("测试商品");
        productVO.setProductDesc("这是一个测试商品");
        productVO.setPrice(new BigDecimal("1.68"));
        return productVO;
    }

}
