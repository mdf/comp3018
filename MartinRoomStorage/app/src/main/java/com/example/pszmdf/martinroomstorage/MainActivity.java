package com.example.pszmdf.martinroomstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyRoomDatabase db;
    FruitDao fruitDao;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MyRoomDatabase.getDatabase(getApplicationContext());
        fruitDao = db.fruitDao();

        getAllFruit();
    }

    public void addFruit(View v) {

        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            Fruit fruit = new Fruit("parp" + rand.nextInt(), "green");
            fruitDao.insert(fruit);
        });

        getAllFruit();
    }

    public void getAllFruit() {

        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Fruit> fruit = fruitDao.getAlphabetizedFruit();
            for(Fruit f: fruit) {
                Log.d("g53mdp", f.getName() + " " + f.getColour());
            }
        });
    }



  /*  // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
*/
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
  /*
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });

    } */
}