package com.zjh.future.utils;

import java.awt.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 图片处理工具
 *
 * @author zhangjh
 */
public class ImageUtil {
    /**
     * 默认水印透明度
     */
    private static float alpha = 0.5F;
    /**
     * 默认水印横向位置
     */
    private static int xOffsetDefault = 150;
    /**
     * 默认水印纵向位置
     */
    private static int yOffsetDefault = 300;
    /**
     * 默认水印文字字体
     */
    private static Font fontDefault = new Font("行楷", Font.BOLD, 72);
    /**
     * 默认水印文字颜色
     */
    private static Color colorDefault = Color.red;

    @Setter
    @Getter
    @ToString
    public static final class WaterRemarkConfig {
        private float alpha;
        private int horizontalOffset;
        private int verticalOffset;
        private Font font;
        private Color color;

        private WaterRemarkConfig() {
        }

        public static WaterRemarkConfig getConfig(float alpha, int xOffset, int yOffset, Font font, Color color) {
            WaterRemarkConfig config = new WaterRemarkConfig();
            config.setAlpha(alpha);
            config.setHorizontalOffset(xOffset);
            config.setVerticalOffset(yOffset);
            config.setFont(font);
            config.setColor(color);
            return config;
        }
    }


    /**
     * 图片打水印
     *
     * @param backgroundImage 背景图片
     * @param waterImage      水印图片
     * @param tarImagePath    新生水印图片路径
     */
    public static void addImageWaterRemark(String backgroundImage, String waterImage, String tarImagePath) {

    }
}
