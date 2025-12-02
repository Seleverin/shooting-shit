package org.shootingshit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.shootingshit.Transform2D;

@EqualsAndHashCode(callSuper = true)
@Data
public class Player extends Entity {
    public Player(){}

    public Player(int health, float moveSpeed, Transform2D transform) {
        super(health, moveSpeed, transform);
    }
}
