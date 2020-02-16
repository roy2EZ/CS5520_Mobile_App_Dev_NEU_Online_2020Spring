package edu.neu.madcourse.numad20s_rongyichen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;
import java.util.List;

/**
 * Homework 2
 * To represent the Link Collector activity
 * which can save user input websites and go to a website on click
 */
public class LinkCollectorActivity extends AppCompatActivity {
    private List<Website> myUrlList;
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private EditText edittextName;
    private EditText edittextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector_layout);

        myRecyclerView = findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);

        FloatingActionButton fab = findViewById(R.id.btn_plus_card);

        edittextName = findViewById(R.id.edittext_name);
        edittextUrl = findViewById(R.id.edittext_url);

        this.myUrlList = new LinkedList<>();

        myAdapter = new Adapter(myUrlList, new Adapter.OnItemClickListener() {
            @Override public void onItemClick(Website Website) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Website.getUrl()));
                startActivity(intent);
            }});
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

        /**
         * To add website after user click on Add button
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Website Website = new Website(edittextName.getText().toString(), edittextUrl.getText().toString());
                myUrlList.add(Website);
                Snackbar.make(v, "Website added to list", Snackbar.LENGTH_LONG) .setAction("Undo", undoOnClickListener).show();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * To remove the saved website after user click on undo
     */
    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
            myUrlList.remove(myUrlList.size() -1);
            myAdapter.notifyDataSetChanged();
            Snackbar.make(view, "Website removed", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
        }
    };


}


