package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.MemberListAdapter;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberManagementFragment extends MasterFragment implements View.OnClickListener  {

    public static String TAG = MemberManagementFragment.class.getName();

    private View mView;
    private LinearLayout addMemberMenu, importMemberMenu, exportMemberMenu;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Animation fab_open,fab_close,rotate_forward,rotate_backward;


    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fabAdd,fabImport, fabExport;

    public MemberManagementFragment() {
        // Required empty public constructor
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return false;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MemberManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MemberManagementFragment newInstance() {
        MemberManagementFragment fragment = new MemberManagementFragment();
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
        mView = inflater.inflate(R.layout.fragment_member_management, container, false);

        addMemberMenu = (LinearLayout) mView.findViewById(R.id.addMemberMenu);
        addMemberMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(CreateMemberFragment.newInstance(), true, CreateMemberFragment.TAG);
            }
        });

        importMemberMenu = (LinearLayout) mView.findViewById(R.id.importMemberMenu);
        exportMemberMenu = (LinearLayout) mView.findViewById(R.id.exportMemberMenu);

        FloatingActionButton  createAssetFab = (FloatingActionButton) mView.findViewById(R.id.fabAdd);
        createAssetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(CreateMemberFragment.newInstance(), true, CreateMemberFragment.TAG);
            }
        });

        initRecyclerView();

        initPullDownRefresh();

        return mView;
    }

    private void initFabButtons() {
//        fab = (FloatingActionButton) mView.findViewById(R.id.fab);
//        fabAdd = (FloatingActionButton) mView.findViewById(R.id.fabAdd);
//        fabImport = (FloatingActionButton) mView.findViewById(R.id.fabImport);
//        fabExport = (FloatingActionButton) mView.findViewById(R.id.fabExport);

        fab_open = AnimationUtils.loadAnimation(getHomeActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getHomeActivity(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getHomeActivity(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getHomeActivity(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fabImport.setOnClickListener(this);
        fabExport.setOnClickListener(this);
    }

    private void initRecyclerView() {
        RecyclerView memberManagementList = (RecyclerView) mView.findViewById(R.id.memberManagementList);
        memberManagementList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MemberListAdapter memberListAdapter = new MemberListAdapter(getHomeActivity(), this, Constants.createItemList());
        memberManagementList.setAdapter(memberListAdapter);
        //setting up our OnScrollListener
        memberManagementList.setOnScrollListener(new HidingScrollListener() {
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
            case R.id.memberRowItem:
                replaceFragment(CreateMemberFragment.newInstance(), true, CreateMemberFragment.TAG);
                break;

        }
    }

    private void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fabAdd.startAnimation(fab_close);
            fabImport.startAnimation(fab_close);
            fabExport.startAnimation(fab_close);
            fabAdd.setClickable(false);
            fabImport.setClickable(false);
            fabExport.setClickable(false);
            isFabOpen = false;
        } else {
            fab.startAnimation(rotate_forward);
            fabAdd.startAnimation(fab_open);
            fabImport.startAnimation(fab_open);
            fabExport.startAnimation(fab_open);
            fabAdd.setClickable(true);
            fabImport.setClickable(true);
            fabExport.setClickable(true);
            isFabOpen = true;
        }
    }


}
