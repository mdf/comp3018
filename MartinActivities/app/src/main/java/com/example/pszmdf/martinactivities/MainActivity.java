package com.example.pszmdf.martinactivities;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("g53mdp", "MainActivity onCreate");

        setContentView(R.layout.activity_main);

        LinearLayout l = findViewById(R.id.layout);
        l.setBackgroundColor(backgroundColour);
    }

    // Activity lifecycle

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "MainActivity onDestroy");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("g53mdp", "MainActivity onPause");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("g53mdp", "MainActivity onResume");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("g53mdp", "MainActivity onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("g53mdp", "MainActivity onStop");
    }

    // starting Activities

    public void onClickActivity2(View v) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void onClickPhoneCall(View v) {

        Uri number = Uri.parse("tel:01151234567");
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(intent);
    }

    public void onClickBrowse(View v) {

        Uri url = Uri.parse("http://www.example.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        startActivity(intent);
    }

    public void onClickAttachment(View v) {

        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivity(intent);
    }

    public void onClickSwipeActivity(View v) {

        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.example.pszmdf.martinactivityswiper", "com.example.pszmdf.martinactivityswiper.MainActivity"));
        startActivity(intent);
    }

    // sending and receiving data between Activities

    static final int ACTIVITY_THREE_REQUEST_CODE = 3;

    public void onClickActivity3(View v) {

        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);

        final EditText textBox = (EditText) findViewById(R.id.editTextSend);
        String text = textBox.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("myString", text);

        intent.putExtras(bundle);

        startActivityForResult(intent, ACTIVITY_THREE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final EditText resultField = (EditText) findViewById(R.id.editTextReceive);

        if (requestCode == ACTIVITY_THREE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("myResult");
                Log.d("g53mdp", "MainActivity ok " + result);

               // resultField = (EditText) findViewById(R.id.editTextReceive);
                resultField.setText(result);
            }
            else if(resultCode == RESULT_CANCELED) {
                Log.d("g53mdp", "MainActivity canceled");
            }
        }
    }

    // maintaining Activity state

    Random rand = new Random();
    int backgroundColour = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("g53mdp", "MainActivity onSaveInstanceState " + backgroundColour);
        outState.putInt("backgroundColour", backgroundColour);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        backgroundColour = savedInstanceState.getInt("backgroundColour");
        Log.d("g53mdp", "MainActivity onRestoreInstanceState " + backgroundColour);

        LinearLayout l = findViewById(R.id.layout);
        l.setBackgroundColor(backgroundColour);
    }
}
