package com.zjh.future;

import java.util.Comparator;

/**
 * Created by zhangjh on 2017/11/21.
 */
public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (x, y) -> y.compareTo(x);
        int value = comparator.compare(1, 2);
        System.out.println(value);
    }
}
