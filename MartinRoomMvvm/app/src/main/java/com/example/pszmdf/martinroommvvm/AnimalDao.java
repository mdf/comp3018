package com.example.pszmdf.martinroommvvm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Animal animal);

    @Query("DELETE FROM animal_table")
    void deleteAll();

    @Query("SELECT * FROM animal_table")
    LiveData<List<Animal>> getAnimals();
}
