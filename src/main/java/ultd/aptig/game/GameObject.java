package ultd.aptig.game;

import android.graphics.Rect;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public abstract class GameObject {

    protected int x,y,dx,dy;
    protected int width;
    protected int height;//for collisions

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public Rect getRectangle(){
        return new Rect(x,y,x+width,y+height);
    }
}
