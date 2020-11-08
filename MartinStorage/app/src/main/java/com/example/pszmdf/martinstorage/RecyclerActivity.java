package com.example.pszmdf.martinstorage;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity{ //} implements SimpleRecyclerViewAdapter.ItemClickListener {

    SimpleRecyclerViewAdapter adapter;
    DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        Cursor cursor = dbAdapter.db.query("myList", new String[] { "_id", "name", "colour" }, null, null, null, null, null);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SimpleRecyclerViewAdapter(this, cursor);
        recyclerView.setAdapter(adapter);
    }


}
