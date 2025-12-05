package ui;

import entity.Player;
import util.Transform2D;

import javax.swing.*;
import java.awt.*;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {

    private MainFrame parentMainFrame;

    private Player player;

    public GamePanel(){}

    public GamePanel(MainFrame mainFrame){
        this.parentMainFrame = mainFrame;
        this.player = mainFrame.getPlayer();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.fillRect(10,10,100,100);

        paintCursor(g2d);

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

    private void paintCursor(Graphics2D g2d){
        short distance2PlayerScale = (short) (Math.sqrt(
                Math.pow(parentMainFrame.getMouseTransform().x - player.getTransform().x, 2) + Math.pow(parentMainFrame.getMouseTransform().y - player.getTransform().y, 2)
        ) / 10 + 10);

        g2d.drawRoundRect(parentMainFrame.getMouseTransform().x, parentMainFrame.getMouseTransform().y, distance2PlayerScale,distance2PlayerScale, 1000, 1000);

        g2d.drawLine(
                player.getTransform().x + 10, player.getTransform().y + 10,
                parentMainFrame.getMouseTransform().x +distance2PlayerScale/2, parentMainFrame.getMouseTransform().y +distance2PlayerScale/2
        );
    }
}
