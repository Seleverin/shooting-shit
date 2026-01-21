package libs.util;

import libs.entity.Entity;

public class Collider2D {
    public double width;
    public double height;
    public boolean isTrigger;

    public Collider2D(double width, double height){
        this.width = width;
        this.height = height;
        this.isTrigger = false;
    }

    public Collider2D(double width, double height, boolean isTrigger){
        this.width = width;
        this.height = height;
        this.isTrigger = isTrigger;
    }

    public boolean isColliding(Transform2D self, Entity other){

        Transform2D otherTransform = other.getTransform();
        Collider2D otherCollider = other.getCollider();

        double leftA   = self.x;
        double rightA  = self.x + 20;
        double topA    = self.y;
        double bottomA = self.y + 20;

        double leftB   = otherTransform.x;
        double rightB  = otherTransform.x + otherCollider.width;
        double topB    = otherTransform.y;
        double bottomB = otherTransform.y + otherCollider.height;

        if (rightA <= leftB ||
                leftA >= rightB ||
                bottomA <= topB ||
                topA >= bottomB) {
            return false;
        }

        return true;
    }

}
