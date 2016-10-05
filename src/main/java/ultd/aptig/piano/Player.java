package ultd.aptig.piano;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Player{

    private Bitmap spritesheet;
    private int score;
    private boolean up;
    private boolean playing;
    private Animation animation;

    private long startTime;
    public Player(Bitmap res,int w,int h,int numFrames){
        animation = new Animation();
        score = 0;
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        //for(int i=0; i<image.length; i++){

          //  image[i] = Bitmap.createBitmap(spritesheet, i*width, 0 , width, height);
        //}

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b){up = b;}//called by motion event// I press it to make the helicopter go up//
    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }

        animation.update();
        if (up) {}
    }
    //public void draw(Canvas canvas){
        //canvas.drawBitmap(animation.getImage(),x,y,null);

    //}

    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){ playing = b;}
    public void resetScore(){score = 0;}

}
