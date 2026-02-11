package libs.entity.item;

import libs.entity.Entity;
import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class HealthItem extends Item {

    private int healthAmount;

    public HealthItem(int healthAmount, Transform2D transform, Collider2D collider) {
        super(transform, collider);

        this.healthAmount = healthAmount;
    }

    @Override
    public void activateAbility(Entity player){
        player.setHealth(player.getHealth() + healthAmount);

        if(player.getHealth() > player.getMaxHealth()){
            player.setHealth(player.getMaxHealth());
            System.out.println("ttttt");
        }

        this.isDead = true;
    }
}
