package com.example.pszmdf.martinstorage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DatabaseActivity extends Activity {

    DatabaseAdapter dbAdapter;
    SimpleCursorAdapter dataAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(MainActivity.CONFIG_STORAGE_NAME, 0);
        if(settings.getBoolean(MainActivity.CONFIG_THEME, false))
            setTheme(android.R.style.Theme_Black);
        else
            setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_db);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        listView = (ListView) findViewById(R.id.listView);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

                TextView nameView = (TextView) view.findViewById(R.id.nameView);
                TextView colourView = (TextView) view.findViewById(R.id.colourView);

                Intent intent = new Intent(DatabaseActivity.this, DatabaseItemViewActivity.class);
                Bundle dataBundle = new Bundle();
                dataBundle.putString("name", nameView.getText().toString());
                dataBundle.putString("colour", colourView.getText().toString());
                intent.putExtras(dataBundle);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        queryDBListView();
    }

    public void queryDBListView() {

        Cursor cursor = dbAdapter.db.query("myList", new String[] { "_id", "name", "colour" },
                null, null, null, null, null);

        /*
        String[] columns = new String[] {
                DatabaseAdapter.KEY_NAME,
                DatabaseAdapter.KEY_COLOUR
        };*/

        String[] columns = new String[] {
               "name",
               "colour"
        };

        int[] to = new int[] {
                R.id.nameView,
                R.id.colourView,
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.db_item_view,
                cursor,
                columns,
                to,
                0);

        listView.setAdapter(dataAdapter);
    }
}
