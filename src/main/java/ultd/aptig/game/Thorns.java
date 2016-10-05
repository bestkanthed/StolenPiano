package ultd.aptig.game;

/**
 * Created by RK on 10/2/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.animation.*;

import java.util.Random;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Thorns extends GameObject {//get the coordinates

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;
    private int xbase[]={54,150,223,312,398};
    private int ybase[]={276,281,281,281,281};
    private int xstart[]={41,130,205,290,380};
    private int ystart[]={132,149,165,178,186};
    private int xend[]={96,194,290,380,488};
    private int yend[]={281,285,285,285,285};

    public Thorns(Bitmap res, int x, int y, int w,int h,int s,int numFrame,int rotate){

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

        for(int i=0;i<image.length;i++) {
            image[i] = Bitmap.createBitmap(spritesheet, xstart[i], ystart[i], xend[i] - xstart[i], yend[i] - ystart[i]);
            image[i] = Bitmap.createBitmap(image[i], 0, 0, image[i].getWidth(), image[i].getHeight(), matrix, true);
        }


        animation.setFrames(image);
        animation.setDelay(100-speed); //spin faster
    }

    public void updated(){
        x = x+GamePanel.MOVE_SPEED;
        animation.update();

    }
    public void draw(Canvas canvas){

        try {
            canvas.drawBitmap(animation.getImage(),x - xbase[animation.getFrame()]+ xstart[animation.getFrame()] ,y - ybase[animation.getFrame()]+ ystart[animation.getFrame()] ,null);
        }catch (Exception e){}

    }

    @Override
    public int getWidth() {//hit tail
        return width-10;
    }
}

