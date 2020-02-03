package edu.neu.madcourse.numad20s_rongyichen;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterAssignment1(View view) {
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    public void enterAssignment2(View view) {
        Intent i = new Intent(this, LinkCollector.class);
        startActivity(i);
    }
    
}