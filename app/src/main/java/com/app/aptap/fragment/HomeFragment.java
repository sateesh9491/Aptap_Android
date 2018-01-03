package com.app.aptap.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.adapter.AdvertisementAdapter;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.RecyclerAdapter;
import com.app.aptap.util.Constants;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * https://www.sitepoint.com/animating-android-floating-action-button/
 * <p>
 * https://github.com/oguzbilgener/CircularFloatingActionMenu
 * Created by aditya on 9/3/2017.
 */

public class HomeFragment extends MasterFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public static String TAG = HomeFragment.class.getName();
    private View view;

    public static LinearLayout footerHomeTabLayout;
    public static FloatingActionMenu floatingHomeActionMenu;
    public static FloatingActionButton fabHomePlus;
    private SubActionButton fabHomePost, fabHomeClassifieds, fabHomeEvents, fabHomePolls;

    private ImageView footerGroups;

    //Add
    private ViewPager addViewPager;
    private AdvertisementAdapter advertisementAdapter;
    private LinearLayout viewPagerCountDots;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int dotsCount;
    private ImageView[] dots;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocietyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        footerGroups = (ImageView) view.findViewById(R.id.footerGroups);
        footerGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceChildHomeFragment(GroupManagement.newInstance(1), true, GroupManagement.TAG);
            }
        });

        initAdvertisement();

        setPageViewIndicator();

        initFooterAndPlusMenu();

        initRecyclerView();

        initPullDownRefresh();

        return view;
    }

    private void initAdvertisement() {
        addViewPager = (ViewPager) view.findViewById(R.id.addViewPager);
        advertisementAdapter = new AdvertisementAdapter(getHomeActivity()); //Here we are defining the Imageadapter object
        addViewPager.setAdapter(advertisementAdapter);
        addViewPager.setOnPageChangeListener(this);
        viewPagerCountDots = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
    }

    private void setPageViewIndicator() {
        Log.d("###setPageViewIndicator", " : called");
        dotsCount = advertisementAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    addViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });
            viewPagerCountDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        getHomeActivity().hideShowFabMenu();
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        if (position + 1 == dotsCount) {
        } else {
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void initFooterAndPlusMenu() {
        footerHomeTabLayout = (LinearLayout) view.findViewById(R.id.footerHomeTabLayout);
        fabHomePlus = (FloatingActionButton) view.findViewById(R.id.fabHomePlus);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getHomeActivity());

        LayoutInflater inflater = (LayoutInflater) getHomeActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myLayout = inflater.inflate(R.layout.post_image_view, null);
        FloatingActionButton imageViewOne = (FloatingActionButton) myLayout.findViewById(R.id.fabPost);

        myLayout = inflater.inflate(R.layout.classified_image_view, null);
        FloatingActionButton imageViewTwo = (FloatingActionButton) myLayout.findViewById(R.id.fabClassifieds);

        myLayout = inflater.inflate(R.layout.event_image_view, null);
        FloatingActionButton imageViewThree = (FloatingActionButton) myLayout.findViewById(R.id.fabEvents);

        myLayout = inflater.inflate(R.layout.poll_image_view, null);
        FloatingActionButton imageViewFour = (FloatingActionButton) myLayout.findViewById(R.id.fabPolls);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Constants.dpToPx(60, getHomeActivity()), Constants.dpToPx(60, getHomeActivity()));

        fabHomePost = itemBuilder.setContentView(imageViewOne).build();
        fabHomePost.setLayoutParams(params);
        fabHomePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreatePostFragment.newInstance(), true, CreatePostFragment.TAG);
            }
        });

        fabHomeClassifieds = itemBuilder.setContentView(imageViewTwo).build();
        fabHomeClassifieds.setLayoutParams(params);
        fabHomeClassifieds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreateClassifiedFragment.newInstance(), true, CreateClassifiedFragment.TAG);
            }
        });

        fabHomeEvents = itemBuilder.setContentView(imageViewThree).build();
        fabHomeEvents.setLayoutParams(params);
        fabHomeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                replaceFragment(CreateEventFragment.newInstance(), true, CreateEventFragment.TAG);
            }
        });

        fabHomePolls = itemBuilder.setContentView(imageViewFour).build();
        fabHomePolls.setLayoutParams(params);
        fabHomePolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeActivity().hideShowFabMenu();
                Toast.makeText(getActivity(), getString(R.string.hello_blank_fragment), Toast.LENGTH_SHORT).show();
            }
        });

        //attach the sub buttons to the main buttonS
        floatingHomeActionMenu = new FloatingActionMenu.Builder(getHomeActivity())
                .addSubActionView(fabHomePost)
                .addSubActionView(fabHomeClassifieds)
                .addSubActionView(fabHomeEvents)
                .addSubActionView(fabHomePolls)
                .attachTo(fabHomePlus)
                .build();

        fabHomePlus.postDelayed(new Runnable() {
            @Override
            public void run() {
                floatingHomeActionMenu.close(true);
            }
        }, 200);

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getHomeActivity(), this, Constants.createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        //setting up our OnScrollListener
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                getHomeActivity().hideShowFabMenu();
                getHomeActivity().hideHomeFooterMenu();
            }

            @Override
            public void onShow() {
                getHomeActivity().showHomeFooterMenu();
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
        int id = view.getId();
        switch (id) {
            case R.id.recyclerViewItem:
                Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
