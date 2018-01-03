package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.action.RecyclerViewClickListener;
import com.app.aptap.ui.CustomTextView;

import java.util.List;

/**
 * Created by aditya on 9/17/2017.
 */

public class AdminNoticeRecyclerAdapter extends RecyclerView.Adapter<AdminNoticeRecyclerAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemList;
    private RecyclerViewClickListener mListener;


    public AdminNoticeRecyclerAdapter(Context context, List<String> itemList, RecyclerViewClickListener listener) {
        mContext = context;
        mItemList = itemList;
        mListener = listener;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public AdminNoticeRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.admin_notice_recycler_item, parent, false);
        return new AdminNoticeRecyclerAdapter.ItemViewHolder(view, mListener);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(AdminNoticeRecyclerAdapter.ItemViewHolder viewHolder, int position) {
        int positionInList = position % mItemList.size();
        String itemText = mItemList.get(positionInList); // we are taking header in to account so all of our items are correctly positioned
        viewHolder.noticeSender.setText(itemText);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return mItemList.size();// header
    }


    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView noticeSender;

        public ItemViewHolder(View itemView, RecyclerViewClickListener mListener) {
            super(itemView);
            noticeSender = (CustomTextView) itemView.findViewById(R.id.noticeSender);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }

}