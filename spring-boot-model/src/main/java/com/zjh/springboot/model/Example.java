package com.zjh.springboot.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjh
 */
@RestController
@EnableAutoConfiguration
public class Example {

    @GetMapping("/example")
    public String example() {
        return RandomStringUtils.random(RandomUtils.nextInt(1, 10), true, true);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
