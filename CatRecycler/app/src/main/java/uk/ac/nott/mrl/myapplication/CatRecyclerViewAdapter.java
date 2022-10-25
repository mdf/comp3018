package uk.ac.nott.mrl.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatRecyclerViewAdapter extends RecyclerView.Adapter<CatRecyclerViewAdapter.CatViewHolder> {

    private List<CatCard> data;
    private Context context;
    private LayoutInflater layoutInflater;

    public CatRecyclerViewAdapter(Context context, List<CatCard> data) {
        this.data = data;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.cat_card_layout, parent, false);
        return new CatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CatViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        CatViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.catImageView);
            textView = itemView.findViewById(R.id.catNameView);
        }

        void bind(final CatCard cat) {
            imageView.setImageResource(cat.resourceId);
            textView.setText(cat.catName);
        }
    }
}
