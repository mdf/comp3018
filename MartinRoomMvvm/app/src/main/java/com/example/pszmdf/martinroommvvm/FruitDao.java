package com.example.pszmdf.martinroommvvm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Fruit fruit);

    @Query("DELETE FROM fruit_table")
    void deleteAll();

    @Query("SELECT * FROM fruit_table ORDER BY name ASC")
    LiveData<List<Fruit>> getAlphabetizedFruit();

}