package libs.entity.item;

import libs.entity.Entity;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class SpeedItem extends Item {

    private float speedModifierAmount;

    public SpeedItem(float speedModifierAmount, Transform2D transform, Collider2D collider) {
        super(transform, collider);

        this.speedModifierAmount = speedModifierAmount;
    }

    @Override
    public void activateAbility(Entity player){
        player.setMovementSpeed((float)(player.getMovementSpeed() + (player.getMovementSpeed() * speedModifierAmount)));
        System.out.println(speedModifierAmount + "   " + player.getMovementSpeed());

        this.isDead = true;
    }
}
