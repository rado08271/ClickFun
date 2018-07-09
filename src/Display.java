import javax.swing.*;
import java.awt.*;

//this whole class is just utility...taken from StackOverflow etc
public class Display {
    private JFrame frame;
    private Canvas canvas;
    private int width, height;
    private String title;

    Display(int width, int height,String title){
        this.width = width;
        this.height = height;
        this.title = title;
        setFrame();
    }

    private void setFrame(){
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        addCanvas();
        frame.add(canvas);
    }

    private void addCanvas(){
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
