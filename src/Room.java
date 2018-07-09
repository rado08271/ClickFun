import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Room {
    private int width, height;
    private int tileDimensions;

    private int[][] map;

    public Room(String path){
        loadWorld(path);
    }

    /**
     *function reads file and sets the room info accordingly
     * sets width and height of room, dimension of a square tile
     * sets the map
     * @param path specifies tthe path to a text file
     */
    private void loadWorld(String path) {
        String file = loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = parseInt(tokens[0]);
        height = parseInt(tokens[1]);
        tileDimensions = parseInt(tokens[2]);
        map = new int[height][width];
        for(int y = 0; y<height; y++){
            for (int x = 0; x<width; x++){
                map[x][y] = parseInt(tokens[(x + y * width) + 3]);
            }
        }
    }

    /**
     *  Function loads file line by line a s stingBuilder...stackOverflow
     *  if is possible to do so load file else exit
     * @param path specifies tthe path to a text file
     * @return
     */
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**
     * funtion gives a nuber if possible and it is the correct format if not correct
     * format return 0
     * @param number index where to save the value
     * @return number or 0 if is not an integer
     */
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileDimensions() {
        return tileDimensions;
    }

    public int[][] getMap() {
        return map;
    }
}
