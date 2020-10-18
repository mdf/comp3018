package com.example.pszmdf.martinlivedata;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> waves;

    public MutableLiveData<Integer> getWaves() {
        if (waves == null) {
            waves = new MutableLiveData<Integer>();
            waves.setValue(0);
        }
        return waves;
    }

    public void incrementWaves() {
        Integer value = getWaves().getValue();
        getWaves().postValue(++value);
    }
}
