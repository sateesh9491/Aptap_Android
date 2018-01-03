package com.app.aptap.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.RecyclerAdapter;
import com.app.aptap.adapter.SpinnerAdapter;
import com.app.aptap.util.Constants;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SocietyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocietyFragment extends MasterFragment implements View.OnClickListener{
    public static String TAG = SocietyFragment.class.getName();

    private View view;
    public static LinearLayout footerSocietyTabLayout;
    public static FloatingActionMenu floatingSocietyActionMenu;
    public static FloatingActionButton fabSocietyPlus;
    private SubActionButton fabSocietyPost, fabSocietyComplaints, fabSocietyEvents, fabSocietyPolls;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Spinner societySpinner;

    private ImageView footerGroups;

    public SocietyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocietyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SocietyFragment newInstance() {
        SocietyFragment fragment = new SocietyFragment();
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
        view = inflater.inflate(R.layout.fragment_society, container, false);

        societySpinner = (Spinner) view.findViewById(R.id.societyList);

        footerGroups = (ImageView) view.findViewById(R.id.footerGroups);
        footerGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceChildSocietyFragment(GroupManagement.newInstance(2), true, GroupManagement.TAG);
            }
        });

        initSocietySpinner();

        initFooterAndPlusMenu();

        initRecyclerView();

        initPullDownRefresh();

        return view;
    }

    private void initSocietySpinner() {
        List<String> list = new ArrayList<String>();
        list.add("Splendour Park Society");
        list.add("EMRLND Society");
        list.add("Happy Homes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        societySpinner.setAdapter(dataAdapter);
    }

    private void initFooterAndPlusMenu() {
        footerSocietyTabLayout = (LinearLayout) view.findViewById(R.id.footerSocietyTabLayout);
        fabSocietyPlus = (FloatingActionButton) view.findViewById(R.id.fabSocietyPlus);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getHomeActivity());

        LayoutInflater inflater = (LayoutInflater) getHomeActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myLayout = inflater.inflate(R.layout.post_image_view, null);
        FloatingActionButton imageViewOne = (FloatingActionButton) myLayout.findViewById(R.id.fabPost);

        myLayout = inflater.inflate(R.layout.event_image_view, null);
        FloatingActionButton imageViewTwo = (FloatingActionButton) myLayout.findViewById(R.id.fabEvents);

        myLayout = inflater.inflate(R.layout.poll_image_view, null);
        FloatingActionButton imageViewThree = (FloatingActionButton) myLayout.findViewById(R.id.fabPolls);

        myLayout = inflater.inflate(R.layout.complaints_image_view, null);
        FloatingActionButton imageViewFour = (FloatingActionButton) myLayout.findViewById(R.id.fabComplaints);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Constants.dpToPx(60, getHomeActivity()), Constants.dpToPx(60, getHomeActivity()));

        fabSocietyPost = itemBuilder.setContentView(imageViewOne).build();
        fabSocietyPost.setLayoutParams(params);
        fabSocietyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreatePostFragment.newInstance(), true, CreatePostFragment.TAG);
            }
        });

        fabSocietyEvents = itemBuilder.setContentView(imageViewTwo).build();
        fabSocietyEvents.setLayoutParams(params);
        fabSocietyEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreateEventFragment.newInstance(), true, CreateEventFragment.TAG);
            }
        });

        fabSocietyPolls = itemBuilder.setContentView(imageViewThree).build();
        fabSocietyPolls.setLayoutParams(params);
        fabSocietyPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                Toast.makeText(getActivity(), getString(R.string.hello_blank_fragment), Toast.LENGTH_SHORT).show();
            }
        });

        fabSocietyComplaints = itemBuilder.setContentView(imageViewFour).build();
        fabSocietyComplaints.setLayoutParams(params);
        fabSocietyComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreateIssueFragment.newInstance(), true, CreateIssueFragment.TAG);
            }
        });

        floatingSocietyActionMenu = new FloatingActionMenu.Builder(getHomeActivity())
                .addSubActionView(fabSocietyPost)
                .addSubActionView(fabSocietyEvents)
                .addSubActionView(fabSocietyPolls)
                .addSubActionView(fabSocietyComplaints)
                .attachTo(fabSocietyPlus)
                .build();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getHomeActivity(),this, Constants.createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        //setting up our OnScrollListener
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                getHomeActivity().hideShowFabMenu();
                getHomeActivity().hideSocietyFooterMenu();
            }

            @Override
            public void onShow() {
                getHomeActivity().showSocietyFooterMenu();
            }
        });
    }

    private void initPullDownRefresh() {
        // enable pull down to refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomeActivity().hideShowFabMenu();
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
        int id= view.getId();
        switch (id){
            case R.id.recyclerViewItem:
                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
