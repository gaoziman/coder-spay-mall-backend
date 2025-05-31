package org.leocoder.spaymall.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leocoder.mall.domain.req.ShopCartReq;
import org.leocoder.mall.domain.resp.PayOrderRes;
import org.leocoder.mall.service.IOrderService;
import org.leocoder.web.MainApplication;
import com.alibaba.fastjson.JSON;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class ApiTest {


    @Resource
    private IOrderService orderService;


    /**
     * 用于测试: test
     */
    @Test
    public void test01() {
        System.out.println("ApiTest.test01");
    }


    @Test
    public void test_createOrder() throws Exception {
        ShopCartReq shopCartReq = new ShopCartReq();
        shopCartReq.setUserId("leocoder");
        shopCartReq.setProductId("10001");
        PayOrderRes payOrderRes = orderService.createProductOrder(shopCartReq);
        log.info("请求参数:{}", JSON.toJSONString(shopCartReq));
        log.info("测试结果:{}", JSON.toJSONString(payOrderRes));
    }

}
