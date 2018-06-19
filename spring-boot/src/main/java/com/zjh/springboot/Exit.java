package com.zjh.springboot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjh
 */
@Slf4j
@Component
public class Exit implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        Random random = new Random(1);
        int exitCode = random.nextInt();
        log.info("=--------------------------- exit code:{}", exitCode);
        return exitCode;
    }
}
