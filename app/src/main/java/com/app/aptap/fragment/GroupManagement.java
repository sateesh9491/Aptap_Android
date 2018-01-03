package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;
import com.app.aptap.adapter.LatestGroupAdapter;
import com.app.aptap.adapter.ServiceUtilityBookingAdapter;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupManagement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupManagement extends MasterFragment implements View.OnClickListener {
    public static String TAG = GroupManagement.class.getName();

    private static final String ARG_FROM = "FROM";

    private View mView;
    private FloatingActionButton createGroup;
    private int mFrom = -1;

    public GroupManagement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GroupManagement.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupManagement newInstance(int fromIndex) {
        GroupManagement fragment = new GroupManagement();
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, fromIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFrom = getArguments().getInt(ARG_FROM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_group_management, container, false);

        createGroup = (FloatingActionButton) mView.findViewById(R.id.createGroup);
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFrom==1) {
                    replaceChildHomeFragment(CreateGroupFragment.newInstance(), true, CreateGroupFragment.TAG);
                } else if(mFrom==2) {
                    replaceChildSocietyFragment(CreateGroupFragment.newInstance(), true, CreateGroupFragment.TAG);
                }
            }
        });

        initServicesRecyclerView();

        return mView;
    }

    @Override
    public void onClick(View view) {
        getHomeActivity().hideShowFabMenu();
        int id = view.getId();
        switch (id) {
            case R.id.groupCircularRL:
                if(mFrom==1) {
                    replaceChildHomeFragment(CreateGroupFragment.newInstance(), true, CreateGroupFragment.TAG);
                } else if(mFrom==2) {
                    replaceChildSocietyFragment(CreateGroupFragment.newInstance(), true, CreateGroupFragment.TAG);
                }
                break;
        }
    }

    private void initServicesRecyclerView() {
        RecyclerView groupRecyclerView = (RecyclerView) mView.findViewById(R.id.groupRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        groupRecyclerView.setLayoutManager(mLayoutManager);
        groupRecyclerView.setItemAnimator(new DefaultItemAnimator());
        LatestGroupAdapter latestGroupAdapter = new LatestGroupAdapter(getHomeActivity(), this, Constants.getGroupData());
        groupRecyclerView.setAdapter(latestGroupAdapter);
    }

}
