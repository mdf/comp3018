package com.example.pszmdf.martinroommvvm;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MyRepository {

    private FruitDao fruitDao;
    private AnimalDao animalDao;
    private LiveData<List<Fruit>> allFruit;
    private LiveData<List<Animal>> allAnimals;

    MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);

        animalDao = db.animalDao();
        fruitDao = db.fruitDao();

        allAnimals = animalDao.getAnimals();
        allFruit = fruitDao.getAlphabetizedFruit();
    }

    LiveData<List<Fruit>> getAllFruit() {
        return allFruit;
    }

    LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    void insert(Fruit fruit) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            fruitDao.insert(fruit);
        });
    }

    void insert(Animal animal) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            animalDao.insert(animal);
        });
    }

}
