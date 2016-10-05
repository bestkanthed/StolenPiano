package ultd.aptig.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Flower extends GameObject{
    private Bitmap spritesheet;
    private int dx;

    public Flower(Bitmap res, int x, int y,int rotate) {
        super.x=x;
        super.y=y;
        spritesheet=res;
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        spritesheet = Bitmap.createBitmap(spritesheet , 0, 0, spritesheet.getWidth(), spritesheet.getHeight(), matrix, true);
        dx = GamePanel.MOVE_SPEED;
    }
    public void update(){


        if(x<-GamePanel.WIDTH){
            x=0;
        }
        x = x+ dx;


    }
    public void draw(Canvas canvas){

        canvas.drawBitmap(spritesheet, x, y, null);
    }

}

