package edu.neu.madcourse.numad20s_rongyichen;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.List;

/**
 * For HW5 Link Collector 2
 * This is the website adapter for "link collector 2" activity
 */
public class WebsiteListAdapter extends RecyclerView.Adapter<WebsiteListAdapter.ViewHolder> {
    private static final String TAG = "WebsiteListAdapter";
    private int webItemLayout;
    private List<Websites> mWebItem;

    private OnWebClickListener mOnWebClickListener;

    public WebsiteListAdapter(int webItemLayout, OnWebClickListener onWebClickListener) {
        this.webItemLayout = webItemLayout;
        this.mOnWebClickListener = onWebClickListener;
    }

    /**
     * This is for a private OnItemClickListener listener;
     * @param mWebItem
     */

    public void setmWebItem(List<Websites> mWebItem) {
        this.mWebItem = mWebItem;
        notifyDataSetChanged();
    }


    public interface OnWebClickListener {
        void onItemClick(int pos);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(webItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view, mOnWebClickListener); return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        holder.textWebName.setText(mWebItem.get(listPosition).getWeb_name());
        holder.textWebUrl.setText(mWebItem.get(listPosition).getWeb_url());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textWebName;
        TextView textWebUrl;
        OnWebClickListener monWebClickListener;

        ViewHolder(View itemView, OnWebClickListener onWebClickListener) {
            super(itemView);
            textWebName = itemView.findViewById(R.id.webitem_name);
            textWebUrl = itemView.findViewById(R.id.webitem_url);
            monWebClickListener = onWebClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            monWebClickListener.onItemClick(getAdapterPosition());
        }
    }
    @Override
    public int getItemCount() {
        return this.mWebItem == null ? 0: mWebItem.size();
    }
    public int psoIDtoItemId (int pos) {
        Log.d(TAG, "pos id: " + pos + " getID: "+ mWebItem.get(pos).getId());
        return mWebItem.get(pos).getId();
    }


}
