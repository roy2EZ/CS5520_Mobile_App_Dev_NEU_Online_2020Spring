package edu.neu.madcourse.numad20s_rongyichen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> mItem;
    private OnItemClickListener listener;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewUrlName;
        public TextView textViewUrlLink;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUrlName = itemView.findViewById(R.id.urlName);
            textViewUrlLink = itemView.findViewById(R.id.urlLink);
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            textViewUrlName.setText(item.getName());
            textViewUrlLink.setText(item.getUrl());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public MyAdapter(List<Item> itemList, OnItemClickListener listener) {
        this.mItem = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item currentItem = mItem.get(position);
        holder.bind(currentItem, listener);

    }

    @Override
    public int getItemCount() {
        return this.mItem.size();
    }
}