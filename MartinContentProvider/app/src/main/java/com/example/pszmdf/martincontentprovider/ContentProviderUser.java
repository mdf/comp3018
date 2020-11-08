package com.example.pszmdf.martincontentprovider;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentProviderUser extends AppCompatActivity {

    SimpleCursorAdapter dataAdapter;

    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_user);

        queryEmails();
        queryFoods();

        getContentResolver().
                registerContentObserver(
                        MartinProviderContract.ALL_URI,
                        true,
                        new ChangeObserver(h));
    }

    class ChangeObserver extends ContentObserver {

        public ChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            this.onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            queryFoods();
            queryEmails();
        }
    }

    public void queryFoods() {

        String[] projection = new String[] {
                MartinProviderContract._ID,
                MartinProviderContract.NAME,
                MartinProviderContract.FOOD
        };

        String colsToDisplay [] = new String[] {
                MartinProviderContract.NAME,
                MartinProviderContract.FOOD
        };

        int[] colResIds = new int[] {
                R.id.value1,
                R.id.value2
        };

        Cursor cursor = getContentResolver().query(MartinProviderContract.FOOD_URI, projection, null, null, null);

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_layout,
                cursor,
                colsToDisplay,
                colResIds,
                0);

        ListView listView = (ListView) findViewById(R.id.foodListView);
        listView.setAdapter(dataAdapter);
    }

    public void queryEmails() {

        String[] projection = new String[] {
                MartinProviderContract._ID,
                MartinProviderContract.NAME,
                MartinProviderContract.EMAIL
        };

        String colsToDisplay [] = new String[] {
                MartinProviderContract._ID,
                MartinProviderContract.NAME,
                MartinProviderContract.EMAIL
        };

        int[] colResIds = new int[] {
                R.id.value1,
                R.id.value2,
                R.id.value3
        };

        Cursor cursor = getContentResolver().query(MartinProviderContract.PEOPLE_URI, projection, null, null, null);

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_layout,
                cursor,
                colsToDisplay,
                colResIds,
                0);

        ListView listView = (ListView) findViewById(R.id.emailListView);
        listView.setAdapter(dataAdapter);
    }

    public void add(View v) {

        final EditText nameField = (EditText) findViewById(R.id.editText1);
        String name = nameField.getText().toString();

        final EditText foodField = (EditText) findViewById(R.id.editText2);
        String food = foodField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.editText3);
        String email = emailField.getText().toString();

        ContentValues newValues = new ContentValues();
        newValues.put(MartinProviderContract.NAME, name);
        newValues.put(MartinProviderContract.EMAIL, email);
        newValues.put(MartinProviderContract.FOOD, food);

        getContentResolver().insert(MartinProviderContract.PEOPLE_URI, newValues);
    }
}
