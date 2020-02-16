package edu.neu.madcourse.numad20s_rongyichen;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.*;

/**
 * For Assignment 3, add three functions in Math Magic activity
 */
@SuppressWarnings("ALL")
public class MathMagicActivity extends AppCompatActivity {

    private static final String TAG = "PrimeActivity";
    private volatile boolean startRequested;
    IntentFilter iFilter = new IntentFilter();
    final IntentFilter filter = new IntentFilter();
    private BroadcastReceiver mBatInfoReceiver;
    Intent intent = new Intent();
    private boolean isFindingPrime;
    private TextView primeResView;
    private Button findPrimeBtn;
    private Button stopPrimeBtn;
    private String primeStr = "This is a prime number: ";

    private AsyncTask primeFinderTask;

    private Button startWatchTime;
    private Button stopWatchTime;
    private AlarmManager alarmMng;
    private PendingIntent alarmPending;
    private TextView watchTimeStatus;
    private boolean timeWatchFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_magic);

        iFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(ChangingStateBroadcastReceiver, iFilter);

        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mBatInfoReceiver, filter);

        primeResView = (TextView) findViewById(R.id.primeNumberView);
        findPrimeBtn = findViewById(R.id.findPrime);
        stopPrimeBtn = findViewById(R.id.stopPrimeButton);
        primeResView.setText("Prime starting:");

        startWatchTime = findViewById(R.id.startWatchTimeBtn);
        stopWatchTime = findViewById(R.id.stopTimeWatchBtn);

        alarmMng = null;
        alarmPending = null;
        watchTimeStatus = findViewById(R.id.watchTimeTitle);

        timeWatchFlag = false;
        isFindingPrime = false;

    }

    /**
     * **********************Part 1. Prime Number***********************************************
     *
     */

    private class PrimeNumberFinder extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            primeResView.setText(primeStr + values[0]);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            int curNumber = 1;

            while (!isCancelled()) {
                boolean primeFlag = true;
                if(curNumber <= 3){
                    publishProgress(curNumber);
                    primeFlag = false;
                } else {
                    for(int i = 2; i < curNumber; i++){
                        if (isCancelled()) {
                            break;
                        }
                        if (curNumber % i == 0) {
                            primeFlag = false;
                            i = curNumber;
                        }
                    }
                }

                if (primeFlag) {
                    publishProgress(curNumber);
                }
                curNumber++;
            }


            return null;
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            primeResView.setText("Prime Number Finding Stopped.");
        }
    }

    public void findPrime(View view) {
        isFindingPrime = true;
        primeFinderTask = new PrimeNumberFinder().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void stopPrime(View view) {
        if (isFindingPrime) {
            primeFinderTask.cancel(true);
            isFindingPrime = false;
        }
    }



    /**
     * **********************Part 2. Watch Time***********************************************
     *
     */

    public void startTimeWatch(View view) {
        timeWatchFlag = true;
        watchTimeStatus.setText("Watch Time function is ON.");
        alarmMng = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MathMagicReceiver.class);
        alarmPending = PendingIntent.getBroadcast(this, 0, intent,0);
        alarmMng.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),
                60*1000,alarmPending);

    }

    public void stopTimeWatch(View view) {
        if (timeWatchFlag) {
            watchTimeStatus.setText("Watch Time function is OFF.");
            alarmMng.cancel(alarmPending);
            timeWatchFlag = false;
        }
    }




    /**
     * **********************Part 3. To Display "POWER!!!" OR "battery" ***************************
     *
     */
    private final BroadcastReceiver ChangingStateBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            final String action = intent.getAction();
            if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(context, "POWER!!!", Toast.LENGTH_SHORT).show();
            }
            else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(context, "battery", Toast.LENGTH_SHORT).show();
            }
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
