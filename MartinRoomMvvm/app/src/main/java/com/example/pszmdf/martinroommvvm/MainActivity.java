package com.example.pszmdf.martinroommvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AnimalViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final AnimalAdapter adapter = new AnimalAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AnimalViewModel.class);

        viewModel.getAllAnimals().observe(this, animals -> {
            adapter.setData(animals);
        });
    }

    public void onClick(View v) {
        Animal a = new Animal("penfold", "mouse");
        viewModel.insert(a);
    }
}