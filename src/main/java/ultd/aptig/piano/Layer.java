package ultd.aptig.piano;


/**
 * Created by RK on 5/10/2016.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Abhishek Kanthed on 5/5/2016.
 */
public class Layer{

    private Random rand = new Random();

    public int r;
    public int x;
    public int y=0;
    private ArrayList<Tiles> tiles;
    public int tilePlace=0;

    public Layer(int x,int y){
        this.x = x;
        this.y = y;
        tilePlace = (int)(rand.nextDouble()*4);
        tiles.add(new Tiles((GamePanel.WIDTH*tilePlace)/5,-1000,'a'));

    }

    public void update(){
        y +=1;
        for(int i=0; i<tiles.size();i++) {
            tiles.get(i).update();
            //if (tiles.get(i).getY() > 2000) {
              //  tiles.remove(i);
            //}
        }
    }
    public void draw(Canvas canvas){


        for(Tiles t:tiles){
          t.draw(canvas);//eff loop
        }

        /*Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawRect(0, y,GamePanel.WIDTH / 5, GamePanel.HEIGHT/ 5 + y, paint);
        //canvas.drawCircle(x-r+2,y-r+2,r,paint);
        //canvas.drawCircle(x-r+4,y-r+1,r,paint);*/
    }

    public int getY() {
        return y;
    }
}
