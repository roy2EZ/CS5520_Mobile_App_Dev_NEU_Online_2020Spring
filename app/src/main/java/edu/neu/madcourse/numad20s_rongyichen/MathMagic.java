package edu.neu.madcourse.numad20s_rongyichen;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.PowerManager;
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
public class MathMagic extends AppCompatActivity {

    private static final String TAG = "PrimeActivity";
    private volatile boolean startRequested;
    IntentFilter iFilter = new IntentFilter();
    final IntentFilter filter = new IntentFilter();
    private BroadcastReceiver mBatInfoReceiver;
    Intent intent = new Intent();

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
    }

    /**
     * **********************Part 1. to Find Primes ***********************************************
     * @param view
     */
    public void findPrimes(View view) {
        PrimeThread primeThread = new PrimeThread();
        primeThread.start();
    }

    class PrimeThread extends Thread {

        TextView numberCount = findViewById(R.id.numberCount);
        TextView primeNumber = findViewById(R.id.prime);
        String prime = "This is a prime number.";
        private Button mButtonStopPrime = findViewById(R.id.stopPrimes);

        @Override
        public void run() {
            startRequested = true;
            int i = 1;
            while (startRequested) {
                Log.d(TAG, "The current number is: " + i);
                mButtonStopPrime.setVisibility(View.INVISIBLE);
                mButtonStopPrime.setVisibility(View.VISIBLE);

                //numbers to be displayed
                numberCount.setText(Integer.toString(i));

                //prime to be displayed
                if (isPrime(i)) {
                    Log.d(TAG, i + " is prime!");
                    primeNumber.setText(prime);
                } else {
                    Log.d(TAG, i + " is not prime!");
                    primeNumber.setText("");
                }
                try {
                    i += 2;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Helper function to find prime number
     * @param n
     * @return
     */
    private boolean isPrime(int n) {
        if(n <= 3){
            return n > 1;
        }
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public void stopPrimes(View view) {
        startRequested = false;
    }

    /**
     * **********************Part 2. Watch Time***********************************************
     * @param view
     */
    public void watchTimePerMin(View view) {
        mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock screenLock
                        = ((PowerManager) getSystemService(POWER_SERVICE))
                        .newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
                screenLock.acquire();
                screenLock.release();
            }
        };
    }


    public void stopWatchTimePerMin(View view) {
        startRequested = false;
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
        registerReceiver(ChangingStateBroadcastReceiver,iFilter);
        registerReceiver(mBatInfoReceiver,filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(ChangingStateBroadcastReceiver,iFilter);
        registerReceiver(mBatInfoReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(ChangingStateBroadcastReceiver);
        unregisterReceiver(mBatInfoReceiver);
    }

}
