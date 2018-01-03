package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.fragment.CreateMemberFragment;
import com.app.aptap.fragment.MasterFragment;
import com.app.aptap.ui.CustomTextView;
import com.google.android.gms.vision.text.Line;

import java.util.List;

/**
 * Created by aditya on 9/17/2017.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mItemList;
    private View.OnClickListener onClickListener;

    public MemberListAdapter(Context context, View.OnClickListener onClickListener,  List<String> itemList) {
        this.mContext = context;
        this.onClickListener = onClickListener;
        this.mItemList = itemList;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public MemberListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.member_list_adapter_item_row, parent, false);
        view.setOnClickListener(onClickListener);
        return new MemberListAdapter.ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(MemberListAdapter.ItemViewHolder viewHolder, int position) {
        String itemText = mItemList.get(position); // we are taking header in to account so all of our items are correctly positioned
        if (position % 2 != 0) {
            viewHolder.memberRowItem.setBackgroundResource(R.color.memberHeaderRowColor);
        }
        viewHolder.memberName.setText(itemText);
        Linkify.addLinks(viewHolder.memberName, Linkify.ALL);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return mItemList.size();// header
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView memberName;
        LinearLayout memberRowItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            memberRowItem = (LinearLayout) itemView.findViewById(R.id.memberRowItem);
            memberName = (CustomTextView) itemView.findViewById(R.id.memberName);
        }
    }

}