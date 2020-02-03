package edu.neu.madcourse.numad20s_rongyichen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * For Assignment 1, this About activity had a button for display name and email of author
 */
public class AboutActivity extends AppCompatActivity {
    private TextView myNameAndEmail;
    private Button buttonAbout;
    private boolean isTextDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        myNameAndEmail = (TextView)findViewById(R.id.myNameAndEmail);
        myNameAndEmail.setVisibility(View.GONE);
        Button buttonAbout = (Button)findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTextDisplayed) {
                    myNameAndEmail.setVisibility(View.VISIBLE);
                    isTextDisplayed = true;
                } else {
                    myNameAndEmail.setVisibility(View.GONE);
                    isTextDisplayed = false;
                }
            }
        });
    }
}