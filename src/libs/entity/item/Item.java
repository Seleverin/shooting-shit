package libs.entity.item;

import libs.entity.Entity;
import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

public abstract class Item extends Entity {

    public Item(Transform2D transform, Collider2D collider) {
        super(transform, collider);
    }

    @Override
    public void move(Transform2D direction, MainFrame parentMainFrame) {
        if (this.collider.isColliding(transform, parentMainFrame.getPlayer())){
            System.out.println(" trset");
            activateAbility(parentMainFrame.getPlayer());
        }
    }

    @Override
    public void takeDamage(int dmg) {
        return;
    }

    public abstract void activateAbility(Entity reciever);
}
