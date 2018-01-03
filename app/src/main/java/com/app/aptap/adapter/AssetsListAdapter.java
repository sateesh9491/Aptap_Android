package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

import java.util.List;

/**
 * Created by am40619 on 02-12-2017.
 */

public class AssetsListAdapter extends RecyclerView.Adapter<AssetsListAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemList;
    private View.OnClickListener onClickListener;

    public AssetsListAdapter(Context context, View.OnClickListener onClickListener,  List<String> itemList) {
        this.mContext = context;
        this.onClickListener = onClickListener;
        this.mItemList = itemList;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public AssetsListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.asset_list_adapter_item_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new AssetsListAdapter.ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(AssetsListAdapter.ItemViewHolder viewHolder, int position) {
        String itemText = mItemList.get(position); // we are taking header in to account so all of our items are correctly positioned
        if (position % 2 != 0) {
            viewHolder.assetsRowItem.setBackgroundResource(R.color.memberHeaderRowColor);
        }
        viewHolder.assetsName.setText(itemText);
        Linkify.addLinks(viewHolder.assetsName, Linkify.ALL);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return mItemList.size();// header
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView assetsName;
        LinearLayout assetsRowItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            assetsRowItem = (LinearLayout) itemView.findViewById(R.id.assetsRowItem);
            assetsName = (CustomTextView) itemView.findViewById(R.id.assetsName);
        }
    }

}