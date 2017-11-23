package com.zjh.future.mode.filterchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjh
 */
@Slf4j
@Service
public class ChainUserExample {
    @Autowired
    private FilterChain chain;

    public void example() throws Exception {
        ChainContext context = new ChainContext();
        chain.execute(context);
    }
}
