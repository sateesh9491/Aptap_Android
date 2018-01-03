package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.aptap.R;
import com.app.aptap.model.LatestGroupObject;
import com.app.aptap.model.ServiceUtilityBookingObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by am40619 on 02-12-2017.
 */

public class LatestGroupAdapter extends RecyclerView.Adapter<LatestGroupAdapter.ItemViewHolder> {

    private Context mContext;
    private List<LatestGroupObject> mItemList;
    private View.OnClickListener onClickListener;

    public LatestGroupAdapter(Context context, View.OnClickListener onClickListener, List<LatestGroupObject> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.onClickListener = onClickListener;
    }

    @Override
    public LatestGroupAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.latest_group_item_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new LatestGroupAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LatestGroupAdapter.ItemViewHolder viewHolder, int position) {
        // we are taking header in to account so all of our items are correctly positioned
        //in some cases, it will prevent unwanted situations
        final int pos = position;
        viewHolder.groupTitle.setText(mItemList.get(position).getServiceName());

        if (mItemList.get(position).getServiceImageURL() != null) {
            Picasso.with(mContext)
                    .load(mItemList.get(position).getServiceImageURL())
                    .into(viewHolder.groupIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemLinearLayout;
        public ImageView groupIcon;
        public TextView groupTitle;
        public ItemViewHolder(final View itemView) {
            super(itemView);
            itemLinearLayout = (LinearLayout) itemView.findViewById(R.id.itemLinearLayout);
            groupIcon = (ImageView) itemView.findViewById(R.id.groupIcon);
            groupTitle = (TextView) itemView.findViewById(R.id.groupTitle);
        }
    }

}