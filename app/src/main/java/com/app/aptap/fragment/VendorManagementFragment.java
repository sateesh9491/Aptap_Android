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

import com.app.aptap.R;
import com.app.aptap.adapter.AssetsListAdapter;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.VendorListAdapter;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorManagementFragment extends MasterFragment implements View.OnClickListener {
    public static String TAG = VendorManagementFragment.class.getName();

    private View mView;
    private FloatingActionButton createVendorFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public VendorManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment VendorManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VendorManagementFragment newInstance() {
        VendorManagementFragment fragment = new VendorManagementFragment();
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
        mView = inflater.inflate(R.layout.fragment_vendor_management, container, false);

        createVendorFab = (FloatingActionButton) mView.findViewById(R.id.createVendorFab);
        createVendorFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(CreateVendorFragment.newInstance(), true, CreateVendorFragment.TAG);
            }
        });

        initRecyclerView();

        initPullDownRefresh();

        return mView;
    }

    private void initRecyclerView() {
        RecyclerView vendorManagementList = (RecyclerView) mView.findViewById(R.id.vendorManagementList);
        vendorManagementList.setLayoutManager(new LinearLayoutManager(getActivity()));
        VendorListAdapter vendorListAdapter = new VendorListAdapter(getHomeActivity(), this, Constants.createItemList());
        vendorManagementList.setAdapter(vendorListAdapter);
        //setting up our OnScrollListener
        vendorManagementList.setOnScrollListener(new HidingScrollListener() {
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
            case R.id.vendorRowItem:
                replaceFragment(CreateVendorFragment.newInstance(), true, CreateVendorFragment.TAG);
                break;
        }
    }

}
