package com.example.pszmdf.martinsimpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onStartCommand");
        return Service.START_STICKY;
    }

    boolean running = true;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onCreate");
        super.onCreate();

        new Thread(new Runnable() {

            public void run() {
                int i = 0;
                while(running) {
                    Log.d("g53mdp", "Counting " + i++);
                    try{Thread.sleep(2000);}catch(Exception e){;}
                }
                Log.d("g53mdp", "Exiting...");
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "service onDestroy");
        running = false;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onBind");
        return null;
    }

}
