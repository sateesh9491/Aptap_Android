package com.app.aptap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.activity.HomeActivity;
import com.app.aptap.fragment.HomeFragment;
import com.app.aptap.fragment.SocietyFragment;
import com.app.aptap.ui.CustomTextView;

import java.util.List;

/**
 * Created by aditya on 9/3/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mItemList;
    private View.OnClickListener onClickListener;

    public RecyclerAdapter(Context context, View.OnClickListener onClickListener, List<String> itemList) {
        mContext = context;
        this.onClickListener = onClickListener;
        mItemList = itemList;
    }

    //modified creating viewholder, so it creates appropriate holder for a given viewType
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(onClickListener);
        return new ItemViewHolder(view);
    }

    //modifed ViewHolder binding so it binds a correct View for the Adapter
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ItemViewHolder holder = (ItemViewHolder) viewHolder;
        String itemText = mItemList.get(position); // we are taking header in to account so all of our items are correctly positioned
        holder.feedTitle.setText(itemText);

        holder.feedMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(HomeFragment.floatingHomeActionMenu.isOpen()) {
                    HomeFragment.floatingHomeActionMenu.close(true);
                }
                if(SocietyFragment.floatingSocietyActionMenu.isOpen()) {
                    SocietyFragment.floatingSocietyActionMenu.close(true);
                }

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.feedMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.feed_item_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    //our old getItemCount()
    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return getBasicItemCount(); // header
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final CustomTextView feedTitle;
        final ImageView feedMenu;

        public ItemViewHolder(View itemView) {
            super(itemView);
            feedTitle = (CustomTextView) itemView.findViewById(R.id.feedTitle);
            feedMenu = (ImageView) itemView.findViewById(R.id.feedMenu);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

}