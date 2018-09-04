package com.github.jedis.support;

import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.BinaryScriptingCommands;
import redis.clients.jedis.MultiKeyBinaryCommands;

/**
 * @author Aaron
 *
 * redis存取二进制数据
 */
public interface BinaryJedisCmd extends BinaryJedisCommands, MultiKeyBinaryCommands, BinaryScriptingCommands {

}
