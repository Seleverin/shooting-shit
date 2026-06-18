package libs.entity.item;

import libs.entity.Player;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class AttackDamageItem extends Item {

    private float attackDamageModifier;

    public AttackDamageItem(float attackDamageModifier, Transform2D transform, Collider2D collider, String sprite) {
        super(transform, collider, sprite);

        this.attackDamageModifier = attackDamageModifier;
    }

    @Override
    public void activateAbility(Player player){
        int newAttckDmg = (int)(player.attackDamage + (player.attackDamage * attackDamageModifier));
        player.attackDamage = newAttckDmg;

        this.isDead = true;
    }
}
