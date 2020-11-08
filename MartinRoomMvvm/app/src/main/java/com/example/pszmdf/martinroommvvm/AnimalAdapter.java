package com.example.pszmdf.martinroommvvm;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public AnimalAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.db_layout_view, parent, false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Animal> newData) {
        if (data != null) {
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        } else {
            data = newData;
        }
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView kindView;

        AnimalViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.nameView);
            kindView = itemView.findViewById(R.id.colourView);
        }

        void bind(final Animal animal) {

            if (animal != null) {
                nameView.setText(animal.getName());
                kindView.setText(animal.getKind());
            }
        }

    }
}
