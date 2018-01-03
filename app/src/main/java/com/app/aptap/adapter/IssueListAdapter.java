package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

import java.util.List;

/**
 * Created by aditya on 9/17/2017.
 */

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemList;
    private View.OnClickListener onClickListener;

    public IssueListAdapter(Context context, View.OnClickListener onClickListener, List<String> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.onClickListener = onClickListener;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public IssueListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.issue_list_adapter_item_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new IssueListAdapter.ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(IssueListAdapter.ItemViewHolder viewHolder, int position) {
        String itemText = mItemList.get(position); // we are taking header in to account so all of our items are correctly positioned

        if (position % 2 != 0) {
            viewHolder.issueRowId.setBackgroundResource(R.color.memberHeaderRowColor);
        }

        viewHolder.issueId.setText(itemText);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return mItemList.size();// header
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView issueId;
        LinearLayout issueRowId;

        public ItemViewHolder(View itemView) {
            super(itemView);
            issueRowId = (LinearLayout) itemView.findViewById(R.id.issueRowId);
            issueId = (CustomTextView) itemView.findViewById(R.id.issueId);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

}