package libs.entity;

import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private BufferedImage activeSprite;

    private final int hitCooldown = 1000;
    private long lastHitTime;

    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);

        lastHitTime = System.currentTimeMillis();
    }

    @Override
    public void move(Transform2D direction, MainFrame frame) {
//        transform.addDirection(direction, movementSpeed);
    }

    @Override
    public void takeDamage(int dmg) {
        if(System.currentTimeMillis() >= lastHitTime + hitCooldown){
            if (health > 0){
                health -= dmg;
                System.out.println("Player got hit");
            }
            else{
                System.out.println("Player died");
            }
            lastHitTime = System.currentTimeMillis();
        }
    }

    public void setActiveSprite(String src){
        try {
            this.activeSprite = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(src))
            );
        }
        catch (IOException e) {
            throw new RuntimeException("Sprite not found for Player ", e);
        }
    }

    public BufferedImage getActiveSprite(){ return activeSprite; }
}
