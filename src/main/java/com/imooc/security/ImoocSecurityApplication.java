package com.imooc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.management.monitor.GaugeMonitor;

@SpringBootApplication
@RestController
//启用swagger2
@EnableSwagger2
public class ImoocSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocSecurityApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security";
    }
}


//启动之后去访问localhost:8060/swagger-ui.html
// 会暴露所有端点,然后可以查看自己编写的所有控制器文档