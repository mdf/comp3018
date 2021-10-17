package com.example.pszmdf.myapplication;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Counter extends BaseObservable {

    int counter = 0;

    @Bindable
    public Integer getCounter() {
        return this.counter;
    }

    public void updateCounter() {
        this.setCounter(counter+1);
    }

    public void setCounter(int counter) {
        this.counter = counter;
        notifyPropertyChanged(BR.counter);
    }
}
