package com.github.work.mode.singleton;

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
