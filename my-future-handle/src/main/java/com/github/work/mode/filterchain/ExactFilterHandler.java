package com.github.work.mode.filterchain;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 带拦截器性质的职责节点，在请求被处理完成或抵达职责链末尾后，拦截器的拦截方法会被按照与职责节点传递方向相反的顺序被调用
 *
 * @author zhangjh
 */
@Slf4j
@Component("exactFilter")
public class ExactFilterHandler implements Filter {
    public boolean postprocess(Context context, Exception exception) {
        log.info("======== filter post process,context:{}", context);
        return true;
    }

    public boolean execute(Context context) throws Exception {
        /**
         * 功能节点自身处理请求的逻辑
         * 返回true则不再向后继节点传递
         */
        ChainContext chainContext = (ChainContext) context;
        int order = chainContext.getOrder();
        if (order == 1) {
            String str = RandomStringUtils.random(6, true, true);
            chainContext.setOutStr(str);
            order++;
            chainContext.setOrder(order);
            log.info("======= handled in second,context:{}", chainContext);
            return true;
        }
        log.info("======= passed to next,context:{}", chainContext);
        return false;
    }
}
