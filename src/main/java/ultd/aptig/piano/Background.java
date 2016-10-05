package ultd.aptig.piano;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Background {
    private Bitmap image;
    private int x=0,y=0,dx;

    public Background(Bitmap res) {
        image=res;
        dx = GamePanel.MOVE_SPEED;
    }
    public void update(){


        if(x<-GamePanel.WIDTH){
            x=0;
        }
        x = x+ dx;


    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
       // canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);


    }

}