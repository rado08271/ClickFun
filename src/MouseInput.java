import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


//class to handle mouse clicks public variables now change for getters
public class MouseInput implements MouseListener, MouseMotionListener{

    public int x,y;
    public boolean isClicked = false;

    MouseInput(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){
            isClicked = true;
        }
//        isClicked = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
