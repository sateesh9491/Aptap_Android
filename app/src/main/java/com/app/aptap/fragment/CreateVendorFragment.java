package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateVendorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateVendorFragment extends MasterFragment {

    public static String TAG = CreateVendorFragment.class.getName();
    private View mView;

    public CreateVendorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateVendorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateVendorFragment newInstance() {
        CreateVendorFragment fragment = new CreateVendorFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_vendor, container, false);

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


        ImageView vendorCategoryIcon = (ImageView) mView.findViewById(R.id.vendorCategoryIcon);
        final CustomTextView vendorCategory = (CustomTextView) mView.findViewById(R.id.vendorCategory);
        vendorCategoryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorCategory.getId(), getString(R.string.create_vendor_title_category), getBlockData());
            }
        });

        vendorCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorCategory.getId(), getString(R.string.create_vendor_title_category), getBlockData());
            }
        });


        ImageView vendorLocationIcon = (ImageView) mView.findViewById(R.id.vendorLocationIcon);
        final CustomTextView vendorLocation = (CustomTextView) mView.findViewById(R.id.vendorLocation);
        vendorLocationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorLocation.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });

        vendorLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorLocation.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });


        ImageView vendorDaysAvailableIcon = (ImageView) mView.findViewById(R.id.vendorDaysAvailableIcon);
        final CustomTextView vendorDaysAvailable = (CustomTextView) mView.findViewById(R.id.vendorDaysAvailable);
        vendorDaysAvailableIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorDaysAvailable.getId(), getString(R.string.signup_location), getHomeActivity().getWeekData());
            }
        });

        vendorDaysAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(vendorDaysAvailable.getId(), getString(R.string.signup_location), getHomeActivity().getWeekData());
            }
        });


        ImageView dateIcon = (ImageView) mView.findViewById(R.id.dateIcon);
        dateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                //getHomeActivity().showDatePickerDialog(view);
            }
        });

        final CustomTextView startTimeText = (CustomTextView) mView.findViewById(R.id.startTimeText);

        ImageView startTimeIcon = (ImageView) mView.findViewById(R.id.startTimeIcon);
        startTimeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });

        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });

        ImageView startTimeDownIcon = (ImageView) mView.findViewById(R.id.startTimeDownIcon);
        startTimeDownIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(startTimeText);
            }
        });

        final CustomTextView endTimeText = (CustomTextView) mView.findViewById(R.id.endTimeText);

        ImageView endTimeIcon = (ImageView) mView.findViewById(R.id.endTimeIcon);
        endTimeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(endTimeText);
            }
        });


        endTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showTimerPickerDialog(endTimeText);
            }
        });

        ImageView endTimeDownIcon = (ImageView) mView.findViewById(R.id.endTimeDownIcon);
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
