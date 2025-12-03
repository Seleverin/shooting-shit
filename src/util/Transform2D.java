package util;

public class Transform2D {
    public int x;
    public int y;

    public Transform2D(){}

    public Transform2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void addDirection(Transform2D direction, float moveSpeed){
        x += (int) (direction.x * moveSpeed);
        y += (int) (direction.y * moveSpeed);
    }
}