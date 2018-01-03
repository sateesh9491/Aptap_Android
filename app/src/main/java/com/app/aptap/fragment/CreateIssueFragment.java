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
import android.widget.Spinner;
import android.widget.Switch;

import com.app.aptap.R;
import com.app.aptap.adapter.SpinnerAdapter;
import com.app.aptap.ui.CustomTextView;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateIssueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateIssueFragment extends MasterFragment {
    public static String TAG = CreateIssueFragment.class.getName();

    private View mView;

    public CreateIssueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateIssueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateIssueFragment newInstance() {
        CreateIssueFragment fragment = new CreateIssueFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_issue, container, false);

        final Switch issueHouseSwitch = (Switch) mView.findViewById(R.id.issueHouseSwitch);
        final Switch issueSocietySwitch = (Switch) mView.findViewById(R.id.issueSocietySwitch);

        issueHouseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    issueSocietySwitch.setChecked(false);
                } else {
                    issueSocietySwitch.setChecked(true);
                }
            }
        });

        issueSocietySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    issueHouseSwitch.setChecked(false);
                } else {
                    issueHouseSwitch.setChecked(true);
                }
            }
        });

        ImageView categoryTypeIcon = (ImageView) mView.findViewById(R.id.categoryTypeIcon);
        final CustomTextView categoryType = (CustomTextView) mView.findViewById(R.id.categoryType);

        categoryTypeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(categoryType.getId(), getString(R.string.issue_header_category), getBlockData());
            }
        });

        categoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(categoryType.getId(), getString(R.string.issue_header_category), getBlockData());
            }
        });


        ImageView addressLine1Icon = (ImageView) mView.findViewById(R.id.addressLine1Icon);
        final CustomTextView addressLine1Type = (CustomTextView) mView.findViewById(R.id.addressLine1Type);

        addressLine1Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(addressLine1Type.getId(), getString(R.string.text_address), getBlockData());
            }
        });

        addressLine1Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(addressLine1Type.getId(), getString(R.string.text_address), getBlockData());
            }
        });

        ImageView vendorAssignmentIcon = (ImageView) mView.findViewById(R.id.vendorAssignmentIcon);
        final CustomTextView vendorAssignmentType = (CustomTextView) mView.findViewById(R.id.vendorAssignmentType);

        vendorAssignmentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorAssignmentType.getId(), getString(R.string.circle_vendor), getBlockData());
            }
        });

        vendorAssignmentType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorAssignmentType.getId(), getString(R.string.circle_vendor), getBlockData());
            }
        });

        return mView;
    }

}
