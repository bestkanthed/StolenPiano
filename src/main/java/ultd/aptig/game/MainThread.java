package ultd.aptig.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import java.math.BigDecimal;

/**
 * Created by Abhishek Kanthed on 5/4/2016.
 */
public class MainThread extends Thread {

    private int FPS=40;
    private double avgFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePanel){

        super();//call constructor of the super class
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    public void run() {
        long startTime;
        long timeMilli;
        long waitTime;
        long totalTime=0;
        int frameCount=0;
        long targetTime=1000/FPS; //each time you run the game loop it should take 1000/30 milli seconds // thats my aim

        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    //each time this runs should be 1/30 of a second
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {}
            finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();}
                }
            }

            timeMilli = (System.nanoTime() - startTime) / 1000000;// time it took
            waitTime = targetTime - timeMilli;// how long i am gonna wait to go throught the loop again// i make it wait so i get my loop

            try{
                this.sleep(waitTime);
            }catch (Exception e){}

            totalTime = totalTime + System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS){

                avgFPS =  1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                //System.out.println(avgFPS);
                //System.out.println(System.nanoTime());
            }
        }

    }

    public void setRunning(boolean b){
        running=b;
    }
}