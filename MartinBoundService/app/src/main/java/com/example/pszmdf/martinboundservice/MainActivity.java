package com.example.pszmdf.martinboundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterService.MyBinder myService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startService(new Intent(this, CounterService.class));
        this.bindService(new Intent(this, CounterService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "MainActivity onServiceConnected");
            myService = (CounterService.MyBinder) service;
            myService.registerCallback(callback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "MainActivity onServiceDisconnected");
            myService.unregisterCallback(callback);
            myService = null;
        }
    };

    ICallback callback = new ICallback() {
        @Override
        public void counterEvent(final int counter) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tv = (TextView) findViewById(R.id.textView);
                    tv.setText("counter: " + counter);
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "MainActivity onDestroy");

 /*       if(serviceConnection!=null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }*/
    }

    public void onClickCountUp(View v) {
        if(myService!=null)
            myService.countUp();
    }

    public void onClickCountDown(View v) {
        myService.countDown();
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
}
