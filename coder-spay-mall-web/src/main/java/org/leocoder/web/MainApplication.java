package org.leocoder.web;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-05-30 11:46
 * @description : 启动类
 */
@SpringBootApplication(scanBasePackages = {
        "org.leocoder.web",
        "org.leocoder.mall.service",
        "org.leocoder.mall.dao"
})
@Configurable
@ComponentScan("org.leocoder.mall.dao")
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        Environment environment = context.getBean(Environment.class);

        System.out.println("访问链接：http://localhost:" + environment.getProperty("server.port"));
        System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功 ლ(´ڡ`ლ)ﾞ \n");
    }
}

