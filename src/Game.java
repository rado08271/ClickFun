import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private int[][] field;
    private int width,height,tile;
    private Display display;
    private boolean running;

    private BufferStrategy bs;
    private Graphics g;
    private Thread thread;
    private MouseInput mouseInput;
    private Room room;
    private Assets assets;

    int attempts;

    private Color[] colors = {Color.BLUE,Color.CYAN,Color.RED,Color.GREEN};

    private int colorSum;

    public Game(String path){
        room = new Room(path);
        this.width = room.getWidth();
        this.height = room.getHeight();
        this.tile = room.getTileDimensions();
        field = room.getMap();
        mouseInput = new MouseInput();
        colorSum = colors.length;
        attempts = 0;
    }

    /**
     * this function runs once the game starts (when thread is opened)
     */
    private void init(){
        display = new Display( (width*tile)+tile/2, (height*tile)+tile+8, "new");
        assets = new Assets();
        display.getFrame().addMouseListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
    }

    /**
     * function prints any given spritesheet as map
     */
    private void printToScreen(){
        for(int i = 0;i<height ;i++){
            for(int x = 0;x<width ;x++){
                g.drawImage(assets.getTile(field[i][x]),x*tile,i*tile,null);
//                g.setColor(colors[field[i][x]]);
//                g.fillRect(x*tile,i*tile,tile,tile);
            }
        }
    }

    /**
     * this fucntion chnges everything in a field it's a main game function
     * and takes care of a room changes all the tiles that the mouse is touching
     * @param xTile gives actual xTile you clicked on
     * @param yTile gives actual yTile you clicked on
     */
    //FIXME problem in some cases! try to resolve
    //doing it the stupid way...not efficient...meybe change later..
    private void changeNaighbours(int xTile,int yTile){
        int clickedTileValue = field[yTile][xTile];
        int yStart = 0;
        int yEnd = 0;

        //for each line find how many to change
        for (int y = 0; y < height; y++) {
            int xStart = xTile;
            int xEnd = xTile;

            //start x
            while(xStart>0){
                if(field[y][xStart] == clickedTileValue) xStart--;
                else break;
            }
            //end x
            while(xEnd<width){
                if(field[y][xEnd] == clickedTileValue) xEnd++;
                else break;
            }

            for (int x = xStart; x < xEnd; x++) {
                //if neighbour is same increse by one
                if(field[y][x] == clickedTileValue){
                    field[y][x]++;
                }
            }
        }

        //if you are ou of field bounds set to 0
        for(int i = 0;i<height ;i++){
            for(int x = 0;x<width ;x++){
                if(field[i][x] > colorSum - 1){
                    field[i][x] =  0;
                }
            }
        }
    }

    /**
     * if you click anywhere inside the window it gets what tile was clicked
     * if clicked elsewhere exit and returns 1
     */
    private void game(){
        int getXTile = mouseInput.x/room.getTileDimensions();
        int getYTile = mouseInput.y/room.getTileDimensions();

        try{
            changeNaighbours(getXTile,getYTile);
        } catch (IndexOutOfBoundsException e){
            System.out.print("Problem Occured! You Clicked elsewhere!");
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * function checks is all te tiles are the same color and the game is won if not return
     */
    public void isWon(){
        for(int i = 0;i<height ;i++){
            for(int x = 0;x<width-1 ;x++){
                if((field[i][x]%4) != (field[i][x+1])){
                    return;
                }
            }
        }
        System.out.println("CONGRATULATION \n ATTEMPTS: " + attempts );
        running = false;
    }

    //updates the game
    private void update(){
        if(mouseInput.isClicked == true) {
            game();
            mouseInput.isClicked = false;
            attempts++;
        }
    }

    //prints on scrren and clears the screen every tick
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        //draw

        printToScreen();

        //stop
        g.dispose();
        bs.show();

    }

    //runs the game
    @Override
    public void run() {
        init();
        while(running){
            update();
            render();
            isWon();
        }
        render();
        stop();
    }

    //starts the thread if not running
    public synchronized void start(){
        if(running)return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //stops the thread if interrupted or stoppped
    public synchronized void stop(){
        if(!running)return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
