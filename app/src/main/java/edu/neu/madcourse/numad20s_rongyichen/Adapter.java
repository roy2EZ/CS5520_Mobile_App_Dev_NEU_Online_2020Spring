package edu.neu.madcourse.numad20s_rongyichen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * to display a scrolling list of saved websites
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Website> myWebsites;
    private OnItemClickListener listener;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewUrlName;
        public TextView textViewUrlLink;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUrlName = itemView.findViewById(R.id.urlName);
            textViewUrlLink = itemView.findViewById(R.id.urlLink);
        }

        public void bind(final Website Website, final OnItemClickListener listener) {
            textViewUrlName.setText(Website.getName());
            textViewUrlLink.setText(Website.getUrl());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(Website);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Website Website);
    }

    public Adapter(List<Website> websiteList, OnItemClickListener listener) {
        this.myWebsites = websiteList;
        this.listener = listener;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * @param parent
     * @param viewType
     * @return a view holder
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position
     * @param holder the view holder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Website currentWebsite = myWebsites.get(position);
        holder.bind(currentWebsite, listener);
    }

    @Override
    public int getItemCount() {
        return this.myWebsites.size();
    }
}
