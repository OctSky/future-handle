package com.zjh.future;

import com.zjh.future.utils.algorithm.BitCalculationUtil;

/**
 * @author zhangjh
 */
public class Main {
    public static void main(String[] args) throws Throwable {
//        //二维码编码
//        String content = "曾经，有一份真挚的爱摆在我面前，我却没有珍惜，等到失去的时候才后悔莫及。如果上天能再给我一次机会的话，我要对那个女孩子说三个字：" +
//                "\"我爱你\"！如果非要为这份爱加一个期限的话，我希望是一辈子！";
//        String filePath = "C:/Users/zhangjh/Desktop/test.png";
//        QRCodeUtil.encodeToFile(content, filePath, "png", 300, 300);
//
//        //二维码解码
//        String str = QRCodeUtil.decodeFromFile(filePath);
//        System.out.println("二维码解码：" + str);

        System.out.println(BitCalculationUtil.sum(1,3));
    }
}
