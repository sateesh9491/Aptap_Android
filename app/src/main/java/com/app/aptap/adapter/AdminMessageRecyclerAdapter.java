package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

import java.util.List;

/**
 * Created by aditya on 9/17/2017.
 */

public class AdminMessageRecyclerAdapter extends RecyclerView.Adapter<AdminMessageRecyclerAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemList;

    public AdminMessageRecyclerAdapter(Context context, List<String> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.admin_message_recycler_adapter_item, parent, false);
        return new ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        String itemText = mItemList.get(position); // we are taking header in to account so all of our items are correctly positioned
        viewHolder.messageTitle.setText(itemText);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return mItemList.size();// header
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView messageTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            messageTitle = (CustomTextView) itemView.findViewById(R.id.messageTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}