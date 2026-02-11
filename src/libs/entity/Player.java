package libs.entity;

import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Player extends Entity {

    public int attackDamage;
    public int bulletAmount;
    public float bulletSpread;

    private BufferedImage activeSprite;

    private final int hitCooldown = 750;
    private long lastHitTime;

    public int shootCooldown;
    private long lastShootTime;

    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);

        // Default values
        this.bulletAmount = 5;
        this.shootCooldown = 1;
        this.attackDamage = 5;
        this.bulletSpread = 0.75f;

        lastHitTime = System.currentTimeMillis();
        lastShootTime = System.currentTimeMillis();
    }

    @Override
    public void move(Transform2D direction, MainFrame frame) {
//        transform.addDirection(direction, movementSpeed);
    }

    @Override
    public void takeDamage(int dmg) {
        if(System.currentTimeMillis() < lastHitTime + hitCooldown) return;
        lastHitTime = System.currentTimeMillis();

        health -= dmg;

        if (health > 0){
            System.out.println(health);
        }
        else {
            isDead = true;
        }
    }

    public void shoot(Transform2D targetPos, List<Entity> entities){
        if(System.currentTimeMillis() < lastShootTime + shootCooldown) return;
        lastShootTime = System.currentTimeMillis();

        for (int b = 0; b<bulletAmount; b++){
            int spread = (int) (Math.sqrt(
                    Math.pow(targetPos.x - transform.x, 2) + Math.pow(targetPos.y - transform.y, 2)
            ) * bulletSpread);

            int xSpread = (int)(Math.floor(Math.random() * spread) - (double) spread / 2);
            int ySpread = (int)(Math.floor(Math.random() * spread) - (double) spread / 2);

            Transform2D lookingDir = new Transform2D(targetPos.x - transform.x + xSpread, targetPos.y - transform.y + ySpread);

            float vectorLength = (float) Math.sqrt(lookingDir.x*lookingDir.x + lookingDir.y*lookingDir.y);

            lookingDir.x /= vectorLength; lookingDir.y /= vectorLength;

            Transform2D spawnPos = new Transform2D(
                    transform.x + lookingDir.x * 10 + 10,
                    transform.y + lookingDir.y * 10 + 10
            );
            entities.add(new Projectile(
                            1, 5f, attackDamage,
                            spawnPos,
                            new Collider2D(5,5, true),
                            lookingDir
                    )
            );
        }
    }

//    public void setActiveSprite(String src){
//        try {
//            this.activeSprite = ImageIO.read(
//                    Objects.requireNonNull(getClass().getResource(src))
//            );
//        }
//        catch (IOException e) {
//            throw new RuntimeException("Sprite not found for Player ", e);
//        }
//    }

//    public BufferedImage getActiveSprite(){ return activeSprite; }
}
