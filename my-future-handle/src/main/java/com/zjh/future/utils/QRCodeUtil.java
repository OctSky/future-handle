package com.zjh.future.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 二维码生成与解析工具
 *
 * @author zhangjh
 */
public class QRCodeUtil {
    private static QRCodeWriter codeWriter = new QRCodeWriter();

    /**
     * 将文本内容转化成二维码文件
     *
     * @param content    文本内容
     * @param file       目标文件全路径
     * @param fileFormat 文件格式，如png、jpeg等
     * @param width      二维码宽度
     * @param height     二维码高度
     * @throws WriterException,IOException 异常
     */
    public static void encodeToFile(String content, String file, String fileFormat, int width, int height) throws Exception {
        BitMatrix bitMatrix = encode2Bit(content, width, height);
        Path path = FileSystems.getDefault().getPath(file);
        int pointIndex = fileFormat.lastIndexOf(".");
        if (pointIndex != -1) {
            fileFormat = fileFormat.substring(pointIndex);
        }
        MatrixToImageWriter.writeToPath(bitMatrix, fileFormat, path);
    }

    /**
     * 将文本转换成二维码bit矩阵
     *
     * @param content 文本内容
     * @param width   宽度
     * @param height  高度
     * @return 比特矩阵
     * @throws Exception 异常
     */
    public static BitMatrix encode2Bit(String content, int width, int height) throws Exception {
        Map<EncodeHintType, Object> hintMap = new HashMap<>(1);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        return codeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
    }

    public static String decodeFromFile(String file) throws Exception {
        BufferedImage image = ImageIO.read(new File(file));
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Map<DecodeHintType, Object> hintMap = new HashMap<>(2);
        hintMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        QRCodeReader codeReader = new QRCodeReader();
        Result decodeResult = codeReader.decode(bitmap, hintMap);
        return decodeResult.getText();
    }
}
