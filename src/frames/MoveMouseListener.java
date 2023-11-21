package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MoveMouseListener implements MouseListener, MouseMotionListener {
    JComponent target;
    JFrame frame;
    Point start_drag;
    Point start_loc;

    public MoveMouseListener(JComponent target, JFrame frame) {
        this.target = target;
        this.frame = frame;
    }

    public static JFrame getFrame(Container target) {
        if (target instanceof JFrame) {
            return (JFrame) target;
        }
        return getFrame(target.getParent());
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        this.start_drag = this.getScreenLocation(e);
        this.start_loc = this.getFrame(this.target).getLocation( );
    }



    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        Point offset = new Point(
                (int)current.getX( )-(int)start_drag.getX( ),
                (int)current.getY( )-(int)start_drag.getY( ));
        JFrame frame = this.getFrame(target);
        Point new_location = new Point(
                (int)(this.start_loc.getX( )+offset.getX( )),
                (int)(this.start_loc.getY( )+offset.getY( )));
        frame.setLocation(new_location);
    }

    Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint( );
        Point target_location = this.target.getLocationOnScreen( );
        return new Point(
                (int)(target_location.getX( )+cursor.getX( )),
                (int)(target_location.getY( )+cursor.getY( )));
    }
}
