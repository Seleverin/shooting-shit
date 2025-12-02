package org.shootingshit.ui;

import javax.swing.*;
import java.awt.*;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {
    public GamePanel(){}

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        int rand = (int) Math.floor(Math.random()*100);
        g2d.drawRect(0,0,100,rand);
    }
}
