package ultd.aptig.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.animation.*;

import java.util.Random;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Missile extends GameObject {//get the coordinates

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;

    public Missile (Bitmap res, int x, int y, int w,int h,int s,int numFrame){

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;
        speed = 7 + (int)(rand.nextDouble()*score/30);
        if(speed>99)speed=99;//max speed
        Bitmap[] image = new Bitmap[numFrame];
        spritesheet=res;
        for(int i=0;i<image.length;i++){
            image[i] = Bitmap.createBitmap(spritesheet,0, i*height,width,height);
        }

        animation.setFrames(image);
        animation.setDelay(100-speed); //spin faster
    }

     public void updated(){
        x-=speed;
        animation.update();

     }
     public void draw(Canvas canvas){

         try {
             canvas.drawBitmap(animation.getImage(),x,y,null);
         }catch (Exception e){}

     }

    @Override
    public int getWidth() {//hit tail
        return width-10;
    }
}
