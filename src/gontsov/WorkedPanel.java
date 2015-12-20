package gontsov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class WorkedPanel extends JPanel implements MouseMotionListener, MouseListener {
    Config conf;
    int x = 0, y = 0;

    public WorkedPanel(Config conf) {
        this.conf = conf;
        setBackground(Color.WHITE);
        setBounds(100, 0, 540, 480);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (conf.type == 1) {
            Graphics2D g2d = (Graphics2D) conf.image.getGraphics();
            g2d.setStroke(new BasicStroke(conf.width));
            g2d.setColor(conf.color);
            g2d.drawLine(x, y, e.getX(), e.getY());
            x = e.getX();
            y = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (conf.type == 1)
            return;

        Graphics2D g2d = (Graphics2D) conf.image.getGraphics();
        g2d.setStroke(new BasicStroke(conf.width));
        g2d.setColor(conf.color);

        switch (conf.type) {
            case 1:
                g2d.drawLine(x, y, e.getX(), e.getY());
                break;
            case 2:
                g2d.drawRect(x, y, e.getX(), e.getY());
                break;
            case 3:
                g2d.drawOval(x, y, e.getX(), e.getY());
                break;
            case 4:
                g2d.drawRoundRect(x, y, e.getX(), e.getY(), 30, 30);
                break;
            case 5:
                g2d.drawLine(x, y, e.getX(), e.getY());
                break;
        }

        x = e.getX();
        y = e.getY();
        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(conf.image, 0, 0, null);
    }
}
