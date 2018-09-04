package com.github.jedis.support;

import redis.clients.jedis.BinaryRedisPipeline;
import redis.clients.jedis.MultiKeyBinaryRedisPipeline;
import redis.clients.jedis.MultiKeyCommandsPipeline;
import redis.clients.jedis.RedisPipeline;

/**
 * @author Aaron
 */
public interface JedisPipelineCmd extends RedisPipeline, BinaryRedisPipeline, MultiKeyBinaryRedisPipeline, MultiKeyCommandsPipeline {

}
