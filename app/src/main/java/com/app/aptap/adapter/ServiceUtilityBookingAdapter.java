package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.aptap.R;
import com.app.aptap.activity.HomeActivity;
import com.app.aptap.model.InterestsModelObject;
import com.app.aptap.model.ServiceUtilityBookingObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by am40619 on 01-12-2017.
 */

public class ServiceUtilityBookingAdapter extends RecyclerView.Adapter<ServiceUtilityBookingAdapter.ItemViewHolder> {

    private Context mContext;
    private List<ServiceUtilityBookingObject> mItemList;
    private View.OnClickListener onClickListener;

    public ServiceUtilityBookingAdapter(Context context, View.OnClickListener onClickListener, List<ServiceUtilityBookingObject> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ServiceUtilityBookingAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.service_utility_booking_adapter_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new ServiceUtilityBookingAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceUtilityBookingAdapter.ItemViewHolder viewHolder, int position) {
        // we are taking header in to account so all of our items are correctly positioned
        //in some cases, it will prevent unwanted situations
        final int pos = position;
        viewHolder.title.setText(mItemList.get(position).getServiceName());

        if (mItemList.get(position).getServiceImageURL() != null) {
            Picasso.with(mContext)
                    .load(mItemList.get(position).getServiceImageURL())
                    .into(viewHolder.imageIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemLinearLayout;
        public ImageView imageIcon;
        public TextView title;
        public ItemViewHolder(final View itemView) {
            super(itemView);
            itemLinearLayout = (LinearLayout) itemView.findViewById(R.id.itemLinearLayout);
            imageIcon = (ImageView) itemView.findViewById(R.id.imageIcon);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}