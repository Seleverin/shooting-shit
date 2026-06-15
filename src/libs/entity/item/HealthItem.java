package libs.entity.item;

import libs.entity.Entity;
import libs.entity.Player;
import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class HealthItem extends Item {

    private int healthAmount;

    public HealthItem(int healthAmount, Transform2D transform, Collider2D collider, String sprite) {
        super(transform, collider, sprite);

        this.healthAmount = healthAmount;
    }

    @Override
    public void activateAbility(Player player){
        if(player.getHealth() < player.getMaxHealth()){
            player.setHealth(player.getHealth() + healthAmount);

            if(player.getHealth() > player.getMaxHealth()){
                player.setHealth(player.getMaxHealth());
            }

            this.isDead = true;
        }
        else{
            player.setMaxHealth(player.getMaxHealth() + healthAmount / 2);
        }
    }
}
