package com.example.pszmdf.myapplication;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThirdViewModel extends ViewModel {

    public final MutableLiveData<String> someState = new MutableLiveData<String>();

}
