package ultd.aptig.game;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import static android.graphics.Color.WHITE;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Stem {
    private Path mPath;
    private Paint mPaint;
    private int x=0,y=0,dx;
    private int stem_width = GamePanel.SCREEN_WIDTH/7;
    private int stem_waves = 2;

    public Stem() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(0xddB03A2E);
        //mPath.moveTo(0, 0);
        //mPath.moveTo(0,GamePanel.SCREEN_HEIGHT/2+stem_width/2);
        mPath.moveTo(0+x,GamePanel.SCREEN_HEIGHT/2-stem_width/2);

        int k=1;//up
        for(int i=0;i<2*stem_waves+2;i++){
            k=k*-1;
            mPath.quadTo((i*2+1)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+(2*k-1)*stem_width/2, (i*2+2)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2-stem_width/2);
        }

        mPath.lineTo(GamePanel.SCREEN_WIDTH+GamePanel.SCREEN_WIDTH/stem_waves+x,GamePanel.SCREEN_HEIGHT/2+stem_width/2);
        k=k*-1;

        for(int i=2*stem_waves+2;i>0;i--){
            k=k*-1;
            mPath.quadTo((i*2-1)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+(2*k+1)*stem_width/2, (i*2-2)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+stem_width/2);
        }
        mPath.lineTo(0+x, GamePanel.SCREEN_HEIGHT/2-stem_width/2);
        dx = GamePanel.MOVE_SPEED;
    }

    public void update(){
        if(x<-GamePanel.SCREEN_WIDTH/2){
            x=0;
        }
        x = x+ dx;
        //Log.d("X",Integer.toString(x));
        mPath.reset();
        //mPath.moveTo(-GamePanel.SCREEN_WIDTH/(stem_waves*2)+x,GamePanel.SCREEN_HEIGHT/2-stem_width/2);

        mPath.moveTo(0+x,GamePanel.SCREEN_HEIGHT/2-stem_width/2);

        int k=1;//up
        for(int i=0;i<2*stem_waves+2;i++){
            k=k*-1;
            mPath.quadTo((i*2+1)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+(2*k-1)*stem_width/2, (i*2+2)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2-stem_width/2);
        }

        mPath.lineTo(GamePanel.SCREEN_WIDTH+GamePanel.SCREEN_WIDTH/stem_waves+x,GamePanel.SCREEN_HEIGHT/2+stem_width/2);
        k=k*-1;

        for(int i=2*stem_waves+2;i>0;i--){
            k=k*-1;
            mPath.quadTo((i*2-1)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+(2*k+1)*stem_width/2, (i*2-2)*GamePanel.SCREEN_WIDTH/(stem_waves*4)+x, GamePanel.SCREEN_HEIGHT/2+stem_width/2);
        }
        mPath.lineTo(0+x, GamePanel.SCREEN_HEIGHT/2-stem_width/2);
    }

    public void draw(Canvas canvas){
        canvas.drawPath(mPath, mPaint);

        //canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);
    }
    public int getX(){return x;}

}
