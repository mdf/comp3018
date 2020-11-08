package com.example.pszmdf.martinstorage;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    Cursor cursor;

    SimpleRecyclerViewAdapter(Context context, Cursor cursor) {
        this.inflater = LayoutInflater.from(context);
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_database_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d("g53mdp", "onBindViewHolder " + position);
        cursor.moveToPosition(position);
        String name = cursor.getString(1);
        String colour = cursor.getString(2);

        holder.nameView.setText(name);
        holder.colourView.setText(colour);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameView;
        TextView colourView;

        ViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.textView);
            colourView = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}