package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocietyParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocietyParentFragment extends MasterFragment implements View.OnClickListener {
    public static String TAG = SocietyParentFragment.class.getName();

    private View mView;

    public SocietyParentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeParentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SocietyParentFragment newInstance() {
        SocietyParentFragment fragment = new SocietyParentFragment();
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
        mView = inflater.inflate(R.layout.fragment_society_parent, container, false);

        replaceChildSocietyFragment(SocietyFragment.newInstance(), false, SocietyFragment.TAG);

        return mView;
    }

    @Override
    public void onClick(View view) {
    }

}
