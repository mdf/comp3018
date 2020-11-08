package com.example.pszmdf.martinstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DatabaseItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(MainActivity.CONFIG_STORAGE_NAME, 0);
        if(settings.getBoolean(MainActivity.CONFIG_THEME, false))
            setTheme(android.R.style.Theme_Black);
        else
            setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_database_item_view);

        Bundle bundle = getIntent().getExtras();

        TextView nameView = findViewById(R.id.textView);
        nameView.setText(bundle.getString("name"));

        TextView colourView = findViewById(R.id.textView2);
        colourView.setText(bundle.getString("colour"));

    }
}
