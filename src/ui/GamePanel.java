package ui;

import entity.Player;
import util.Transform2D;

import javax.swing.*;
import java.awt.*;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {

    private Player player;
    private Transform2D mouseTransform;

    public GamePanel(){}

    public GamePanel(Player player, Transform2D mouseTransform){
        this.player = player;
        this.mouseTransform = mouseTransform;
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.fillRect(10,10,100,100);

        paintPlayer(g2d);

        paintCursor(g2d);
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
                Math.pow(mouseTransform.x - player.getTransform().x, 2) + Math.pow(mouseTransform.y - player.getTransform().y, 2)
        ) / 10 + 10);

        g2d.drawRoundRect(mouseTransform.x, mouseTransform.y, distance2PlayerScale,distance2PlayerScale, 1000, 1000);

        g2d.drawLine(
                player.getTransform().x + 10, player.getTransform().y + 10,
                mouseTransform.x +distance2PlayerScale/2, mouseTransform.y +distance2PlayerScale/2
        );
    }

    public void setMouseTransform(Transform2D mouseTransform){
        this.mouseTransform = mouseTransform;
    }
}
