package ultd.aptig.piano;


import android.graphics.Bitmap;

/**
 * Created by RK on 5/5/2016.
 */
public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startsTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.frames=frames;
        currentFrame = 0 ;
        startsTime = System.nanoTime();

    }
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame = i;}

    public void update(){
        long elapsed = (System.nanoTime()-startsTime)/1000000;
        if(elapsed>delay){

            currentFrame++;
            startsTime = System.nanoTime();
        }

        if(currentFrame==frames.length){
            currentFrame = 0;
            playedOnce = true;

        }
    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }
    public int getFrame(){
        return currentFrame;
    }
    public boolean playedOnce(){return playedOnce;}
}

