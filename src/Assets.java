import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {
    private ImageLoader image;
    private SpriteSheet sheet;
    private ArrayList<BufferedImage> tiles;
    private int tilesX,tilesY;

    public Assets(){
        image = new ImageLoader();
        tiles = new ArrayList<>();
        sheet = new SpriteSheet(image.loadImage("/assets/sheet.png"));
        tilesX= 4;
        tilesY= 1;
        makeList();
    }

    /**
     * Functions crops the file to images and saves it into an arraylist
     */
    private void makeList() {
        for (int i = 0; i < tilesY; i++) {
            for (int x = 0; x < tilesX; x++) {
                tiles.add(sheet.crop(x * 32, i * 32, 32, 32));
            }
        }
    }

    /**
     * Functions gets tile you clicked on
     * @param index index of a tile (color or style)
     * @return tile style as image
     */
    public BufferedImage getTile(int index){
        return tiles.get(index);
    }


}
