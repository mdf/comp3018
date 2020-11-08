package com.example.pszmdf.martincontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        Log.d("g53mdp", "DBHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("g53mdp", "onCreate");
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE people (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name VARCHAR(128) NOT NULL," +
                "email VARCHAR(128) NOT NULL," +
                "food VARCHAR(128) NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE animals (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name VARCHAR(128) NOT NULL," +
                "kind VARCHAR(128) NOT NULL," +
                "food VARCHAR(128) NOT NULL" +
                ");");

        db.execSQL("INSERT INTO people (name, email, food) VALUES ('martin', 'mdf@cs.nott.ac.uk', 'cheese');");
        db.execSQL("INSERT INTO people (name, email, food) VALUES ('donald', 'donald@gmail.com', 'burgers');");

        db.execSQL("INSERT INTO animals (name, kind, food) VALUES ('scribble', 'cat', 'whiskers');");
        db.execSQL("INSERT INTO animals (name, kind, food) VALUES ('hector', 'dog', 'bones');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS people");
        db.execSQL("DROP TABLE IF EXISTS animals");
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }
}