package ultd.aptig.piano;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //Display display = getWindowManager().getDefaultDisplay();
    int first1=1;
    public static int width;
    public static int height;
    MainActivity m;// deprecated

   // int getWidth(){return width;}
    //int getHeight(){return height;}
    /*public void onClickThis(){

        Log.v("example", "ended");
        Intent intent = new Intent(this,MainScreen.class);
        Log.v("example", "still ended");
        //intent.putExtra("isTitleShow",false);
        startActivity(intent);
        Log.v("example", "finally ended");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        //Log.v("example", "Touch: {x:" + width + " } {y:" + height + "}");



        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(first1 == 0) {
            first1++;
            setContentView(R.layout.activity_main);
            //setContentView(new GamePanel(this));
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragment())
                        .commit();
            }

        }
        else {
            Intent intent = new Intent(this, MainScreen.class);
            Log.v("example", "still ended");
            //intent.putExtra("isTitleShow",false);
            startActivity(intent);
        }
        //setContentView(new GamePanel(this));*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void changeScreen(){
        Intent intent = new Intent(this,MainScreen.class);
        Log.v("example", "still ended");
        //intent.putExtra("isTitleShow",false);
        startActivity(intent);

    }
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> mForecastAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            // Create some dummy data for the ListView.  Here's a sample weekly forecast
            String[] data = {
                    "Ham dil de chuke saman",
                    "Don't matter no",
                    "Tujhe dekah to ye jana sanam",
                    "Phela Nasha",
                    "Kabhi Kabhi Aditi",
                    "Its Okay"
            };
            List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            mForecastAdapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_forecast, // The name of the layout ID.
                            R.id.list_item_forecast_textview, // The ID of the textview to populate.
                            weekForecast);

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            listView.setAdapter(mForecastAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        String forecast = mForecastAdapter.getItem(position);
                        //m.changeScreen();
                        //Intent intent = new Intent(MainActivity.this,MainScreen.class);
                        //Log.v("example", "still ended");
                        //intent.putExtra("isTitleShow",false);
                        //startActivity(intent);
                        Log.v("example", "finally ended");
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            });

            return rootView;
        }
    }
}