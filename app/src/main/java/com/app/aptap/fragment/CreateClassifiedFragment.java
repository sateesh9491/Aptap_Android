package com.app.aptap.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.aptap.R;
import com.app.aptap.action.SelectedImageBitmapAndPath;
import com.app.aptap.ui.CustomTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateClassifiedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateClassifiedFragment extends MasterFragment {
    public static String TAG = CreateClassifiedFragment.class.getName();

    private View mView;
    private ImageView locationIcon;
    private CustomTextView location;
    private LinearLayout uploadPicLL;

    public CreateClassifiedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateClassifiedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateClassifiedFragment newInstance() {
        CreateClassifiedFragment fragment = new CreateClassifiedFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_classified, container, false);

        ImageView classifiedCtegoryTypeIcon = (ImageView) mView.findViewById(R.id.classifiedCtegoryTypeIcon);
        final CustomTextView classifiedCategoryType = (CustomTextView) mView.findViewById(R.id.classifiedCategoryType);
        classifiedCtegoryTypeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(classifiedCategoryType.getId(), getString(R.string.issue_header_category), getBlockData());
            }
        });

        classifiedCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(classifiedCategoryType.getId(), getString(R.string.issue_header_category), getBlockData());
            }
        });

        ImageView classifiedTypeIcon = (ImageView) mView.findViewById(R.id.classifiedTypeIcon);
        final CustomTextView classifiedType = (CustomTextView) mView.findViewById(R.id.classifiedType);
        classifiedTypeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(classifiedType.getId(), getString(R.string.create_classified_title_type), getBlockData());
            }
        });

        classifiedType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(classifiedType.getId(), getString(R.string.create_classified_title_type), getBlockData());
            }
        });


        locationIcon = (ImageView) mView.findViewById(R.id.locationIcon);
        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showGenderAndLocationSelecationDialog(location.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });

        location = (CustomTextView) mView.findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showGenderAndLocationSelecationDialog(location.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });

        uploadPicLL = (LinearLayout) mView.findViewById(R.id.uploadPicLL);
        uploadPicLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHomeActivity().showPictureDialog(new SelectedImageBitmapAndPath() {
                    @Override
                    public void imageBitmapData(Bitmap bitmap) {
                        Log.d(TAG, "Image Bitmap:-" + bitmap);
                    }

                    @Override
                    public void imagePath(String string) {
                        Log.d(TAG, "Image Path:-" + string);
                    }
                });

            }
        });


        return mView;

    }

}
