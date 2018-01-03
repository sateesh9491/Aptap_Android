package com.app.aptap.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aptap.R;
import com.app.aptap.model.SpinnerItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ke41342 on 22-09-2017.
 */

public class SpinnerAdapter extends ArrayAdapter<SpinnerItemData> {
    int groupid;
    Activity context;
    List<SpinnerItemData> list;
    LayoutInflater inflater;
    public SpinnerAdapter(Activity context, int groupid, int id, List<SpinnerItemData>
            list){
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);
        ImageView imageView=(ImageView)itemView.findViewById(R.id.img);
        TextView textView=(TextView)itemView.findViewById(R.id.txt);
        textView.setText(list.get(position).getItemTitle());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}