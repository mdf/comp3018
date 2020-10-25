package com.example.pszmdf.martinremoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

public class RemoteCounterService extends Service {

    private Messenger messenger;
    private Counter counter;

    public static final int REGISTER = 0;
    public static final int UNREGISTER = 1;
    public static final int COUNT_UP = 2;
    public static final int COUNT_DOWN = 3;
    public static final int COUNT_VALUE = 4;
    public static final int REVERSE_STRING = 5;

    ArrayList<Messenger> clientMessengers = new ArrayList<Messenger>();

    protected class Counter extends Thread implements Runnable {

        public boolean direction = true;
        public int count = 0;
        public boolean running = true;

        public Counter() {
            this.start();
        }

        public void run() {

            while(this.running) {

                try {Thread.sleep(2000);} catch(Exception e) {return;}

                if(direction)
                    count++;
                else
                    count--;

                Log.d("g53mdp", "Service counter " + count);

                for(int i=clientMessengers.size()-1; i>=0; i--) {
                    try {
                        clientMessengers.get(i).send(Message.obtain(null, COUNT_VALUE, count, 0));
                    }
                    catch(RemoteException e){
                        clientMessengers.remove(i);
                    }
                }
            }

            Log.d("g53mdp", "Service counter thread exiting");
        }
    }

    private class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case REGISTER:
                    clientMessengers.add(msg.replyTo);
                    break;
                case UNREGISTER:
                    clientMessengers.remove(msg.replyTo);
                    break;
                case COUNT_UP:
                    makeCountUp();
                    break;
                case COUNT_DOWN:
                    makeCountDown();
                    break;
                case REVERSE_STRING:
                    Bundle replyBundle = reverseString(msg.getData());
                    Message reply = Message.obtain();
                    reply.what = REVERSE_STRING;
                    reply.setData(replyBundle);

                    try {
                        msg.replyTo.send(reply);
                    } catch (RemoteException e) {
                        Log.d("g53mdp", e.toString());
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    public void makeCountUp() {
        counter.direction = true;
    }

    public void makeCountDown() {
        counter.direction = false;
    }

    public Bundle reverseString(Bundle bundle) {
        bundle.setClassLoader((ExampleParcelable.class.getClassLoader()));
        ExampleParcelable p = bundle.getParcelable("myParcel");

        Log.d("g53mdp", p.x + " " + p.y + " " + p.text);

        // reverse the input text
        String reversed = new StringBuilder(p.text).reverse().toString();
        Bundle outBundle = new Bundle();
        outBundle.putString("reversed", reversed);
        return outBundle;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onCreate");
        super.onCreate();
        counter = new Counter();
        messenger = new Messenger(new MessageHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("g53mdp", "service onBind");
        return messenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onDestroy");
        counter.running = false;
        counter = null;
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onUnbind");
        return super.onUnbind(intent);
    }
}
