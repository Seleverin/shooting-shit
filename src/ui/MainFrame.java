package ui;

import entity.Player;
import util.Transform2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//
// Main Game- and Input-Loop + managing and processing data to be shown on screen by the GamePanel-Class
//
public class MainFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    // Variables
    private Player player = new Player(100, 10f, new Transform2D(10,10));
    private Transform2D mouseTransform = new Transform2D();

    private GamePanel gamePanel = new GamePanel(player, mouseTransform);

    // Main Game-Loop
    public MainFrame(){
        setSize(500,500);

        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.createImage("");
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "invisibleCursor");
        setCursor(invisibleCursor);

        add(gamePanel);

        setVisible(true);
    }


    //
    // Key Listener Methods
    //
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Transform2D direction = new Transform2D(0,0);

        System.out.println(keyEvent);

        char keyChar = keyEvent.getKeyChar();
        if(keyEvent.getKeyChar() == 'w'){
            direction.y = -1;
        }
        if(keyEvent.getKeyChar() == 'a'){
            direction.x = -1;
        }
        if(keyEvent.getKeyChar() == 's'){
            direction.y = 1;
        }
        if(keyEvent.getKeyChar() == 'd'){
            direction.x = 1;
        }

        player.move(direction);

        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    //
    // Mouse Listener Methods
    //
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    //
    // Mouse Motion Listener
    //
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseTransform.x = e.getX() - 25;
        mouseTransform.y = e.getY() - 45;

        gamePanel.setMouseTransform(mouseTransform);

        gamePanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseTransform.x = e.getX() - 25;
        mouseTransform.y = e.getY() - 45;

        gamePanel.setMouseTransform(mouseTransform);

        gamePanel.repaint();
    }
}
