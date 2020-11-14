package com.example.pszmdf.martinbroadcasts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_PROCESS_CALLS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void attemptRegisterBroadcast(View v) {

        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.PROCESS_OUTGOING_CALLS) ==
                PackageManager.PERMISSION_GRANTED) {
            Log.d("g53mdp", "granted");
            registerBroadcastReceiver();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.PROCESS_OUTGOING_CALLS)) {

            Log.d("g53mdp", "explanation required");
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("explanation - this permission is required for this app to function");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) ->
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                            MY_PERMISSIONS_REQUEST_PROCESS_CALLS
                    ));
            alertDialog.show();

        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    MY_PERMISSIONS_REQUEST_PROCESS_CALLS
            );
            Log.d("g53mdp", "explanation not needed");
        }
    }

    public void registerBroadcastReceiver() {

        Toast.makeText(this, "receiver registered", Toast.LENGTH_LONG).show();
        IntentFilter filter = new IntentFilter("android.intent.action.NEW_OUTGOING_CALL");
        filter.setPriority(2);
        registerReceiver(new MyBroadcastReceiver(), filter);
    }

    public void onClickBroadcast(View v) {
        Intent intent = new Intent(this, CustomBroadcastReceiver.class);
        intent.setAction("com.example.pszmdf.martinbroadcasts.MY_CUSTOM_BROADCAST");
        sendBroadcast(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PROCESS_CALLS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("g53mdp", "permission granted");
                } else {
                    Log.d("g53mdp", "permission denied");
                }
                return;
            }
        }
    }
}