package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeParentFragment extends MasterFragment implements View.OnClickListener {
    public static String TAG = HomeParentFragment.class.getName();

    private View mView;

    public HomeParentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HomeParentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeParentFragment newInstance() {
        HomeParentFragment fragment = new HomeParentFragment();
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
        mView = inflater.inflate(R.layout.fragment_home_parent, container, false);

        replaceChildHomeFragment(HomeFragment.newInstance(), false, HomeFragment.TAG);

        return mView;

    }

    @Override
    public void onClick(View view) {

    }

}
