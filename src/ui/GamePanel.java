package ui;

import entity.Player;

import javax.swing.*;
import java.awt.*;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {

    private Player player;

    public GamePanel(){}

    public GamePanel(Player player){
        this.player = player;
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintPlayer(g2d);
    }

    private void paintPlayer(Graphics2D g2d){
        g2d.setColor(Color.ORANGE);

        g2d.fillRoundRect(
                player.getTransform().x,player.getTransform().y,
                20,20,
                100,100
        );

        g2d.setColor(Color.BLACK);
    }
}
