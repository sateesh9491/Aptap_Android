package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.aptap.R;
import com.app.aptap.adapter.AssetsListAdapter;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.MemberListAdapter;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssetsManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssetsManagementFragment extends MasterFragment implements View.OnClickListener {
    public static String TAG = AssetsManagementFragment.class.getName();

    private View mView;
    private FloatingActionButton createAssetFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public AssetsManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AssetsManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssetsManagementFragment newInstance() {
        AssetsManagementFragment fragment = new AssetsManagementFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_assets_management, container, false);

        createAssetFab = (FloatingActionButton) mView.findViewById(R.id.createAssetFab);
        createAssetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(CreateAssetFragment.newInstance(), true, CreateAssetFragment.TAG);
            }
        });

        initRecyclerView();

        initPullDownRefresh();


        return mView;
    }

    private void initRecyclerView() {
        RecyclerView assetsManagementList = (RecyclerView) mView.findViewById(R.id.assetsManagementList);
        assetsManagementList.setLayoutManager(new LinearLayoutManager(getActivity()));
        AssetsListAdapter assetsListAdapter = new AssetsListAdapter(getHomeActivity(), this, Constants.createItemList());
        assetsManagementList.setAdapter(assetsListAdapter);
        //setting up our OnScrollListener
        assetsManagementList.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
            }

            @Override
            public void onShow() {
            }
        });
    }

    private void initPullDownRefresh() {
        // enable pull down to refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something
                // after refresh is done, remember to call the following code
                if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);  // This hides the spinner
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        getHomeActivity().hideShowFabMenu();
        int id = view.getId();
        switch (id) {
            case R.id.assetsRowItem:
                replaceFragment(CreateAssetFragment.newInstance(), true, CreateAssetFragment.TAG);
                break;
        }
    }


}
