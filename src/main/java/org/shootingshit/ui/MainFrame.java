package org.shootingshit.ui;

import org.shootingshit.Transform2D;
import org.shootingshit.entity.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//
// Main Game- and Input-Loop + managing and processing data to be shown on screen by the GamePanel-Class
//
public class MainFrame extends JFrame implements KeyListener, MouseListener {
    // Variables
    private Player player = new Player(100, 2f, new Transform2D(10,10));
    private GamePanel gamePanel = new GamePanel(player);

    // Main Game-Loop
    public MainFrame(){
        setSize(500,500);

        addKeyListener(this);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        switch (keyEvent.getKeyChar()){
            case 'w':
                direction.y = -1;
                break;
            case 'a':
                direction.x = -1;
                break;
            case 's':
                direction.y = 1;
                break;
            case 'd':
                direction.x = 1;
                break;
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
}
