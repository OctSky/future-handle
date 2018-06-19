package com.zjh.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjh
 */
@Slf4j
@SpringBootApplication
public class Example {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Example.class, args);
        log.info("==============Let's inspect the beans provided by Spring Boot:[{}]", Arrays.asList(context.getBeanDefinitionNames()));
        System.exit(SpringApplication.exit(context));
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) { //在SpringApplication启动后，run执行之前执行
        return arg -> log.info("==============Let's inspect the beans provided by Spring Boot:\n{}\n", Arrays.asList(context.getBeanDefinitionNames()).stream().collect(Collectors.joining("\n")));
    }
}
