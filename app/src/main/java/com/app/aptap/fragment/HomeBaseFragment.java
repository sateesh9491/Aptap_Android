package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.aptap.R;
import com.app.aptap.adapter.CustomFragmentPagerAdapter;
import com.app.aptap.viewpager.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeBaseFragment extends MasterFragment implements CustomViewPager.OnItemClickListener {
    public static String TAG = HomeBaseFragment.class.getName();

    private View mView;
    private CustomFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public HomeBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HomeBaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeBaseFragment newInstance() {
        HomeBaseFragment fragment = new HomeBaseFragment();
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
        mView = inflater.inflate(R.layout.fragment_home_base, container, false);
        initViewPagerAndTabs();
        return mView;
    }

    private void initViewPagerAndTabs() {

        viewPager = (CustomViewPager) mView.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) mView.findViewById(R.id.tabLayout);
        pagerAdapter = new CustomFragmentPagerAdapter(getChildFragmentManager());

        pagerAdapter.addFragment(HomeParentFragment.newInstance(), getString(R.string.tab_home));
        pagerAdapter.addFragment(SocietyParentFragment.newInstance(), getString(R.string.tab_society));
        pagerAdapter.addFragment(ServicesFragment.newInstance(), getString(R.string.tab_service));
        pagerAdapter.addFragment(AccountsFragment.newInstance(), getString(R.string.tab_account));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
                pagerAdapter.notifyDataSetChanged();
                getHomeActivity().hideShowFabMenu();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        /*viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // your code
            }
        });*/

    }

    @Override
    public void onItemClick(int position) {
        getHomeActivity().hideShowFabMenu();
    }
}
