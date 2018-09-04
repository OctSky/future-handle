package com.github.work.test;

import com.github.work.mode.filterchain.ChainUserExample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangjh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FilterChainTest {
    @Autowired
    private ChainUserExample example;

    @Test
    public void exampleTest() throws Exception {
        example.example();
    }
}
