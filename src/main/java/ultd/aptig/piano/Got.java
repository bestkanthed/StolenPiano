package ultd.aptig.piano;

/**
 * Created by RK on 5/20/2016.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by RK on 5/10/2016.
 */
/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Got{

    public int r;
    public int x=0;
    public int y=0;
    public boolean clicked = false;
    public Got(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void update(){
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        if(!clicked)
            paint.setColor(Color.RED);
        else
            paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawRect(x, y, x + 100, y + 40, paint);
        paint.setColor(Color.CYAN);
        paint.setTextSize(40);
        canvas.drawText("Got it", x, y+40, paint);
        //canvas.drawCircle(x-r+2,y-r+2,r,paint);
        //canvas.drawCircle(x-r+4,y-r+1,r,paint);
    }

    public int getY() {
        return y;
    }
    public boolean checkClicked(float xc,float yc) {
        if(xc>x && xc<x + GamePanel.WIDTH/4 && yc>y && yc<y+GamePanel.HEIGHT/5)clicked = true;
        else clicked = false;
        return clicked;
    }
    public boolean Getclicked(){return clicked;}
}

