package com.cy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description: 启动类
 * @date 2021-12-20 21:35:45
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan(basePackages = "com.cy.*.*.mapper")
public class WuYeManagementProgram {
    public static void main(String[] args) {
        SpringApplication.run(WuYeManagementProgram.class);
    }
}
