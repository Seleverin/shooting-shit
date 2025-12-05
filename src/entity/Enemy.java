package entity;

import ui.MainFrame;
import util.Collider2D;
import util.Transform2D;

public class Enemy extends Entity {
    public Enemy(int health, float moveSpeed, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);
    }

    @Override
    public void move(Transform2D playerTransform, MainFrame frame) {

        Transform2D direction = new Transform2D(playerTransform.x - transform.x, playerTransform.y - transform.y);

        float length = (float) Math.sqrt(direction.x*direction.x + direction.y*direction.y);

        // Normalize Vector
        float nx = direction.x / length;
        float ny = direction.y / length;

        // Move
        float fx = transform.x + (nx * movementSpeed);
        float fy = transform.y + (ny * movementSpeed);

        for(Entity entity : frame.getEntities()){
            collider.isColliding(transform, entity);
        }

        transform.x = fx;
        transform.y = fy;
    }


}
