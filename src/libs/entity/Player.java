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

    private BufferedImage activeSprite;

    private final int hitCooldown = 1000;
    private long lastHitTime;

    private final int shootCooldown = 75;
    private long lastShootTime;

    public Player(){}

    public Player(int health, float moveSpeed, int attackDamage, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);

        this.attackDamage = attackDamage;

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

        if (health > 0){
            health -= dmg;
            System.out.println(health);
        }
        else {
            isDead = true;
        }
    }

    public void shoot(Transform2D targetPos, List<Entity> entities){
        if(System.currentTimeMillis() < lastShootTime + shootCooldown) return;
        lastShootTime = System.currentTimeMillis();

        int bulletSpread = (int) (Math.sqrt(
                Math.pow(targetPos.x - transform.x, 2) + Math.pow(targetPos.y - transform.y, 2)
        ) / 10);

        int xSpread = (int)(Math.floor(Math.random() * bulletSpread) - (double) bulletSpread / 2);
        int ySpread = (int)(Math.floor(Math.random() * bulletSpread) - (double) bulletSpread / 2);

        System.out.println(xSpread + " | " + ySpread);

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
