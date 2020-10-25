package com.example.pszmdf.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class StringReverserServiceImpl extends Service {

    private final IStringReverser.Stub binder = new IStringReverser.Stub() {

        public String reverseString(String inString) {
            Log.d("g53mdp", "reverseString");
            return new StringBuilder(inString).reverse().toString();
        }

        public int modifyBundle(Bundle bundle) {
            String s = bundle.getString("myKey");
            bundle.putString("myKey", new StringBuilder(s).reverse().toString());
            return 1;
        }

        public int modifyBundleReference(Bundle bundle) {
            String s = bundle.getString("myKey");
            bundle.putString("myKey", new StringBuilder(s).reverse().toString());
            return 1;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }
}
