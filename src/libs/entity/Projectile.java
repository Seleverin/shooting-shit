package libs.entity;

import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class Projectile extends Entity {

    public int attackDamage;

    public Transform2D direction;

    // Destroy timer
    private long timeToDestroy;

    public Projectile(int health, float moveSpeed, int attackDamage, Transform2D transform, Collider2D collider, Transform2D direction) {
        super(health, moveSpeed, transform, collider);

        this.attackDamage = attackDamage;

        this.direction = direction;
        this.timeToDestroy = System.currentTimeMillis() + 10000; // Destroy after 10 secs
    }

    @Override
    public void move(Transform2D direction, MainFrame parentMainFrame) {

        if (timeToDestroy < System.currentTimeMillis()) {
            isDead = true;
            return;
        }

        direction = this.direction;

        float vectorLength = (float) Math.sqrt(direction.x*direction.x + direction.y*direction.y);

        Transform2D dirNormalized = new Transform2D(
                direction.x / vectorLength, direction.y / vectorLength
        );

        transform.x += direction.x * movementSpeed;
        transform.y += direction.y * movementSpeed;

        for(Entity entity : parentMainFrame.getEntities()){
            if(entity != this && collider.isColliding(transform,entity) && entity.getClass() != Player.class && entity.getClass() != Projectile.class){
                entity.takeDamage(attackDamage);
                this.isDead = true;
                break;
            }
        }
    }

    @Override
    public void takeDamage(int dmg) {}
}
