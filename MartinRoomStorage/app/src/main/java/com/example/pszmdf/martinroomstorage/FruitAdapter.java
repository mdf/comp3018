package com.example.pszmdf.martinroomstorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FriuitViewHolder> {

    private List<Fruit> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public FruitAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FriuitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.db_layout_view, parent, false);
        return new FriuitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriuitViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Fruit> newData) {
        if (data != null) {
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        } else {
            data = newData;
        }
    }

    class FriuitViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView colourView;

        FriuitViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameView);
            colourView = itemView.findViewById(R.id.colourView);
        }

        void bind(final Fruit fruit) {

            if (fruit != null) {
                nameView.setText(fruit.getName());
                colourView.setText(fruit.getColour());
            }
        }
    }
}
