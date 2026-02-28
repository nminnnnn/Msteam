package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import view.MainUI;

public class ImageEncoder {

    // Phương thức chọn ảnh từ file và trả về byte[]
    public static byte[] encodeImageToBytes(JLabel label) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(350, 350, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                
                return bufferedImageToByteArray(image, "png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Phương thức chuyển ImageIcon thành byte[]
    public static byte[] encodeImageToBytes(ImageIcon imageIcon) {
        try {
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = toBufferedImage(image);
            
            return bufferedImageToByteArray(bufferedImage, "png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Chuyển BufferedImage thành byte[]
    private static byte[] bufferedImageToByteArray(BufferedImage image, String format) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, format, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Chuyển Image thành BufferedImage
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
            img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
