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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;
import java.util.List;

public class LinkCollector extends AppCompatActivity {
    private List<Item> mUrlList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText edittextName;
    private EditText edittextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector_layout);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        FloatingActionButton fab = findViewById(R.id.btn_plus_card);

        edittextName = findViewById(R.id.edittext_name);
        edittextUrl = findViewById(R.id.edittext_url);

        this.mUrlList = new LinkedList<>();

        mAdapter = new MyAdapter(mUrlList, new MyAdapter.OnItemClickListener() {
            @Override public void onItemClick(Item item) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                startActivity(intent);
            }});
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item(edittextName.getText().toString(), edittextUrl.getText().toString());
                mUrlList.add(item);
                Snackbar.make(v, "Item added to list", Snackbar.LENGTH_LONG) .setAction("Undo", undoOnClickListener).show();
                mAdapter.notifyDataSetChanged();
            }

        });
    }

    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
            mUrlList.remove(mUrlList.size() -1);
            mAdapter.notifyDataSetChanged();
            Snackbar.make(view, "Item removed", Snackbar.LENGTH_LONG) .setAction("Action", null).show();
        }
    };


}

