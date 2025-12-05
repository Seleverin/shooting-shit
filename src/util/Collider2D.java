package util;

import entity.Entity;

import java.awt.*;
import java.awt.geom.Dimension2D;

public class Collider2D {
    public double width;
    public double height;

    public Collider2D(double width, double height){
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(Transform2D self, Entity other){
        double selfX = self.x + width;
        double selfY = self.y + height;

        Transform2D otherTransform = other.getTransform();
        Collider2D otherCollider = other.getCollider();

        double otherX = otherTransform.x + otherCollider.width;
        double otherY = otherTransform.y + otherCollider.height;

        if(self.x >= otherTransform.x){
            System.out.println("vertical align!");
        }

        return true;
    }
}
