package com.flipclock;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by mulan on 21/1/21.
 */
public class MainActivity extends Activity implements View.OnClickListener, FlipLayout.FlipOverListener {

    private android.widget.EditText etInput;
    private android.widget.Button btnSet;
    private FlipLayout bit_hour;
    private FlipLayout bit_minute;
    private FlipLayout bit_second;
    private Calendar oldNumber = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.bit_second = (FlipLayout) findViewById(R.id.bit_flip_3);
        this.bit_minute = (FlipLayout) findViewById(R.id.bit_flip_2);
        this.bit_hour = (FlipLayout) findViewById(R.id.bit_flip_1);

        bit_hour.flip(oldNumber.get(Calendar.HOUR_OF_DAY),24,TimeTAG.hour);
        bit_minute.flip( oldNumber.get(Calendar.MINUTE),60,TimeTAG.min);
        bit_second.flip(oldNumber.get(Calendar.SECOND),60,TimeTAG.sec);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 1000, 1000);//每一秒执行一次
//        bit_hour.addFlipOverListener(this);
//        bit_minute.addFlipOverListener(this);
//        bit_second.addFlipOverListener(this);
    }



    @Override
    public void onClick(View v) {

        start();

    }

    @Override
    public void onFLipOver(FlipLayout flipLayout) {
//        if(flipLayout.isFlipping()){
//            flipLayout.smoothFlip(1, true);
//        }
    }
    public void  start(){
        Calendar now = Calendar.getInstance();
        int nhour = now.get(Calendar.HOUR_OF_DAY);
        int nminute = now.get(Calendar.MINUTE);
        int nsecond = now.get(Calendar.SECOND);

        int ohour = oldNumber.get(Calendar.HOUR_OF_DAY);
        int ominute = oldNumber.get(Calendar.MINUTE);
        int osecond = oldNumber.get(Calendar.SECOND);

        oldNumber = now;

        int hour = nhour - ohour;
        int minute = nminute - ominute;
        int second = nsecond- osecond;


        if (hour >= 1||hour==-23) {
            bit_hour.smoothFlip(1, 24,TimeTAG.hour,false);

        }

        if (minute >=1||minute==-59) {
            bit_minute.smoothFlip(1, 60,TimeTAG.min,false);

        }

        if (second >=1||second==-59) {
            bit_second.smoothFlip(1, 60,TimeTAG.sec,false);
        }//当下一秒变为0时减去上一秒是-59



    }
}
