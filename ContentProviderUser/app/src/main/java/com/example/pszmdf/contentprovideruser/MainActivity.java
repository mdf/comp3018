package com.example.pszmdf.contentprovideruser;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.pszmdf.martincontentprovider.MartinProviderContract;

public class MainActivity extends AppCompatActivity {

    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] projection = new String[] {
                MartinProviderContract._ID,
                MartinProviderContract.NAME,
                MartinProviderContract.FOOD
        };

        int[] colResIds = new int[] {
                R.id.value1,
                R.id.value2,
                R.id.value3
        };

        Cursor cursor = getContentResolver().query(MartinProviderContract.FOOD_URI, projection, null, null, null);

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_layout,
                cursor,
                projection,
                colResIds,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

        ChangeObserver observer = new ChangeObserver(new Handler());
        getContentResolver().registerContentObserver(MartinProviderContract.ALL_URI, true, observer);

        queryProvider();

        startService(new Intent(this, ProviderService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, ProviderService.class));
    }

    class ChangeObserver extends ContentObserver {
        ChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queryProvider();
        }
    }

    public void queryProvider()
    {
        Log.d("g53mdp", "data changed");

        String[] projection = new String[] {
                MartinProviderContract._ID,
                MartinProviderContract.NAME,
                MartinProviderContract.FOOD
        };

        Cursor cursor = getContentResolver().query(MartinProviderContract.FOOD_URI, projection, null, null, null);
        dataAdapter.changeCursor(cursor);
    }
}
