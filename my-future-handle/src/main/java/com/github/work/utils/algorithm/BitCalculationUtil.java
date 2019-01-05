package com.github.work.utils.algorithm;

/**
 * @author Aaron
 *
 * 位运算实现的工具
 */
public class BitCalculationUtil {
    public static int sum(int a, int b) {
        //无进位
        if (b == 0) {
            return a;
        }
        //当前位取值
        int bitSum = a ^ b;
        //进位取值
        int carry = (a & b) << 1;
        return sum(bitSum, carry);
    }
}
