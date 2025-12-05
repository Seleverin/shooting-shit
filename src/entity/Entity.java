package entity;

import util.Transform2D;

public abstract class Entity {
    protected int health;
    protected float movementSpeed;
    protected Transform2D transform;

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

    public abstract void move(Transform2D direction);


    public Transform2D getTransform(){
        return transform;
    }

    public float getMovementSpeed(){return movementSpeed;}
}
