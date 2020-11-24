package com.example.pszmdf.myapplication;

import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {

    String someState = "hello world";

    public String getSomeState() {
        return someState;
    }

    public void setSomeState(String someState) {

        this.someState = someState;
    }

}
