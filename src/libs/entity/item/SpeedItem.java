package libs.entity.item;

import libs.entity.Entity;
import libs.entity.Player;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class SpeedItem extends Item {

    private float speedModifierAmount;

    public SpeedItem(float speedModifierAmount, Transform2D transform, Collider2D collider, String sprite) {
        super(transform, collider, sprite);

        this.speedModifierAmount = speedModifierAmount;
    }

    @Override
    public void activateAbility(Player player){
        float newMovementSpeed = (float)(player.getMovementSpeed() + (player.getMovementSpeed() * speedModifierAmount));
        player.setMovementSpeed(newMovementSpeed);

        this.isDead = true;
    }
}
