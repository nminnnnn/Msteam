package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

public class ImageDecoder {
    
    public static ImageIcon decodeByteArrayToImageIcon(byte[] imageBytes) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            return new ImageIcon(bufferedImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
}
