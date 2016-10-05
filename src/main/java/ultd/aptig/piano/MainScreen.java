package ultd.aptig.piano;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by RK on 5/16/2016.
 */
public class MainScreen extends Activity{

    public static int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Log.v("example", "its working");
        Log.v("example", "Not working");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        Log.v("example", "Touch: {x:" + width + " } {y:" + height + "}");
        setContentView(new GamePanel(this));

    }

    public void MainScreen(){

        Log.v("example", "its working1");
    }
    public void Play(String song){

        Log.v("example", "its working2");
        GamePanel.setSong(song);


    }
}
