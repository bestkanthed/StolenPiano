package ultd.aptig.piano;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
//import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by RK on 5/8/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback , GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    // for me sharps are capital

    //String notes = "caggccffgacddfdcddfdAcccgagggaAagcgaggagffcagfcAagfgagffaggffefgdaAagffgaagfccgagaAagfffgaggafgaagAcAgeffafafefcfefafafefcfedfdfddefaefgcgcgcfgagfeffafafagAfaefcfeffafafagAfaefcfedfAfAfAacfAdefaefecgcgfefac-cc-cfgccefcfefacccdcAag-ggfedefAaadefaggagfedefAaafgafgaccdcfgdcadaga-aaAcAccdcfaAdfadaga-aaAcAccgaAcAgecaAagagfefedecd-ddefeffgfdAcafefcdDddcAgdccacacAdacacfefcdDddcAgdcc-cdDdDfDdcefefcdDddcAgdcafgafefcdDddcAgdccAcdDdccAccAcdDffAAcaAdfAegcaccc-cfgccefcfefacccdcAag-ggfedefAaadefaggagfedefAaafgafgaccdcfgdcAcfcAcAcAGADAGAGfAcAGfDfgGcAGgfAcAGffDfaAaAaaAaAacfgaaAagfcfefcdDddcAgdccfefcdDddcAgdccfefcdDddcAgdccfefcdDddcAgdcc-cAcdDdccAc-cAcdDffAAcaAdegaccc-cfgcc-efcfefaccc-dcAagggfedefAaadefaggagfe-defAaa-fgafg-accd-cfgdc";
    static String notes = "dddagafgAa-dddagafgfe-decdecdfe-decdecfed";///////////////////DDLJ////////////////////////////////////
    //static String notes = "babagbcdcbgFgeegaababagbFgeeccdddfedcbaabbbcbabbbcbgaaacbgF";
    //String notes = "dddagafgAadddagafgfedecdecdfedecdecfed";
    //String notes = "CbCbCbCEDCbababCbCbCbCEDCbababgaCaCafgagaCaCagffAagag";
    //static String notes = "ggfDdf,AcddDfgDAAAGgGcddddcdDD";Chu kar
    //static String notes = "gbcbcbdcabcbcbdcacdcdcedacdcdceddccbbaagggababcdeeddccbdcabcbcbdcabcbcbdcabcbcbdcacdcdcedacdcdceddccbbaagggababcdeeddccbdcedededeggfededccbcbcbceeaagfeddegfeddeeddccbbgggababcdeeddccbdc";
    //static String notes = "cdeeeeeeeeedef eeedddbdc ccggggggggggaf fffffeddfe eedeedeggaff eeeeeddbdc cdeeeedf efgggfedfe feeeddbdc ccggggeggggga fffffedfe egcbabaga ccddeedef\n";//Jana gana mana
    //static String notes ="eeecdedcdeeeecded";
    //static String notes ="AbCAbAbGFFGFe eFGGGAGGFbGFe eFGGGAGGb";/// tere naam
    //static String notes = "C fDDCCcAGACccc AAGAAGgfDf C fDDCCcAGACccc AAGAAGgfDf ccfDCCcAAcc cAAAGAAgfDD ";//tum hi hp
    //static String notes ="fGcCGAg fGcCGAg fGcCGAg fGcCGAg";
    //static String notes = "FCdCF FCdCF  FCdCF FCdCF bCdCbCdbFedCbCa abCa abcaedcdag cbAbCAbCAbcA";
    public int StringPointer = 0,StringPointert = 0;

    public static final int WIDTH =  1024;///my bg
    public static final int HEIGHT = 720;
    public static int MOVE_SPEED = -5;//speed of my frame
    private static long tilesStartTime = System.nanoTime();
    private MainThread thread;
    private Background bg;
    private ArrayList<Tiles> tiles;
    private Got got;
    private GestureDetector gestureDetector;
    private Random rand = new Random();
    long y=0;
    public static float xc,yc;
    //private ArrayList<Layer> layers;


    MediaPlayer mpsong,mpa,mpb,mpc,mpd,mpe,mpf,mpg,mpA,mpC,mpD,mpF,mpG,mpa2,mpb2,mpc2,mpd2,mpe2,mpf2,mpg2,mpA2,mpC2,mpD2,mpF2,mpG2,mpa3,mpb3,mpc3,mpd3,mpe3,mpf3,mpg3,mpA3,mpC3,mpD3,mpF3,mpG3;
    int a=0,b=0,c=0,d=0,e=0,f=0,g=0,A=0,C=0,D=0,F=0,G=0;
  /*  /// Music privates
    private final float duration = (float) 0.2; // seconds
    private final int sampleRate = 8000;
    private final int numSamples = (int)(duration * sampleRate);
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 440; // hz
    private final byte generatedSnd[] = new byte[2 * numSamples];

    private AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
            sampleRate, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
            AudioTrack.MODE_STATIC);


    /// sound pool privates
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();

*/

    //////////////////

    public GamePanel(Context context){

        super(context);
        getHolder().addCallback(this);// call back to surface holder to intercept events
        thread = new MainThread(getHolder(), this);
        // Set up gesture detector
        this.gestureDetector = new GestureDetector(context,this);
        //make gamePanel focusable so it can handle events

        // set up music
        //this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
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
        mpsong = MediaPlayer.create(context, R.raw.asong);

        //mSounds.put(R.raw.a1, this.mShortPlayer.load(context, R.raw.a1, 1));
        //mSounds.put(R.raw.f1, this.mShortPlayer.load(context, R.raw.f1, 1));

        setFocusable(true);
    }



    ////////////////////////// MUSIC ///////////////////////
    /*
    public void playShortResource(int piResource) {
            int iSoundId = (Integer) mSounds.get(piResource);
            this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
    }

    public void release() {
            // Cleanup
            this.mShortPlayer.release();
            this.mShortPlayer = null;
    }


    void genTone(int n){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
            }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            switch (n) {
                case 1:
                    generatedSnd[idx++] = (byte) (val & 0x00ff);//0x00ff
                    generatedSnd[idx++] = (byte) ((val & 0x4400) >>> 8);//0xff00//4400
                    break;
                case 2:
                    generatedSnd[idx++] = (byte) (val & 0x55ff);//0x00ff
                    generatedSnd[idx++] = (byte) ((val & 0x9900) >>> 8);//0xff00//4400
                    break;
                case 3:
                    generatedSnd[idx++] = (byte) (val & 0xaaff);//0x00ff
                    generatedSnd[idx++] = (byte) ((val & 0xaa00) >>> 8);//0xff00//4400
                    break;
                case 4:
                    generatedSnd[idx++] = (byte) (val & 0xffff);//0x00ff
                    generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);//0xff00//4400
                    break;
            }
        }
    }

    void playSound(){

        try {
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                    AudioTrack.MODE_STATIC);
            audioTrack.write(generatedSnd, 0, generatedSnd.length);
            audioTrack.play();
        }catch (Exception e){
            audioTrack.flush();
            audioTrack.release();
        }

    }
*/
    //////// SURFACE HOLDER METHOD  ///////////

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.back));
        //player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.helicopter),65,25,3);
        tiles = new ArrayList<Tiles>();
        got = new Got(700,10);
        //missiles = new ArrayList<Missile>();
        //missileElapsed = System.nanoTime();
        //tilesStartTime = System.nanoTime();



        thread.setRunning(true);
        thread.start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

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
    /////////////////// Gesture Methods ///////////////////////////


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        char i='X';
        xc = (event.getX()*WIDTH)/MainActivity.width;
        yc = (event.getY()*HEIGHT)/MainActivity.height;
        Log.v("example", "Touch: {x:" + xc + " } {y:" + yc + "}");
        if(got.checkClicked(xc,yc)){
            Intent intent = new Intent(getContext(),Question.class);
            Log.v("example", "still ended");//yes... yes ... i got it... yes i will develop a very good ui and ux for those people who have hire me
            //intent.putExtra("isTitleShow",false);
            //startActivity(intent);
        }
        for(Tiles t:tiles){
            i = t.checkClick(xc,yc);
            if(i!='X')break;//switch (i){
            //  case 0:break;
            //default:genTone(i);
            //}//eff loop
        }
        got.Getclicked();

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

        StringPointer++;
        //try{playShortResource(0);}catch(Exception e){e.printStackTrace();}//playSound();


        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);


    }




    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    //////////////////  Draw method  ///////////////////////////
    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {

            final int savedState = canvas.save();//scaling
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            got.draw(canvas);

           for(Tiles t:tiles){
               t.draw(canvas);//eff loop
           }

            canvas.restoreToCount(savedState);
        }
    }

    //////////////  update method ///////////////////////////////////////////////
    public void update(){


        //if(player.getPlaying()) {
        bg.update();
        //smoke
        long elapsed = (System.nanoTime() - tilesStartTime)/1000000 ;//(in milli seconds)
        if(y+4==144){
            switch(notes.charAt(StringPointert)){
                case 'a':tiles.add(new Tiles(0*(WIDTH/4),-HEIGHT/5,'a'));break;
                case 'e':tiles.add(new Tiles(0*(WIDTH/4),-HEIGHT/5,'e'));break;
                case 'C':tiles.add(new Tiles(0*(WIDTH/4),-HEIGHT/5,'C'));break;
                case 'b':tiles.add(new Tiles(1*(WIDTH/4),-HEIGHT/5,'b'));break;
                case 'f':tiles.add(new Tiles(1*(WIDTH/4),-HEIGHT/5,'f'));break;
                case 'D':tiles.add(new Tiles(1*(WIDTH/4),-HEIGHT/5,'D'));break;
                case 'c':tiles.add(new Tiles(2*(WIDTH/4),-HEIGHT/5,'c'));break;
                case 'g':tiles.add(new Tiles(2*(WIDTH/4),-HEIGHT/5,'g'));break;
                case 'F':tiles.add(new Tiles(2*(WIDTH/4),-HEIGHT/5,'F'));break;
                case 'd':tiles.add(new Tiles(3*(WIDTH/4),-HEIGHT/5,'d'));break;
                case 'A':tiles.add(new Tiles(3*(WIDTH/4),-HEIGHT/5,'A'));break;
                case 'G':tiles.add(new Tiles(3*(WIDTH/4),-HEIGHT/5,'G'));break;
            }
            StringPointert++;
            //tiles.add(new Tiles((int)(rand.nextDouble()*4)*(WIDTH/4),-HEIGHT/5));
            //layers.add(new Layer(0,-1000));
            tilesStartTime = System.nanoTime();
            y=0;
        }

        for(int i=0; i<tiles.size();i++){
            tiles.get(i).update();
            if(tiles.get(i).getY()>2000){
                tiles.remove(i);
            }
        }
        y=y+10;
        /*for(int i=0; i<layers.size();i++){
            layers.get(i).update();
            if(layers.get(i).getY()>2000){
                layers.remove(i);
            }
        }*/

        //  player.update();

        //}
    }

    public static void setSong(String song){
        if(song.equals("Ham dil de chuke saman"))
            notes = "dddagafgAa-dddagafgfe-decdecdfe-decdecfed";
        else if(song.equals("Don't matter no"))
            notes = "caggccffgacddfdcddfdAcccgagggaAagcgaggagffcagfcAagfgagffaggffefgdaAagffgaagfccgagaAagfffgaggafgaagAcAgeffafafefcfefafafefcfedfdfddefaefgcgcgcfgagfeffafafagAfaefcfeffafafagAfaefcfedfAfAfAacfAdefaefecgcgfefac-cc-cfgccefcfefacccdcAag-ggfedefAaadefagga";
        else notes = "CbCbCbCEDCbababCbCbCbCEDCbababgaCaCafgagaCaCagffAagag";

    }


}
