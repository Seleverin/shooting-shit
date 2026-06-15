package libs.ui;

import libs.entity.Enemy;
import libs.entity.Entity;
import libs.entity.Player;
import libs.entity.Projectile;
import libs.entity.item.*;
import libs.util.ConfigData;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//
// Class for displaying the game on screen according to data given by the MainFrame-Class
//
public class GamePanel extends JPanel {

    private MainFrame parentMainFrame;

    private Player player;

    private final Image backgroundSprite = new ImageIcon("src/assets/ground.png").getImage();

    public GamePanel(){}

    public GamePanel(MainFrame mainFrame) {
        this.parentMainFrame = mainFrame;
        this.player = mainFrame.getPlayer();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintBackground(g2d);

        paintEntities(g2d);

        paintCursor(g2d);
        paintPlayer(g2d);

        paintHealthbar(g2d);

        try {
            printScore(g2d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Debug
        if(ConfigData.show_hitboxes) paintHitboxes(g2d);
    }


    private void printScore(Graphics2D g2d) throws IOException, FontFormatException {
        Font pixelFont = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("src/assets/fonts/PressStart2P-Regular.ttf"))
                .deriveFont(16f);

        g2d.setFont(pixelFont);

        g2d.drawString("Score: "+player.score, 10,20);
    }

    private void paintBackground(Graphics2D g2d){
        int xTileCount = (int)(parentMainFrame.getWidth() / 64) + 1, yTileCount = (int)(parentMainFrame.getHeight() / 64) + 1;
        for(int y=0; y<yTileCount; y++){
            for(int x=0; x<xTileCount; x++){
                g2d.drawImage(
                        backgroundSprite,
                        x * 64, y * 64,
                        64, 64,
                        this
                );
            }
        }
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
        g2d.drawImage(
                player.getSprite(),
                (int)player.getTransform().x, (int)player.getTransform().y,
                (int)player.getCollider().width,(int)player.getCollider().height,
                this
        );
    }

    private void paintCursor(Graphics2D g2d){
        short distance2PlayerScale = (short) (Math.sqrt(
                Math.pow(parentMainFrame.getMouseTransform().x - player.getTransform().x, 2) + Math.pow(parentMainFrame.getMouseTransform().y - player.getTransform().y, 2)
        ) / 10 + 10);

        g2d.drawRoundRect((int)parentMainFrame.getMouseTransform().x, (int)parentMainFrame.getMouseTransform().y, distance2PlayerScale,distance2PlayerScale, 1000, 1000);

        g2d.drawLine(
                (int)player.getTransform().x + 10, (int)player.getTransform().y + 10,
                (int)parentMainFrame.getMouseTransform().x +distance2PlayerScale/2, (int)parentMainFrame.getMouseTransform().y +distance2PlayerScale/2
        );
    }

    private void paintEntities(Graphics2D g2d){
        for(Entity entity : parentMainFrame.getEntities()){
            if(entity.getClass() != Player.class){
                g2d.drawImage(
                        entity.getSprite(),
                        (int)entity.getTransform().x, (int)entity.getTransform().y,
                        (int)entity.getCollider().width,(int)entity.getCollider().height,
                        this
                );

                if(entity.getClass() == Enemy.class){
                    // Background
                    g2d.setColor(Color.darkGray);
                    g2d.fillRect(
                            (int)entity.getTransform().x, (int)(entity.getTransform().y - entity.getCollider().height * 0.35),
                            (int)entity.getCollider().width,6
                    );
                    // Fill
                    g2d.setColor(Color.red);
                    g2d.fillRect(
                            (int)entity.getTransform().x, (int)(entity.getTransform().y - entity.getCollider().height * 0.35),
                            (int)(entity.getHealth() / (entity.getMaxHealth() / entity.getCollider().width)),6
                    );
                    g2d.setColor(Color.BLACK);
                }
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
