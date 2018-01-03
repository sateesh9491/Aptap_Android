package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateMemberFragment extends MasterFragment {
    public static String TAG = CreateMemberFragment.class.getName();

    private View mView;

    CustomTextView categoryType;

    public CreateMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CreateMemberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateMemberFragment newInstance() {
        CreateMemberFragment fragment = new CreateMemberFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_member, container, false);


        ImageView categoryIcon = (ImageView) mView.findViewById(R.id.categoryIcon);
        categoryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(categoryType.getId(), getString(R.string.create_member_block_title), getBlockData());
            }
        });

        categoryType = (CustomTextView) mView.findViewById(R.id.categoryType);
        categoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(categoryType.getId(), getString(R.string.create_member_block_title), getBlockData());
            }
        });

        return mView;
    }

}
