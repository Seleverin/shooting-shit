package libs.ui;

import libs.entity.Enemy;
import libs.entity.Entity;
import libs.entity.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {

    private MainFrame parentMainFrame;

    private Player player;
    private final int entityScale = 40;

    public GamePanel(){}

    public GamePanel(MainFrame mainFrame){
        this.parentMainFrame = mainFrame;
        this.player = mainFrame.getPlayer();

        // load Sprites
//        try {
//            sprite_player = ImageIO.read(
//                    Objects.requireNonNull(getClass().getResource("/assets/player/right-down.png"))
//            );
//        } catch (IOException e) {
//            throw new RuntimeException("Image not found ", e);
//        }
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintCursor(g2d);
        paintPlayer(g2d);

        paintEnemies(g2d);

        // Debug
//        paintHitboxes(g2d);
    }



    private void paintPlayer(Graphics2D g2d){
        g2d.setColor(Color.ORANGE);

        g2d.drawImage(
                player.getActiveSprite(),
                (int)player.getTransform().x,(int)player.getTransform().y,
                entityScale,entityScale,null
        );

        g2d.setColor(Color.BLACK);
    }

    private void paintCursor(Graphics2D g2d){
        short distance2PlayerScale = (short) (Math.sqrt(
                Math.pow(parentMainFrame.getMouseTransform().x - player.getTransform().x, 2) + Math.pow(parentMainFrame.getMouseTransform().y - player.getTransform().y, 2)
        ) / 10 + 10);

        g2d.drawRoundRect((int)parentMainFrame.getMouseTransform().x, (int)parentMainFrame.getMouseTransform().y, distance2PlayerScale,distance2PlayerScale, 1000, 1000);

        g2d.drawLine(
                (int)player.getTransform().x + entityScale/2, (int)player.getTransform().y + entityScale/2,
                (int)parentMainFrame.getMouseTransform().x +distance2PlayerScale/2, (int)parentMainFrame.getMouseTransform().y +distance2PlayerScale/2
        );
    }

    private void paintEnemies(Graphics2D g2d){
        g2d.setColor(Color.RED);

        for(Entity entity : parentMainFrame.getEntities()){
            if(entity.getClass() == Enemy.class){
                g2d.fillRoundRect(
                        (int)entity.getTransform().x, (int)entity.getTransform().y,
                        20,20,
                        100,100
                );
            }
        }

        g2d.setColor(Color.BLACK);
    }

    private void paintHitboxes(Graphics2D g2d){
        g2d.setColor(Color.BLUE);

        for(Entity entity : parentMainFrame.getEntities()){
            g2d.drawRect(
                    (int)entity.getTransform().x, (int)entity.getTransform().y,
                    (int)entity.getCollider().width,(int)entity.getCollider().height
            );
        }

        g2d.drawRect(
                (int)player.getTransform().x, (int)player.getTransform().y,
                (int)player.getCollider().width,(int)player.getCollider().height
        );

        g2d.setColor(Color.BLACK);
    }
}
