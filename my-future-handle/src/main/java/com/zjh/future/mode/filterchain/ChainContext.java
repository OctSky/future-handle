package com.zjh.future.mode.filterchain;

import org.apache.commons.chain.impl.ContextBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用于维护职责链状态信息的上下文，比如存放一些在各职责节点间传递的公共参数
 *
 * @author zhangjh
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ChainContext extends ContextBase {
    /**
     * 此处定义的属性实际上会被父类克隆
     */
    private String outStr;
    private int order;
}
