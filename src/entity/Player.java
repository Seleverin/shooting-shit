package entity;

import util.Transform2D;

public class Player extends Entity {
    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform) {
        super(health, moveSpeed, transform);
    }
}
