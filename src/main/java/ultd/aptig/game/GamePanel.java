package ultd.aptig.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLACK;

/**
 * Created by Abhishek Kanthed on 5/4/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //HUMP = x + WIDTH/8

    static String note = "dddagafgAa-dddagafgfe-decdecdfe-decdecfed";
    //static String note = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    public static final int WIDTH =  1280;///my bg
    public static final int HEIGHT = 720;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int MOVE_SPEED = -10;//speed of my frame
    public static int SONG_PLAY_SPEED = 2000;
    private long monsterStartTime;
    private long thornStartTime;
    private long thornElapsed;
    private MainThread thread;
    private Background bg;
    private Player player;
    private Stem stem;
    private ArrayList <Notes> notes;
    private ArrayList<Thorns> thorns;
    private ArrayList<Monster> monsters;
    private Tendrills tendrills;
    private ArrayList<SmokePuff> smoke;
    private ArrayList<Flower> flowers;
    private ArrayList<WoodThorn> woodThorns;
    private ArrayList<Missile> missiles;
    public int StringPointer = 0,StringPointert = 0;
    private Random rand = new Random();

    MediaPlayer mpsong,mpa,mpb,mpc,mpd,mpe,mpf,mpg,mpA,mpC,mpD,mpF,mpG,mpa2,mpb2,mpc2,mpd2,mpe2,mpf2,mpg2,mpA2,mpC2,mpD2,mpF2,mpG2,mpa3,mpb3,mpc3,mpd3,mpe3,mpf3,mpg3,mpA3,mpC3,mpD3,mpF3,mpG3;
    int a=0,b=0,c=0,d=0,e=0,f=0,g=0,A=0,C=0,D=0,F=0,G=0;


    public GamePanel(Context context){

        super(context);
        getHolder().addCallback(this);// call back to surface holder to intercept events

        thread = new MainThread(getHolder(), this);

        mpa = MediaPlayer.create(context, R.raw.a1);
        mpb = MediaPlayer.create(context, R.raw.b1);
        mpc = MediaPlayer.create(context, R.raw.c1);
        mpd = MediaPlayer.create(context, R.raw.d1);
        mpe = MediaPlayer.create(context, R.raw.e1);
        mpf = MediaPlayer.create(context, R.raw.f1);
        mpg = MediaPlayer.create(context, R.raw.g1);
        mpA = MediaPlayer.create(context, R.raw.a1s);
        mpC = MediaPlayer.create(context, R.raw.c1s);
        mpD = MediaPlayer.create(context, R.raw.d1s);
        mpF = MediaPlayer.create(context, R.raw.f1s);
        mpG = MediaPlayer.create(context, R.raw.g1s);
        mpa2 = MediaPlayer.create(context, R.raw.a1);
        mpb2 = MediaPlayer.create(context, R.raw.b1);
        mpc2 = MediaPlayer.create(context, R.raw.c1);
        mpd2 = MediaPlayer.create(context, R.raw.d1);
        mpe2 = MediaPlayer.create(context, R.raw.e1);
        mpf2 = MediaPlayer.create(context, R.raw.f1);
        mpg2 = MediaPlayer.create(context, R.raw.g1);
        mpA2 = MediaPlayer.create(context, R.raw.a1s);
        mpC2 = MediaPlayer.create(context, R.raw.c1s);
        mpD2 = MediaPlayer.create(context, R.raw.d1s);
        mpF2 = MediaPlayer.create(context, R.raw.f1s);
        mpG2 = MediaPlayer.create(context, R.raw.g1s);
        mpa3 = MediaPlayer.create(context, R.raw.a1);
        mpb3 = MediaPlayer.create(context, R.raw.b1);
        mpc3 = MediaPlayer.create(context, R.raw.c1);
        mpd3 = MediaPlayer.create(context, R.raw.d1);
        mpe3 = MediaPlayer.create(context, R.raw.e1);
        mpf3 = MediaPlayer.create(context, R.raw.f1);
        mpg3 = MediaPlayer.create(context, R.raw.g1);
        mpA3 = MediaPlayer.create(context, R.raw.a1s);
        mpC3 = MediaPlayer.create(context, R.raw.c1s);
        mpD3 = MediaPlayer.create(context, R.raw.d1s);
        mpF3 = MediaPlayer.create(context, R.raw.f1s);
        mpG3 = MediaPlayer.create(context, R.raw.g1s);
        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true; //for multiple attempts
            int counter = 0;
            while (retry&&counter<1000){
                try{
                    counter++;
                    thread.setRunning(false);
                    thread.join();
                    retry = false;
                }catch (InterruptedException e){e.printStackTrace();}

            }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        SCREEN_HEIGHT = getHeight();
        SCREEN_WIDTH = getWidth();
        stem = new Stem();
        tendrills = new Tendrills(200,300,(float)-1,300,10);
        player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.helicopter),65,25,3);

        //Log.d("TAG",Float.toString(getHeight())+" "+Float.toString(getWidth()));
        bg = new Background(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.jungle),getWidth(),getHeight(),false));
        notes = new ArrayList<Notes>();
        woodThorns = new ArrayList<>();

        monsters= new ArrayList<Monster>();
        flowers = new ArrayList<Flower>();

        thorns = new ArrayList<Thorns>();
        thornElapsed = System.nanoTime();
        thornStartTime = System.nanoTime();

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying()){
                player.setPlaying(true);
            }
            else{
                player.setUp(true);
            }
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_UP){
            player.setUp(false);
            return true;
        }

        float xc = event.getX();
        float yc = event.getY();
        Log.v("example", "Touch: {x:" + xc + " } {y:" + yc + "}");
        char i='X';
        for (Notes n:notes){
            i = n.checkClick(xc,yc);
            Log.e("Check Click",Boolean.toString(n.getClicked()));
            if(i!='X')break;
        }

        switch(i){
            case 'a': if(a%3==0)mpa.start();else if(a%3==1)mpa3.start(); else mpa2.start();a++;break;
            case 'b': if(b%3==0)mpb.start();else if(b%3==1)mpb3.start(); else mpb2.start();b++;break;
            case 'c': if(c%3==0)mpc.start();else if(c%3==1)mpc3.start(); else mpc2.start();c++;break;
            case 'd': if(d%3==0)mpd.start();else if(d%3==1)mpd3.start(); else mpd2.start();d++;break;
            case 'e': if(e%3==0)mpe.start();else if(e%3==1)mpe3.start(); else mpe2.start();e++;break;
            case 'f': if(f%3==0)mpf.start();else if(f%3==1)mpf3.start(); else mpf2.start();f++;break;
            case 'g': if(g%3==0)mpg.start();else if(g%3==1)mpg3.start(); else mpg2.start();g++;break;
            case 'A': if(A%3==0)mpA.start();else if(A%3==1)mpA3.start(); else mpA2.start();A++;break;
            case 'C': if(C%3==0)mpC.start();else if(C%3==1)mpC3.start(); else mpC2.start();C++;break;
            case 'D': if(D%3==0)mpD.start();else if(D%3==1)mpD3.start(); else mpD2.start();D++;break;
            case 'F': if(F%3==0)mpF.start();else if(F%3==1)mpF3.start(); else mpF2.start();F++;break;
            case 'G': if(G%3==0)mpG.start();else if(G%3==1)mpG3.start(); else mpG2.start();G++;break;
        }


        return super.onTouchEvent(event);

    }

    public void update(){

        if(player.getPlaying()) {
            bg.update();
//            player.update();
            stem.update();
            tendrills.update();

            //thorn
            thornElapsed = (System.nanoTime()-thornStartTime)/1000000;
            if(stem.getX()+ WIDTH/8 == 0) {   //faster with score
                if (rand.nextBoolean())
                    thorns.add(new Thorns(BitmapFactory.decodeResource(getResources(), R.drawable.thorn), WIDTH + WIDTH/2,HEIGHT/3 - (int)(rand.nextDouble()*(HEIGHT/12)) , 45, 15, player.getScore(), 5,0));
                else
                    thorns.add(new Thorns(BitmapFactory.decodeResource(getResources(), R.drawable.thorn), WIDTH + WIDTH/2, HEIGHT/3, 45, 15, player.getScore(), 5,0));
                //reset timer
                thornStartTime = System.nanoTime();
            }

            //loop of destruction
            for(int i=0;i<thorns.size();i++){
                thorns.get(i).updated();
                if(collision(thorns.get(i),player)){

                        thorns.remove(i);
                        player.setPlaying(false);
                        break;
                }
                if(thorns.get(i).getX()<-100){
                    thorns.remove(i);
                    break;
                }

            }
//
//
//
            //monster
            long elapsed = (System.nanoTime() - monsterStartTime)/1000000 ;//(in milli seconds)
            double c = rand.nextDouble();
            if(c<0.6 && c >0.4) c= 0.7;
            if(stem.getX()+ WIDTH/8 == 0){
                monsters.add(new Monster(BitmapFactory.decodeResource(getResources(), R.drawable.monster), WIDTH + WIDTH/4, (int)(HEIGHT*c), 45, 15, player.getScore(), 5,(int)(rand.nextDouble()*360)));
                //monsterStartTime = System.nanoTime();
             }

            for(int i=0; i<monsters.size();i++){
                monsters.get(i).update();
                if(monsters.get(i).getX()<-300){
                    monsters.remove(i);
                }
            }

            if(stem.getX()+ WIDTH/8 == 0){
                flowers.add(new Flower(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.flower),(int)(c+2)*100,(int)(c+2)*100,false), WIDTH+WIDTH/8, (int)(HEIGHT*(1-c)),(int)(rand.nextDouble()*360)));
                //monsterStartTime = System.nanoTime();
            }

            for(int i=0; i<flowers.size();i++){
                flowers.get(i).update();
                if(flowers.get(i).getX()<-200){
                    flowers.remove(i);
                }
            }

            if(stem.getX()+ WIDTH/8 == 0){
                woodThorns.add(new WoodThorn(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.woodthorn),73,142,false), WIDTH+WIDTH/2, HEIGHT/2-HEIGHT/48,0));
                //monsterStartTime = System.nanoTime();
            }

            for(int i=0; i<woodThorns.size();i++){
                woodThorns.get(i).update();
                if(woodThorns.get(i).getX()<-200){
                    woodThorns.remove(i);
                }
            }

            if(stem.getX()+ WIDTH/8 == 0){
                switch(note.charAt(StringPointert)){
                    case 'a':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a),150,150,false), WIDTH+WIDTH/2 ,0,'a'));break;
                    case 'e':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.b),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'e'));break;
                    case 'C':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'C'));break;
                    case 'b':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.d),150,150,false), WIDTH+WIDTH/2 ,0,'b'));break;
                    case 'f':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.e),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'f'));break;
                    case 'D':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.f),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'D'));break;
                    case 'c':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.g),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'c'));break;
                    case 'g':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a),150,150,false), WIDTH+WIDTH/2 ,0,'g'));break;
                    case 'F':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'F'));break;
                    case 'd':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.d),150,150,false), WIDTH+WIDTH/2 ,0,'d'));break;
                    case 'A':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.f),150,150,false), WIDTH+WIDTH/2 ,HEIGHT/2+HEIGHT/6,'A'));break;
                    case 'G':notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.e),150,150,false), WIDTH+WIDTH/2 ,0,'G'));break;

                }
                StringPointert++;
                //notes.add(new Notes(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a),150,150,false), WIDTH + 10, (int)(rand.nextDouble() * (HEIGHT)),'a'));
                monsterStartTime = System.nanoTime();
            }

            for(int i=0; i<notes.size();i++){
                notes.get(i).update();
                if(notes.get(i).getX()<-200){
                    notes.remove(i);
                }
            }

        }
    }

    public boolean collision(GameObject a, GameObject b){
        if(Rect.intersects(a.getRectangle(),b.getRectangle())){
            return true;
        }
        else return false;
    }

    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        //Log.d("TAG",Float.toString(getHeight())+" "+Float.toString(getWidth()));
        if(canvas!=null) {

            final int savedState = canvas.save();//scaling
            //canvas.scale(scaleFactorX, scaleFactorY);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            bg.draw(canvas);
            stem.draw(canvas);
            tendrills.draw(canvas);

            for(Notes n:notes){
                if(!n.getClicked())
                n.draw(canvas);//eff loop
            }
            //player.draw(canvas);
            for(Thorns m:thorns){
                m.draw(canvas);
            }
            for(WoodThorn w:woodThorns){
                w.draw(canvas);//eff loop
            }
            for(Monster m:monsters){
                m.draw(canvas);//eff loop
            }
            for(Flower f:flowers){
                f.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }
    }
}
