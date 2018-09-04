package com.github.jedis.support;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyCommands;
import redis.clients.jedis.ScriptingCommands;

/**
 * @author Aaron
 *
 * redis存取string数据
 */
public interface JedisCmd extends JedisCommands, MultiKeyCommands, ScriptingCommands {

}
