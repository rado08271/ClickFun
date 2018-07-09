import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
     * Function gives a cropped subimgae of sprite sheet
     * @param x where on x to start crop
     * @param y where on y to start crop
     * @param width where on x to stop crop
     * @param height where on y to stop crop
     * @return cropped image
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }

}
