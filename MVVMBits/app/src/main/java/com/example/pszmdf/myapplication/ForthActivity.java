package com.example.pszmdf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.pszmdf.myapplication.databinding.ActivityForthBinding;

public class ForthActivity extends AppCompatActivity {

    ForthViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        model = new ViewModelProvider(this).get(ForthViewModel.class);

        ActivityForthBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forth);

        binding.setLifecycleOwner(this);

        setContentView(binding.getRoot());
        binding.setViewmodel(model);

    }
}