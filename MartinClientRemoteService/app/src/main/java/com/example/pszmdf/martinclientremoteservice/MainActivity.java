package com.example.pszmdf.martinclientremoteservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pszmdf.martinremoteservice.ExampleParcelable;

public class MainActivity extends AppCompatActivity {

    private Messenger messenger; // serviceMessenger
    private Messenger replyMessenger; //ourMessenger

    private class RemoteCounterService {
        public static final int REGISTER = 0;
        public static final int UNREGISTER = 1;
        public static final int COUNT_UP = 2;
        public static final int COUNT_DOWN = 3;
        public static final int COUNT_VALUE = 4;
        public static final int REVERSE_STRING = 5;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.pszmdf.martinremoteservice", "com.example.pszmdf.martinremoteservice.RemoteCounterService"));
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        replyMessenger = new Messenger(new MyHandler());
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch(msg.what) {
                case RemoteCounterService.COUNT_VALUE:
                    final int counterValue = msg.arg1;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = (TextView) findViewById(R.id.textView);
                            tv.setText("counter: " + counterValue);
                        }
                    });
                    break;
                case RemoteCounterService.REVERSE_STRING:
                    final String reverseString = msg.getData().getString("reversed");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = (TextView) findViewById(R.id.textView3);
                            tv.setText("reversed: " + reverseString);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickCountUp(View v)
    {
        Message message = Message.obtain(null, RemoteCounterService.COUNT_UP, 0, 0);

        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClickCountDown(View v)
    {
        Message message = Message.obtain(null, RemoteCounterService.COUNT_DOWN, 0, 0);

        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClickReverse(View v) {

        EditText editText = (EditText) findViewById(R.id.textView2);
        String toReverse = editText.getText().toString();

        Message message = Message.obtain(null, RemoteCounterService.REVERSE_STRING, 0, 0);

        ExampleParcelable p = new ExampleParcelable();
        p.x = 5;
        p.y = 10;
        p.text = toReverse;

        Bundle b = new Bundle();
        b.putParcelable("myParcel", (Parcelable) p);
        message.setData(b);
        message.replyTo = replyMessenger;

        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "onServiceConnected");
            messenger = new Messenger(service);

            try {
                Message msg = Message.obtain(null, RemoteCounterService.REGISTER);

                msg.replyTo = replyMessenger; // messenger itself is parcelable, sendable
                messenger.send(msg);
            } catch (RemoteException e) {
                // service died
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "onServiceDisconnected");
            messenger = null;
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "MainActivity onDestroy");

        if(serviceConnection!=null) {

            try {
                Message msg = Message.obtain(null, RemoteCounterService.UNREGISTER);
                msg.replyTo = replyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                // service died
            }
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }

}
