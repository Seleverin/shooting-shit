package ui;

import entity.Entity;
import entity.Player;
import util.InputData;
import util.Transform2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//
// Main Game- and Input-Loop + managing and processing data to be shown on screen by the GamePanel-Class
//
public class MainFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener, Runnable {
    // Variables
    private Player player = new Player(100, 3f, new Transform2D(10,10));
    private Transform2D mouseTransform = new Transform2D();

    // Controls
    private boolean up, down, left, right;

    private GamePanel gamePanel = new GamePanel(this);

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

        // Game Loop
        Thread gameThread = new Thread(this);
        gameThread.start();
    }


    //
    // Key Listener Methods
    //
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseTransform.x = e.getX() - 25;
        mouseTransform.y = e.getY() - 45;
    }

    @Override
    public void run() {
        while (true) {

            if (up) player.getTransform().y -= (int) player.getMovementSpeed();
            if (down) player.getTransform().y += (int) player.getMovementSpeed();
            if (left) player.getTransform().x -= (int) player.getMovementSpeed();
            if (right) player.getTransform().x += (int) player.getMovementSpeed();

            gamePanel.repaint();

            // 60 FPS
            try {
                Thread.sleep(16);
            }
            catch (InterruptedException e) {
                throw new RuntimeException("An error occurred while executing the main Game-Loop! "+e);
            }
        }
    }

    public Player getPlayer(){
        return this.player;
    }

    public Transform2D getMouseTransform(){
        return this.mouseTransform;
    }
}
