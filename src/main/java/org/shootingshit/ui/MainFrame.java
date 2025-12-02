package org.shootingshit.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//
// Main Game- and Input-Loop + managing and processing data to be shown on screen by the GamePanel-Class
//
public class MainFrame extends JFrame implements KeyListener, MouseListener {
    private GamePanel gamePanel = new GamePanel();

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
        System.out.println(keyEvent.getKeyChar());
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
