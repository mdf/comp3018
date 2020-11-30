package com.example.pszmdf.martinroomstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final FruitAdapter adapter = new FruitAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Fruit> fruit = fruitDao.getAlphabetizedFruit();
            adapter.setData(fruit);
        });
    }

    public void addFruit(View v) {

        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            Fruit fruit = new Fruit("name" + rand.nextInt(), "green");
            fruitDao.insert(fruit);
        });

        getAllFruit();
    }

    public void getAllFruit() {

        MyRoomDatabase.databaseWriteExecutor.execute(() -> {

            List<Fruit> fruit;
            fruit = fruitDao.getAlphabetizedFruit();
            for(Fruit f: fruit) {
                Log.d("g53mdp", f.getName() + " " + f.getColour());
            }
        });
    }
}