package com.example.pszmdf.martinintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CounterIntentService extends IntentService {

    public CounterIntentService() {
        super("CounterIntentService");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        Log.d("g53mdp", "service onCreate");

        //try{Thread.sleep(5000);}catch(Exception e){;}
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "service onHandleIntent");

            int jobNumber = intent.getIntExtra("jobNumber", 0);

            for(int i=0; i<5; i++) {
                try{Thread.sleep(1000);}catch(Exception e){;}
                Log.d("g53mdp", "working on " + jobNumber + " step " + i);
            }
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("g53mdp", "service onStart");
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "service onDestroy");
    }

}
