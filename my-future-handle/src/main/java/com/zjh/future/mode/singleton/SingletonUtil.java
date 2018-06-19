package com.zjh.future.mode.singleton;

/**
 * @author zhangjh
 */
public class SingletonUtil {

    public static Singleton getInstance() {
        return Singleton.INSTANCE;
    }

    private enum Singleton /**implements someInterface**/{
        INSTANCE
    }
}
