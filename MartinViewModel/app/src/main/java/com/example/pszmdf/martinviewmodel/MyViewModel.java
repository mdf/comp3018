package com.example.pszmdf.martinviewmodel;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableInt;

public class MyViewModel extends ObservableViewModel {

    public String helloWorld = "Hello World!";

    public String getHelloWorld() {
        return helloWorld;
    }

    public ObservableInt waves = new ObservableInt(0);

    public void wave() {
        waves.set(waves.get()+1);
        notifyPropertyChanged(BR.popularity);
        helloWorld = "blah";
    }

    @Bindable
    public int getPopularity() {
        return waves.get();
    }
}

