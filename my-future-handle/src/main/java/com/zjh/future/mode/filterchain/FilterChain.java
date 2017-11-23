package com.zjh.future.mode.filterchain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 职责链
 *
 * 应当被设计成多线程安全的，即标志执行节点的状态信息应当是本地的（local context）
 *
 * @author zhangjh
 */
@Component("filterChain")
public class FilterChain extends ChainBase {
    @Autowired
    public FilterChain(Collection<Command> chainNodes) {
        super(chainNodes);
    }
}
