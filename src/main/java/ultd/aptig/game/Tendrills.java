package ultd.aptig.game;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.WHITE;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Tendrills extends GameObject {
    private Path mPath;
    private Paint mPaint;
    private int x=0,y=0,dx;
    private int tend_width = GamePanel.SCREEN_WIDTH/128;
    private int tend_length;
    private float tend_angle;

    public Tendrills(int x,int y,float angle, int len,int width) {
        tend_length = len;
        mPath = new Path();
        mPaint = new Paint();
        super.x=x;super.y=y;tend_length=len;tend_width=width;tend_angle=angle;
        mPaint.setColor(0xFF157C41);
        mPath.moveTo(x,y+(len/2)*((float)sin(angle)));
        mPath.rQuadTo(0,-(len/2)*((float)sin(angle)),(len/2)*((float)cos(angle)),-(len/2)*((float)sin(angle)));
        mPath.rLineTo(width*((float)sin(angle)),width*((float)cos(angle)));
        mPath.rQuadTo(-(len/2)*((float)cos(angle)),0,-(len/2)*((float)cos(angle)),(len/2)*((float)sin(angle)));
        mPath.lineTo(x,y+(len/2)*((float)sin(angle)));
        dx = GamePanel.MOVE_SPEED;
    }

    public void update(){
        if(x<0){
            x=GamePanel.SCREEN_WIDTH;
        }
        x = x+ dx;
        //Log.d("X",Integer.toString(x));
        mPath.reset();
        mPath.moveTo(x,y+(tend_length/2)*((float)sin(tend_angle)));
        mPath.rQuadTo(0,-(tend_length/2)*((float)sin(tend_angle)),(tend_length/2)*((float)cos(tend_angle)),-(tend_length/2)*((float)sin(tend_angle)));
        mPath.rLineTo(tend_width*((float)sin(tend_angle)),tend_width*((float)cos(tend_angle)));
        mPath.rQuadTo(-(tend_length/2)*((float)cos(tend_angle)),0,-(tend_length/2)*((float)cos(tend_angle)),(tend_length/2)*((float)sin(tend_angle)));
        mPath.lineTo(x,y+(tend_length/2)*((float)sin(tend_angle)));
        //mPath.quadTo();


    }

    public void draw(Canvas canvas){
        canvas.drawPath(mPath, mPaint);

        //canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);
    }

}
