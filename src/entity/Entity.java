package entity;

import ui.MainFrame;
import util.Collider2D;
import util.Transform2D;

public abstract class Entity {
    protected int health;
    protected float movementSpeed;
    protected Transform2D transform;
    protected Collider2D collider;

    public Entity(){}

    public Entity(int health, float movementSpeed, Transform2D transform, Collider2D collider){
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.transform = transform;
        this.collider = collider;
    }

    public abstract void move(Transform2D direction, MainFrame parentMainFrame);


    public Transform2D getTransform(){
        return transform;
    }

    public float getMovementSpeed(){return movementSpeed;}

    public Collider2D getCollider(){
        return collider;
    }
}
