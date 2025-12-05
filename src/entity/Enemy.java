package entity;

import util.Transform2D;

public class Enemy extends Entity {
    public Enemy(int health, float moveSpeed, Transform2D transform) {
        super(health, moveSpeed, transform);
    }

    @Override
    public void move(Transform2D playerTransform) {
        Transform2D direction = new Transform2D(playerTransform.x - transform.x, playerTransform.y - transform.y);

        transform.addDirection(direction, movementSpeed);
    }


}
