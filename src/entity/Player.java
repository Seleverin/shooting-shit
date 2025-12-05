package entity;

import ui.MainFrame;
import util.Collider2D;
import util.Transform2D;

import javax.xml.crypto.dsig.Transform;

public class Player extends Entity {
    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform, Collider2D collider) {
        super(health, moveSpeed, transform, collider);
    }

    @Override
    public void move(Transform2D direction, MainFrame frame) {
//        transform.addDirection(direction, movementSpeed);
    }
}
