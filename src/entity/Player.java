package entity;

import util.Transform2D;

import javax.xml.crypto.dsig.Transform;

public class Player extends Entity {
    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform) {
        super(health, moveSpeed, transform);
    }

    @Override
    public void move(Transform2D direction) {
//        transform.addDirection(direction, movementSpeed);
    }
}
