package com.app.aptap.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;
import com.app.aptap.adapter.AdminMessageRecyclerAdapter;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminMessagesFragment} interface
 * to handle interaction events.
 * Use the {@link AdminMessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMessagesFragment extends MasterFragment {

    public static String TAG = AdminMessagesFragment.class.getName();

    private View mView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public AdminMessagesFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AdminMessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminMessagesFragment newInstance() {
        AdminMessagesFragment fragment = new AdminMessagesFragment();
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
        mView = inflater.inflate(R.layout.fragment_admin_messages, container, false);

        initRecyclerView();
        initPullDownRefresh();

        return mView;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.adminMessageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);*/
        AdminMessageRecyclerAdapter adminMessageRecyclerAdapter = new AdminMessageRecyclerAdapter(getHomeActivity(), Constants.createItemList());
        recyclerView.setAdapter(adminMessageRecyclerAdapter);
        //setting up our OnScrollListener
        recyclerView.setOnScrollListener(new HidingScrollListener() {
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
}
