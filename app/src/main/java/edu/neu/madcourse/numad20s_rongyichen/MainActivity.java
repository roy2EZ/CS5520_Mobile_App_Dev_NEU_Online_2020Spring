package edu.neu.madcourse.numad20s_rongyichen;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * To represent the main activity of this app
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void enterAssignment1(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void enterAssignment2(View view) {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }

    public void enterAssignment3(View view) {
        Intent intent = new Intent(this, MathMagicActivity.class);
        startActivity(intent);
    }

    public void enterAssignment4(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void enterAssignment5(View view) {
        Intent intent = new Intent(this, WebLinkCollectors.class);
        startActivity(intent);
    }


}