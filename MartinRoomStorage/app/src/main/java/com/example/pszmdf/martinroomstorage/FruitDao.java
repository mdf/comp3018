package com.example.pszmdf.martinroomstorage;

import android.database.Cursor;

import java.util.List;

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

    @Query("SELECT * FROM fruit_table")
    Cursor getAllFruit();

    @Query("SELECT * FROM fruit_table ORDER BY name ASC")
    List<Fruit> getAlphabetizedFruit();

}
