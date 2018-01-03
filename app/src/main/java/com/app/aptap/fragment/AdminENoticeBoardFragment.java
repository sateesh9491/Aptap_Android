package com.app.aptap.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.aptap.R;
import com.app.aptap.adapter.CustomFragmentPagerAdapter;
import com.app.aptap.viewpager.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminENoticeBoardFragment} interface
 * to handle interaction events.
 * Use the {@link AdminENoticeBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminENoticeBoardFragment extends MasterFragment {
    public static String TAG = AdminENoticeBoardFragment.class.getName();

    private View mView;
    private ViewPager viewPager;
    private CustomFragmentPagerAdapter customFragmentPagerAdapter;

    private LinearLayout viewPagerCountDots;
    private int dotsCount;
    private ImageView[] dots;

    public AdminENoticeBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AdminENoticeBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminENoticeBoardFragment newInstance() {
        AdminENoticeBoardFragment fragment = new AdminENoticeBoardFragment();
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
        mView = inflater.inflate(R.layout.fragment_admin_enotice_board, container, false);
        initViewPagerAndTabs();
        setPageViewIndicator();
        return mView;
    }

    private void initViewPagerAndTabs() {
        viewPager = (CustomViewPager) mView.findViewById(R.id.viewPager);
        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getChildFragmentManager());

        customFragmentPagerAdapter.addFragment(NoticePagerFragment.newInstance(), getString(R.string.admin_notice_pager));
        customFragmentPagerAdapter.addFragment(NoticePagerFragment.newInstance(), getString(R.string.admin_notice_pager));
        customFragmentPagerAdapter.addFragment(NoticePagerFragment.newInstance(), getString(R.string.admin_notice_pager));
        customFragmentPagerAdapter.addFragment(NoticePagerFragment.newInstance(), getString(R.string.admin_notice_pager));
        viewPager.setAdapter(customFragmentPagerAdapter);
        // Disable clip to padding
        viewPager.setClipToPadding(false);
        // set padding manually, the more you set the padding the more you see of prev & next page
        viewPager.setPadding(80, 0, 80, 0);
        // sets a margin b/w individual pages to ensure that there is a gap b/w them
        viewPager.setPageMargin(30);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
                customFragmentPagerAdapter.notifyDataSetChanged();

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.notice_nonselecteditem_dot));
                }
                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.notice_selecteditem_dot));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void setPageViewIndicator() {
        Log.d("###setPageViewIndicator", " : called");
        viewPagerCountDots = (LinearLayout) mView.findViewById(R.id.viewPagerCountDots);
        dotsCount = customFragmentPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.notice_nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    viewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });
            viewPagerCountDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.notice_selecteditem_dot));
    }

}
