package libs.entity;

import libs.entity.item.*;
import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class Enemy extends Entity {
    public int attackDamage;
    public Transform2D knockback;

    private final Entity[] itemDrops = {
            new HealthItem(
                    (int)Math.floor(Math.random() * 25 + 25),
                    transform,
                    new Collider2D(25,25),
                    "src/assets/items/health_item.png"
            ),
            new SpeedItem(
                    (float)(Math.random() * 0.2 + 0.05),
                    transform,
                    new Collider2D(25,25),
                    "src/assets/items/speed_upgrade_item.png"
            ),
            new AttackSpeedItem(
                    (float)(Math.random() * 0.1 + 0.05),
                    transform,
                    new Collider2D(25,25),
                    "src/assets/items/attack_speed_upgrade.png"
            ),
            new AttackDamageItem(
                    (float)(Math.random() * 0.25 + 0.1),
                    transform,
                    new Collider2D(25,25),
                    "src/assets/items/attack_damage_upgrade.png"
            ),
            new BulletAmountUpgrade(
                    1,
                    (float)(Math.random() * 0.5 + 1),
                    transform,
                    new Collider2D(25,25),
                    "src/assets/items/bullet_amount_upgrade.png"
            )
    };

    private MainFrame mainFrame;

    public Enemy(int health, float moveSpeed, int attackDamage, Transform2D transform, Collider2D collider, String sprite) {
        super(health, moveSpeed, transform, collider, sprite);
        knockback = new Transform2D(0,0);

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
                        knockback = new Transform2D(-nx * movementSpeed * 2.5f, -ny * movementSpeed * 2.5f);
                    }
                }
            }
        }

        transform.x = fx + knockback.x;
        transform.y = fy + knockback.y;

        if (knockback.x + knockback.y != 0){
            knockback.x *= 0.95;
            knockback.y *= 0.95;
        }
    }

    @Override
    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0 && !isDead){
            Entity item = itemDrops[(int)(Math.random() * itemDrops.length)];
            item.transform = transform;
            mainFrame.addEntity2AddingQueue(
                    item
            );

            mainFrame.getPlayer().addScore((int)((health + attackDamage + movementSpeed) / 5));

            isDead = true;
        }
    }


}
