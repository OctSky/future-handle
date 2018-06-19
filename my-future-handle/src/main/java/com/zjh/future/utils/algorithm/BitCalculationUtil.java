package com.zjh.future.utils.algorithm;

/**
 * Created by 俊虎 on 2018/1/20.
 *
 * 位运算实现的工具
 */
public class BitCalculationUtil {
    public static int sum(int a, int b) {
        if (b == 0) return a; //无进位
        int bitSum = a ^ b;//当前位取值
        int carry = (a & b) << 1;//进位取值
        return sum(bitSum, carry);
    }
}
