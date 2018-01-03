package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassifiedFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassifiedFeedFragment extends MasterFragment {

    public static String TAG = ClassifiedFeedFragment.class.getName();

    public ClassifiedFeedFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ClassifiedFeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassifiedFeedFragment newInstance() {
        ClassifiedFeedFragment fragment = new ClassifiedFeedFragment();
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
        return inflater.inflate(R.layout.fragment_classified_feed, container, false);
    }

}
