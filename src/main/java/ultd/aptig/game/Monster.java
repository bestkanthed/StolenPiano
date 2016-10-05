package ultd.aptig.game;

/**
 * Created by RK on 10/3/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.animation.*;

import java.util.Random;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Monster extends GameObject {//get the coordinates

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private int xstart = 18;
    private int ystart[]={0,171,342,493,632};
    private int xend= 235;
    private int yend[]={171,342,493,632,782};

    public Monster(Bitmap res, int x, int y, int w,int h,int s,int numFrame, int rotate){

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;
        speed = 7 + (int)(rand.nextDouble()*score/30);
        if(speed>99)speed=99;//max speed
        Bitmap[] image = new Bitmap[numFrame];
        spritesheet=res;
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        for(int i=0;i<image.length;i++){
            image[i] = Bitmap.createBitmap(spritesheet,xstart,ystart[i],xend-xstart,yend[i]-ystart[i]);
             image[i] = Bitmap.createBitmap(image[i] , 0, 0, image[i].getWidth(), image[i].getHeight(), matrix, true);
        }


        animation.setFrames(image);
        animation.setDelay(100-speed); //spin faster
    }

    public void update(){
        x = x+GamePanel.MOVE_SPEED;
        animation.update();

    }
    public void draw(Canvas canvas){

        try {
            canvas.drawBitmap(animation.getImage(),x ,y ,null);
        }catch (Exception e){}

    }

    @Override
    public int getWidth() {//hit tail
        return width-10;
    }
}
