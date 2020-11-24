package com.example.pszmdf.myapplication;

import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForthViewModel extends ViewModel {

    private MutableLiveData<String> someState;

    public MutableLiveData<String> getSomeState() {
        if (someState == null) {
            someState = new MutableLiveData<>();
        }
        return someState;
    }

    public void setSomeState(String someState) {
        this.someState.setValue(someState + Math.random());
    }


    private MutableLiveData<Integer> someValue;

    public MutableLiveData<Integer> getSomeValue() {
        Log.d("g53mdp", "getSomeValue");
        if (someValue == null) {
            someValue = new MutableLiveData<>();
        }
        return someValue;
    }

    public void setSomeValue(int someValue) {
        Log.d("g53mdp", "setSomeValue");
        this.someValue.setValue(someValue);
    }
}
