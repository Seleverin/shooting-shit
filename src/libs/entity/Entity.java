package libs.entity;

import libs.ui.MainFrame;
import libs.util.Collider2D;
import libs.util.Transform2D;

import javax.swing.*;
import java.awt.*;

public abstract class Entity {
    public boolean isDead;
    protected int health;
    protected int maxHealth;
    protected float movementSpeed;
    protected Transform2D transform;
    protected Collider2D collider;
    protected Image sprite;

    public Entity(){}

    public Entity(int health, float movementSpeed, Transform2D transform, Collider2D collider, String sprite){
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.transform = transform;
        this.collider = collider;
        this.isDead = false;
        this.maxHealth = health;
        this.sprite = new ImageIcon(sprite).getImage();
    }

    public Entity(Transform2D transform, Collider2D collider, String sprite) {
        this.transform = transform;
        this.collider = collider;
        this.sprite = new ImageIcon(sprite).getImage();
    }

    public abstract void move(Transform2D direction, MainFrame parentMainFrame);

    public abstract void takeDamage(int dmg);

    public Transform2D getTransform(){
        return transform;
    }

    public float getMovementSpeed(){return movementSpeed;}

    public Collider2D getCollider(){
        return collider;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public void setMovementSpeed(float movementSpeed){
        this.movementSpeed = movementSpeed;
    }

    public Image getSprite(){
        return sprite;
    }

    public void setSprite(String sprite){
        this.sprite = new ImageIcon(sprite).getImage();
    }
}
