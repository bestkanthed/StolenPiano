package ultd.aptig.piano;

/**
 * Created by RK on 5/10/2016.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Tiles{

    public int r;
    public int x;
    public int y=0;
    public char tone;
    public boolean clicked = false;
    public Tiles(int x,int y,char tone){
        this.x = x;
        this.y = y;
        this.tone = tone;


    }

    public void update(){
        y +=10;
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        if(!clicked)
            paint.setColor(Color.BLACK);
        else
            paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawRect(x, y,x + GamePanel.WIDTH / 4, GamePanel.HEIGHT/ 5 + y, paint);
        //canvas.drawCircle(x-r+2,y-r+2,r,paint);
        //canvas.drawCircle(x-r+4,y-r+1,r,paint);
    }

    public int getY() {
        return y;
    }
    public boolean Getclicked(){return clicked;}
    public char checkClick(float xc,float yc){
        if(xc>x && xc<x + GamePanel.WIDTH/4 && yc>y && yc<y+GamePanel.HEIGHT/5){
            {clicked = true;return tone;}
        }
        else return 'X';
    }
}
