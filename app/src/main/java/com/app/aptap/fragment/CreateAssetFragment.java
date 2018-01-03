package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAssetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAssetFragment extends MasterFragment {

    public static String TAG = CreateAssetFragment.class.getName();

    private View mView;

    private ImageView startTimeIcon, startTimeDownIcon, endTimeIcon, endTimeDownIcon, dateIcon;
    private CustomTextView startTimeText, endTimeText;

    public CreateAssetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateAssetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAssetFragment newInstance() {
        CreateAssetFragment fragment = new CreateAssetFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_asset, container, false);

//        ImageView assetTypeIcon = (ImageView) mView.findViewById(R.id.assetTypeIcon);
//        final CustomTextView assetType = (CustomTextView) mView.findViewById(R.id.assetType);
//        assetTypeIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // pass id here
//                showFlatWingSelecationDialog(assetType.getId(), getString(R.string.create_asset_title_type), getBlockData());
//            }
//        });
//
//        assetType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // pass id here
//                showFlatWingSelecationDialog(assetType.getId(), getString(R.string.create_asset_title_type), getBlockData());
//            }
//        });
//
//
//        dateIcon = (ImageView) mView.findViewById(R.id.dateIcon);
//        dateIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // pass id here
//                //getHomeActivity().showDatePickerDialog(view);
//            }
//        });

        startTimeIcon = (ImageView) mView.findViewById(R.id.startTimeIcon);
        startTimeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });

        startTimeText = (CustomTextView) mView.findViewById(R.id.startTimeText);
        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });

        startTimeDownIcon = (ImageView) mView.findViewById(R.id.startTimeDownIcon);
        startTimeDownIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });


        endTimeIcon = (ImageView) mView.findViewById(R.id.endTimeIcon);
        endTimeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(endTimeText);
            }
        });

        endTimeText = (CustomTextView) mView.findViewById(R.id.endTimeText);
        endTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(endTimeText);
            }
        });

        endTimeDownIcon = (ImageView) mView.findViewById(R.id.endTimeDownIcon);
        endTimeDownIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(endTimeText);
            }
        });




        return mView;
    }



}
