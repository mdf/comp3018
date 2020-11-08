package com.example.pszmdf.martinroommvvm;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AnimalViewModel extends AndroidViewModel {

    private MyRepository repository;

    private final LiveData<List<Animal>> allAnimals;

    public AnimalViewModel(Application application) {
        super(application);
        repository = new MyRepository(application);
        allAnimals = repository.getAllAnimals();
    }

    LiveData<List<Animal>> getAllAnimals() { return allAnimals; }

    public void insert(Animal animal) { repository.insert(animal); }
}
