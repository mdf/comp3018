package com.example.pszmdf.martinstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String CONFIG_STORAGE_NAME = "preferences";
    public static String CONFIG_THEME = "theme";
    boolean darkTheme = false;
    CheckBox checkBox;
    DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(MainActivity.CONFIG_STORAGE_NAME, 0);
        if(settings.getBoolean(MainActivity.CONFIG_THEME, false))
            setTheme(android.R.style.Theme_Black);
        else
            setTheme(R.style.AppTheme);
        darkTheme = settings.getBoolean(CONFIG_THEME, false);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        setContentView(R.layout.activity_main);
        queryDBTextView();

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                savePreferences(isChecked);
            }
        });
    }

    public void savePreferences(boolean isChecked) {

        SharedPreferences settings = getSharedPreferences(CONFIG_STORAGE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        if (isChecked && !darkTheme) {
            darkTheme = true;
            setTheme(android.R.style.Theme_Black);
            editor.putBoolean(CONFIG_THEME, darkTheme);
            recreate();
        } else if (!isChecked && darkTheme) {
            darkTheme = false;
            setTheme(R.style.AppTheme);
            editor.putBoolean(CONFIG_THEME, darkTheme);
            recreate();
        }
        editor.apply();
    }

    public void onClickLaunchDBActivity(View v) {
        startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
    }

    public void onClickLaunchRecyclerActivity(View v) {
        startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
    }

    public void queryDBTextView() {

        StringBuilder sb = new StringBuilder();
        TextView tv = (TextView)findViewById(R.id.dbTextView);

        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor c = dbAdapter.db.query("myList", new String[] { "_id", "name", "colour" }, null, null, null, null, null);

        if(c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String colour = c.getString(2);

                sb.append(""+id+ ": " + name + ", " + colour);
                sb.append("\n");

                Log.d("g53mdp", id + " " + name);
            } while(c.moveToNext());
        }

        tv.setText(sb);
    }

    public void onClickAdd(View v) {

        final EditText inputFieldColour = (EditText) findViewById(R.id.editTextColour);
        final EditText inputFieldName = (EditText) findViewById(R.id.editTextName);

        dbAdapter.addNameColour(inputFieldName.getText().toString(), inputFieldColour.getText().toString());

        queryDBTextView();
    }
}
