package libs.entity.item;

import libs.entity.Entity;
import libs.entity.Player;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class AttackSpeedItem extends Item {

    private float attackSpeedModifier;

    public AttackSpeedItem(float attackSpeedModifier, Transform2D transform, Collider2D collider, String sprite) {
        super(transform, collider, sprite);

        this.attackSpeedModifier = attackSpeedModifier;
    }

    @Override
    public void activateAbility(Player player){
        int newAttckSpeed = (int)(player.shootCooldown - (player.shootCooldown * attackSpeedModifier));
        player.shootCooldown = newAttckSpeed;

        this.isDead = true;
    }
}
