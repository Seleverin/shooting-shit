package org.shootingshit.entity;

import lombok.Data;
import org.shootingshit.Transform2D;

import javax.xml.transform.TransformerFactory;

@Data
public abstract class Entity {
    private int health;
    private float movementSpeed;
    private Transform2D transform;

    public Entity(){}

    public Entity(int health, float movementSpeed, Transform2D transform){
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.transform = transform;
    }

    public void takeDamage(int damage){
        if(health > 0){
            health -= damage;
        }
        else{
            System.out.println("DIED!");
        }
    }

    public void move(Transform2D direction){
        transform.addDirection(direction, movementSpeed);
    }
}
