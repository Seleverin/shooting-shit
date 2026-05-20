package libs.entity;

import libs.entity.item.HealthItem;
import libs.entity.item.SpeedItem;
import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class Enemy extends Entity {
    public int attackDamage;

    private final Entity[] itemDrops = {
            new HealthItem(
                    (int)Math.floor(Math.random() * 25 + 25),
                    transform,
                    new Collider2D(20,20)
            ),
            new SpeedItem(
                    (float)(Math.random() * 0.25 + 0.05),
                    transform,
                    new Collider2D(20,20)
            )
    };

    private MainFrame mainFrame;

    public Enemy(int health, float moveSpeed, int attackDamage, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);

        this.attackDamage = attackDamage;
    }

    @Override
    public void move(Transform2D playerTransform, MainFrame frame) {

        mainFrame = frame;

        Transform2D direction = new Transform2D(playerTransform.x - transform.x, playerTransform.y - transform.y);

        float length = (float) Math.sqrt(direction.x*direction.x + direction.y*direction.y);

        // Normalize Vector
        float nx = direction.x / length;
        float ny = direction.y / length;

        // Move
        float fx = transform.x + (nx * movementSpeed);
        float fy = transform.y + (ny * movementSpeed);

        for(Entity entity : frame.getEntities()){
            if(entity != this){
                if(collider.isColliding(new Transform2D(fx,fy), entity) && (entity.getClass() == Enemy.class || entity.getClass() == Player.class)){
                    fx = transform.x; fy = transform.y;
                    if(entity.getClass() == Player.class){
                        entity.takeDamage(attackDamage);
                    }
                }
            }
        }

        transform.x = fx;
        transform.y = fy;
    }

    @Override
    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0 && !isDead){
            Entity item = itemDrops[(int)(Math.random() * itemDrops.length)];
//            Entity item = itemDrops[1];
            item.transform = transform;
            mainFrame.addEntity2AddingQueue(
                    item
            );

            isDead = true;
        }
    }


}
