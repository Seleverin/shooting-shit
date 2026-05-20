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
        System.out.println("old: "+ player.getMovementSpeed());
        float newMovementSpeed = (float)(player.getMovementSpeed() + (player.getMovementSpeed() * speedModifierAmount));
        player.setMovementSpeed(newMovementSpeed);
        System.out.println("new: "+newMovementSpeed);
        System.out.println("modifier: "+speedModifierAmount);

        this.isDead = true;
    }
}
