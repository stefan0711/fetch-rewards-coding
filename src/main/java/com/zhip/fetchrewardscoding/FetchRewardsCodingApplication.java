package com.zhip.fetchrewardscoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.zhip.fetchrewardscoding.controller"})
public class FetchRewardsCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FetchRewardsCodingApplication.class, args);
        System.out.println("=============> database init complete");
    }

}
