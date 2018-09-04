package com.github.springboot;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjh
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return RandomStringUtils.random(RandomUtils.nextInt(1, 10), true, true);
    }
}
