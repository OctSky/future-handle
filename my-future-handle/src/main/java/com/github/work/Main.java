package com.github.work;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

        //        System.out.println(BitCalculationUtil.sum(1,3));
        Instant now = Instant.now();
        ZonedDateTime zoneTime= now.atZone(ZoneId.systemDefault());
        System.out.println(zoneTime);
        System.out.println(new Date());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate);
        System.out.println(nowDate.format(formatter));

        Random random = new Random(100);
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    }
}
