package com.example.pszmdf.martinviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.pszmdf.martinviewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ActivityMainBinding v = ActivityMainBinding.inflate(LayoutInflater.from(this));

        setContentView(v.getRoot());
        v.setViewmodel(model);
    }
}