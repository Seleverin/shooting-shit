package libs.entity;

import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class Projectile extends Entity {

    public Transform2D direction;

    public Projectile(int health, int attackDmg, float moveSpeed, Transform2D transform, Collider2D collider, Transform2D direction) {
        super(health, moveSpeed, transform, collider);

        this.direction = direction;
    }

    @Override
    public void move(Transform2D direction, MainFrame parentMainFrame) {

        direction = this.direction;

        float vectorLength = (float) Math.sqrt(direction.x*direction.x + direction.y*direction.y);

        Transform2D dirNormalized = new Transform2D(
            direction.x / vectorLength, direction.y / vectorLength
        );

        transform.x += direction.x * movementSpeed;
        transform.y += direction.y * movementSpeed;
    }

    @Override
    public void takeDamage(int dmg) {}
}
