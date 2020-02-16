package edu.neu.madcourse.numad20s_rongyichen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class MathMagicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("in the receiver");
        Toast.makeText(context, "Right now the time is: " + Calendar.getInstance().getTime().toString(), Toast.LENGTH_LONG).show();


    }
}
