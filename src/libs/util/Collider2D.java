package libs.util;

import libs.entity.Entity;

public class Collider2D {
    public double width;
    public double height;

    public Collider2D(double width, double height){
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(Transform2D self, Entity other){
        double selfPosX = self.x + 20;
        double selfPosY = self.y + 20;

        Transform2D otherTransform = other.getTransform();
        Collider2D otherCollider = other.getCollider();

        if(
                selfPosX >= otherTransform.x &&
                selfPosX <= otherTransform.x + otherCollider.width &&
                selfPosY >= otherTransform.y &&
                selfPosY <= otherTransform.y + otherCollider.height
        ){
            return true;
        }
        else{
            return false;
        }
    }
}
