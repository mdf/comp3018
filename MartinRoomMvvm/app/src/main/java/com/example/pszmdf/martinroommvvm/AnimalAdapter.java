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
    // private ButtonClickListener onDeleteButtonClickListener;

    public AnimalAdapter(Context context) { //}, ButtonClickListener listener) {
        this.data = new ArrayList<>();
        this.context = context;
        // this.onDeleteButtonClickListener = listener;
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
            /*PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);*/

            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
            //diffResult.dispatchUpdatesTo(this);
        } else {
            // first initialization
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
/*
    class PostDiffCallback extends DiffUtil.Callback {

        private final List<Post> oldPosts, newPosts;

        public PostDiffCallback(List<Post> oldPosts, List<Post> newPosts) {
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize() {
            return oldPosts.size();
        }

        @Override
        public int getNewListSize() {
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getId() == newPosts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }*/

/*
public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    Cursor cursor;

    AnimalListAdapter(Context context, Cursor cursor) {
        this.inflater = LayoutInflater.from(context);
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.db_layout_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
*/