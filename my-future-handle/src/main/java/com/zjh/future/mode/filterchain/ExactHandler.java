package com.zjh.future.mode.filterchain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 职责节点
 *
 * @author zhangjh
 */
@Slf4j
@Component("exactHandler")
@DependsOn("exactFilter") //在filter后实例化
public class ExactHandler implements Command {
    public boolean execute(Context context) throws Exception {
        /**
         * 功能节点自身处理请求的逻辑
         * 返回false则不再向后继节点传递
         */
        ChainContext chainContext = (ChainContext) context;
        int order = chainContext.getOrder();
        if (order == 0) {
            log.info("======= passed to next,context:{}", chainContext);
        }
        String str = RandomStringUtils.random(6, true, true);
        chainContext.setOutStr(str);
        chainContext.setOrder(++order);
        log.info("======= handled in second,context:{}", chainContext);
        return true;
    }
}
