package com.example.pszmdf.martinroommvvm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animal_table")
public class Animal {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    private String kind;

    public Animal(@NonNull String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }
}
