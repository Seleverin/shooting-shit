package libs.entity.item;

import libs.entity.Player;
import libs.util.Collider2D;
import libs.util.Transform2D;

public class BulletAmountUpgrade extends Item {

    private int bulletAmount;
    private float BulletSpreadModifier;

    public BulletAmountUpgrade(int bulletAmount, float BulletSpreadModifier, Transform2D transform, Collider2D collider, String sprite) {
        super(transform, collider, sprite);

        this.bulletAmount = bulletAmount;
        this.BulletSpreadModifier = BulletSpreadModifier;
    }

    @Override
    public void activateAbility(Player player){
        player.bulletAmount += bulletAmount;

        float newSpread = (float)(player.bulletSpread + (player.bulletSpread * BulletSpreadModifier));
        player.bulletSpread = newSpread;

        this.isDead = true;
    }
}
