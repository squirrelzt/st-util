package com.util.qrcode;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 类名称: QRCodeUtils
 * 类描述: 二维码工具类
 * @author squriiel
 * @date 2019-03-04
 */
public class QRCodeUtils {

    /**
     * 生成二维码
     * @param qrData 生成二维码中要存储的信息
     * @param path 二维码图片存储路径
     * @return
     */
    public static boolean creatQrcode(String qrData, String path) {
        try {
            Qrcode qrcode = new Qrcode();
            // 纠错等级（分为L、M、H三个等级）
            qrcode.setQrcodeErrorCorrect('M');
            // N代表数字，A代表a-Z，B代表其它字符
            qrcode.setQrcodeEncodeMode('B');
            // 版本
            qrcode.setQrcodeVersion(7);
            // 设置一下二维码的像素
            int width = 67 + 12 * (7 - 1);
            int height = 67 + 12 * (7 - 1);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 绘图
            Graphics2D gs = bufferedImage.createGraphics();
            gs.setBackground(Color.WHITE);
            gs.setColor(Color.BLACK);
            // 清除画板内容
            gs.clearRect(0, 0 , width, height);
            // 设置下偏移量,如果不加偏移量，有时会导致出错
            int pixoff = 2;
            byte[] bytes = qrData.getBytes("utf-8");
            if (bytes.length > 0 && bytes.length < 120) {
                boolean[][] s = qrcode.calQrcode(bytes);
                for (int i = 0; i < s.length; i++) {
                    for (int j = 0; j < s.length; j++) {
                        if (s[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            }
            gs.dispose();
            bufferedImage.flush();
            ImageIO.write(bufferedImage, "png", new File(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解析二维码 (QRCode)
     * @param imgPath 图片路径
     * @return
     */
    public static String decoderQRCode(String imgPath) {
        // QRCode 二维码图片的文件
        File imageFile = new File(imgPath);
        BufferedImage bufferedImage = null;
        String content = null;
        try {
            bufferedImage = ImageIO.read(imageFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufferedImage)), "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


}

class TwoDimensionCodeImage implements QRCodeImage {
    /**
     * BufferedImage作用将一幅图片加载到内存中
     */
    BufferedImage bufferedImage;

    public TwoDimensionCodeImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public int getPixel(int i, int i1) {
        return bufferedImage.getRGB(i, i1);
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "src/main/resources/QRCode/myQRCode.png";
        QRCodeUtils.creatQrcode("http://www.baidu.com", path);
        String content = QRCodeUtils.decoderQRCode(path);
        System.out.println(content);
    }
}