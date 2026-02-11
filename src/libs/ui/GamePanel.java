package libs.ui;

import libs.entity.Enemy;
import libs.entity.Entity;
import libs.entity.Player;
import libs.entity.Projectile;
import libs.entity.item.HealthItem;

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
    private final int entityScale = 20;

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

        paintEntities(g2d);

        paintCursor(g2d);
        paintPlayer(g2d);

        paintHealthbar(g2d);

        // Debug
//        paintHitboxes(g2d);
    }


    private void paintHealthbar(Graphics2D g2d){
        // Background
        g2d.setColor(Color.darkGray);
        g2d.fillRect(
                (int)player.getTransform().x, (int)(player.getTransform().y - player.getCollider().height * 0.35),
                (int)player.getCollider().width,6
        );
        // Fill
        g2d.setColor(Color.red);
        g2d.fillRect(
                (int)player.getTransform().x, (int)(player.getTransform().y - player.getCollider().height * 0.35),
                (int)(player.getHealth() / (player.getMaxHealth() / player.getCollider().width)),6
        );
        g2d.setColor(Color.BLACK);
    }

    private void paintPlayer(Graphics2D g2d){
        g2d.setColor(new Color(83, 88, 219));

//        g2d.drawImage(
//                player.getActiveSprite(),
//                (int)player.getTransform().x,(int)player.getTransform().y,
//                entityScale,entityScale,null
//        );
        g2d.fillRoundRect(
                (int)player.getTransform().x, (int)player.getTransform().y,
                20,20,
                100,100
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

    private void paintEntities(Graphics2D g2d){
        for(Entity entity : parentMainFrame.getEntities()){
            // Enemies
            if(entity.getClass() == Enemy.class){
                int green = (int)(180 * (entity.getHealth() * 0.01));
                if (green > 225) green = 225;

                if (green > 0){
                    g2d.setColor(new Color(240, green, 24));
                    g2d.fillRoundRect(
                            (int)entity.getTransform().x, (int)entity.getTransform().y,
                            (int)entity.getCollider().width,(int)entity.getCollider().height,
                            100,100
                    );
                }
            }
            // Projectiles
            else if(entity.getClass() == Projectile.class){
//                int bulletColor = (int)(Math.floor(Math.random() * 200));
                g2d.setColor(new Color(50, 50, 50));
                g2d.fillRoundRect(
                        (int)entity.getTransform().x, (int)entity.getTransform().y,
                        (int)entity.getCollider().width,(int)entity.getCollider().height,
                        100,100
                );
            }
            // Items
            else if(entity.getClass() == HealthItem.class){
//                int bulletColor = (int)(Math.floor(Math.random() * 200));
                g2d.setColor(new Color(34, 199, 39));
                g2d.fillRoundRect(
                        (int)entity.getTransform().x, (int)entity.getTransform().y,
                        (int)entity.getCollider().width,(int)entity.getCollider().height,
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
