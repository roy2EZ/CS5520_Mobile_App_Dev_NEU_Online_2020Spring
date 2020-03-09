package edu.neu.madcourse.numad20s_rongyichen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class WebLinkCollectors extends AppCompatActivity implements WebsiteListAdapter.OnWebClickListener {
    private static final String TAG = "WebLinkCollector";
    private List<Websites> mWebList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainViewModel mViewModel;
    private WebsiteListAdapter adapter;



    private EditText inputName;
    private EditText inputUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_link_collectors);

        mRecyclerView = findViewById(R.id.web_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager(this);

        inputName = findViewById(R.id.input_name);
        inputUrl = findViewById(R.id.input_url);

        this.mWebList = new LinkedList<>();

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void listenerSetup() {
        FloatingActionButton fab = findViewById(R.id.btn_add_website);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String newWebName = inputName.getText().toString();
                    String newWebUrl = inputUrl.getText().toString();
                    // check whether url is empty
                    if (newWebUrl.isEmpty())
                        throw new IllegalArgumentException("url cannot be empty!");
                    // reformat the url
                    if (!newWebUrl.startsWith("https://") && !newWebUrl.startsWith("http://"))
                        newWebUrl = "http://" + newWebUrl;

                    Websites newWeb = new Websites(newWebName, newWebUrl);
                    mViewModel.insertWebsite(newWeb);
                    clearFields();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(),
                            "Url cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





    private void observerSetup() {
        mViewModel.getAllWebs().observe(this, new Observer<List<Websites>>() {
            @Override
            public void onChanged(@Nullable final List<Websites> websites) {
                if(mWebList.size() > 0){
                    mWebList.clear();
                }
                if(websites != null){
                    mWebList.addAll(websites);
                }
                adapter.notifyDataSetChanged();

                adapter.setmWebItem(websites);
            }
        });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new WebsiteListAdapter(R.layout.web_item, this);
        recyclerView = findViewById(R.id.web_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void clearFields() {
        inputName.setText("");
        inputUrl.setText("");
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper
            .SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d(TAG, "adapter pos: " + viewHolder.getAdapterPosition());
            mViewModel.deleteWebsite(adapter.psoIDtoItemId(viewHolder.getAdapterPosition()));
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mWebList.get(pos).getWeb_url()));
        Log.d(TAG, "onItemClick: " + intent.toString());
        startActivity(intent);
    }
}