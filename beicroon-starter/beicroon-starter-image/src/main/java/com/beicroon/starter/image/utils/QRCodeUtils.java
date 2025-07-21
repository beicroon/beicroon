package com.beicroon.starter.image.utils;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.HashUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class QRCodeUtils {

    private QRCodeUtils() {

    }

    public static String createBase64QRCode(String content, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();

            hints.put(EncodeHintType.CHARACTER_SET, SystemConstant.CHARSET.name());

            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            ImageIO.write(image, "png", stream);

            return "data:image/png;base64," + HashUtils.base64Encode(stream.toByteArray());
        } catch (WriterException | IOException ex) {
            throw new RuntimeException("生成二维码失败", ex);
        }
    }

}
