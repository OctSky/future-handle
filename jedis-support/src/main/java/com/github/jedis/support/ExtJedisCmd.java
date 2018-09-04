package com.github.jedis.support;

import java.util.Map;

/**
 * @author Aaron
 */
public interface ExtJedisCmd {
    /**
     * 批量设置多个key-value，并设置过期时间
     *
     * @param kvPairs       key-value键值对
     * @param expireSeconds 过期间隔，单位:秒
     */
    void mset(Map kvPairs, int expireSeconds);
}
