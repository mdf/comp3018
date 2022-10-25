package uk.ac.nott.mrl.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] catNames = getResources().getStringArray(R.array.cat_names);
        ArrayList<CatCard> catList = new ArrayList<CatCard>();

        for(int i=0;i<catNames.length;i++) {
            CatCard catcatCard = new CatCard();
            catcatCard.resourceId = getResources().getIdentifier("@drawable/cat"+i, "drawable", getPackageName());
            catcatCard.catName = catNames[i];
            catList.add(catcatCard);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CatRecyclerViewAdapter adapter = new CatRecyclerViewAdapter(this, catList);
        recyclerView.setAdapter(adapter);

    }
}