package com.example.pszmdf.martinroomstorage;


import android.content.Context;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Fruit.class}, version = 5, exportSchema = false) // drop and recreat
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract FruitDao fruitDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "fruit_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(createCallback)
                            //allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback createCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.d("g53mdp", "dboncreate");
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FruitDao fruitDao = INSTANCE.fruitDao();
                fruitDao.deleteAll();

                Fruit fruit = new Fruit("mango", "orange");
                fruitDao.insert(fruit);
            });
        }
    };
}
