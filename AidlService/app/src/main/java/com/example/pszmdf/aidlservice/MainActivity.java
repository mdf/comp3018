package com.example.pszmdf.aidlservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        bindService(new Intent(MainActivity.this, StringReverserServiceImpl.class), connection, Context.BIND_AUTO_CREATE);
    }

    private IStringReverser service;

    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder iservice) {
            service = IStringReverser.Stub.asInterface(iservice);
            Log.d("g53mdp", "connected");
        }

        public void onServiceDisconnected(ComponentName className) {
            service = null;
        }
    };

    public void onClickReverse(View v) {
        try {
            String toReverse = editText.getText().toString();
            String reversed = service.reverseString(toReverse);
            textView.setText(reversed);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClickOneWay(View v) {
        try {
            Bundle b = new Bundle();
            b.putString("myKey", editText.getText().toString());

            service.modifyBundle(b);

            String s = b.getString("myKey");
            textView.setText(s);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onClickTwoWay(View v) {
        try {
            Bundle b = new Bundle();
            b.putString("myKey", editText.getText().toString());

            service.modifyBundleReference(b);

            String s = b.getString("myKey");
            textView.setText(s);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
