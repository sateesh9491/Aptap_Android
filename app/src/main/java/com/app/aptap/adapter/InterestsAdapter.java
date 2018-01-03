package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.app.aptap.R;
import com.app.aptap.model.InterestsModelObject;
import java.util.List;

/**
 * Created by am40619 on 29-10-2017.
 */

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.ItemViewHolder> {

    private Context mContext;
    private List<InterestsModelObject> mItemList;
    private View.OnClickListener onClickListener;

    public InterestsAdapter(Context context, View.OnClickListener onClickListener, List<InterestsModelObject> itemList) {
        this.mContext = context;
        this.mItemList = itemList;
        this.onClickListener = onClickListener;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public InterestsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.interest_list_adapter_item_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new InterestsAdapter.ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(InterestsAdapter.ItemViewHolder viewHolder, int position) {
        // we are taking header in to account so all of our items are correctly positioned
        //in some cases, it will prevent unwanted situations
        final int pos = position;
        viewHolder.interests.setText(mItemList.get(position).getIntrestType());
        viewHolder.interests.setChecked(new Boolean(mItemList.get(position).getIntrested()));
        viewHolder.interests.setTag(mItemList.get(position));

        viewHolder.interests.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                InterestsModelObject emp = (InterestsModelObject) cb.getTag();
                emp.setIntrested(cb.isChecked());
                mItemList.get(pos).setIntrested(cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public CheckBox interests;
        public ItemViewHolder(final View itemView) {
            super(itemView);
            interests = (CheckBox) itemView.findViewById(R.id.interests);
        }
    }

}