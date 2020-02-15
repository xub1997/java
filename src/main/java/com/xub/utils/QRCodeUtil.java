package com.xub.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;


public class QRCodeUtil {

    /**
     * 图片的格式
     */
    private static final String QRCODE_SUFFIX = "png";

    public static void createQRCode(String filePath, String content) {
        int width = 300;            //图片的宽度
        int height = 300;            //图片的高度


        /**
         * 定义二维码的参数
         */
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    //指定字符编码为“utf-8”
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  //指定二维码的纠错等级为中级
        hints.put(EncodeHintType.MARGIN, 2);    //设置图片的边距

        /**
         * 生成二维码
         */
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(filePath).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, QRCODE_SUFFIX, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getContentFromQRCode(String filePath) {
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File(filePath);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer
                    (new BufferedImageLuminanceSource(image)));
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    //指定字符编码为“utf-8”
            Result result = formatReader.decode(binaryBitmap, hints);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
