package ultd.aptig.game;

/**
 * Created by RK on 10/2/2016.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Notes extends GameObject{

    public char tone;
    public boolean clicked = false;
    private Bitmap spritesheet;
    Bitmap rotatedBitmap;
    public Notes(Bitmap spritesheet, int x,int y,char tone){
        super.x = x;
        super.y = y;
        this.tone = tone;
        this.spritesheet = spritesheet;

    }

    public void update(){
        x=x+GamePanel.MOVE_SPEED;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(spritesheet,x,y,null);
    }

    public int getY() {
        return y;
    }
    public boolean getClicked(){return clicked;}
    public char checkClick(float xc,float yc){
        if(xc>x && xc<x + 150 && yc>y && yc<150+y){
            {clicked = true;return tone;}
        }
        else return 'X';
    }
}
