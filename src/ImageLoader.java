import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    /**
     * This functions loads an bufferedImage from file and gives its instance if
     * file was not found exit
     * @param path specifies tthe path to a file
     * @return if possible return an image otherwise stop if not possible or return null
     */
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
