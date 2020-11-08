package com.example.pszmdf.martinroomstorage;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruit_table")
public class Fruit {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    private String colour;

    public Fruit(@NonNull String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }
}

