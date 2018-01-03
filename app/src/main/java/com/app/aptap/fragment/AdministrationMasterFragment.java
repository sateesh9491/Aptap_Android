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

import com.app.aptap.R;
import com.app.aptap.adapter.CustomFragmentPagerAdapter;
import com.app.aptap.viewpager.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdministrationMasterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdministrationMasterFragment extends MasterFragment {
   public static String TAG = AdministrationMasterFragment.class.getName();

    private View mView;
    private CustomFragmentPagerAdapter pagerAdapter;
    private ViewPager adminViewPager;
    private TabLayout adminTabLayout;

    public AdministrationMasterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AdministrationMasterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdministrationMasterFragment newInstance() {
        AdministrationMasterFragment fragment = new AdministrationMasterFragment();
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
        mView = inflater.inflate(R.layout.fragment_administration_master, container, false);
        initViewPagerAndTabs();
        return mView;
    }

    private void initViewPagerAndTabs() {
        adminViewPager = (CustomViewPager) mView.findViewById(R.id.adminViewPager);
        adminTabLayout = (TabLayout) mView.findViewById(R.id.adminTabLayout);
        pagerAdapter = new CustomFragmentPagerAdapter(getChildFragmentManager());

        pagerAdapter.addFragment(AdminManagementFragment.newInstance(), getString(R.string.tab_admin_managment));
        pagerAdapter.addFragment(AdminMessagesFragment.newInstance(), getString(R.string.tab_admin_messages));
        pagerAdapter.addFragment(AdminENoticeBoardFragment.newInstance(), getString(R.string.tab_admin_enotice));

        adminViewPager.setAdapter(pagerAdapter);
        adminTabLayout.setupWithViewPager(adminViewPager);
        adminTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        adminViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adminViewPager.getAdapter().notifyDataSetChanged();
                pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

}
