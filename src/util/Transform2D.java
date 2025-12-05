package util;

public class Transform2D {
    public float x;
    public float y;

    public Transform2D(){}

    public Transform2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void addDirection(Transform2D direction, float moveSpeed){
        float length = (float) Math.sqrt(direction.x*direction.x + direction.y*direction.y);

        // Normalize Vector
        float nx = direction.x / length;
        float ny = direction.y / length;

        // Move
        x += (nx * moveSpeed);
        y += (ny * moveSpeed);
    }
}