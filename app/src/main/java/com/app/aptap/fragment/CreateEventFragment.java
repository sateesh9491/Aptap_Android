package com.app.aptap.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.aptap.R;
import com.app.aptap.ui.CustomTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEventFragment extends MasterFragment {

    public static String TAG = CreateEventFragment.class.getName();
    private View mView;
    private static final int PICK_CONTACT = 0;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEventFragment newInstance() {
        CreateEventFragment fragment = new CreateEventFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_event, container, false);


        ImageView locationEventIcon = (ImageView) mView.findViewById(R.id.locationEventIcon);
        final CustomTextView locationEventText = (CustomTextView) mView.findViewById(R.id.locationEventText);
        locationEventIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(locationEventText.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });

        locationEventText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showFlatWingSelecationDialog(locationEventText.getId(), getString(R.string.signup_location), getHomeActivity().getLocationData());
            }
        });

        ImageView startDateIcon = (ImageView) mView.findViewById(R.id.startDateIcon);
        final CustomTextView startDateText = (CustomTextView) mView.findViewById(R.id.startDateText);
        startDateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showDatePickerDialog(startDateText);
            }
        });

        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showDatePickerDialog(startDateText);
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


        ImageView endDateIcon = (ImageView) mView.findViewById(R.id.endDateIcon);
        final CustomTextView endDateText = (CustomTextView) mView.findViewById(R.id.endDateText);
        endDateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showDatePickerDialog(endDateText);
            }
        });

        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().showDatePickerDialog(endDateText);
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

        ImageView addInvitesIcon = (ImageView) mView.findViewById(R.id.addInvitesIcon);
        addInvitesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                // pass id here
                Intent it = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(it, PICK_CONTACT);
            }
        });

        CustomTextView addInvitesText = (CustomTextView) mView.findViewById(R.id.addInvitesText);
        addInvitesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                Intent it = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(it, PICK_CONTACT);
            }
        });


        ImageView sendSMSIcon = (ImageView) mView.findViewById(R.id.sendSMSIcon);
        sendSMSIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().openSmsMsgAppFnc("9975075219", "Hi");
            }
        });

        CustomTextView sendSMSText = (CustomTextView) mView.findViewById(R.id.sendSMSText);
        sendSMSText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                getHomeActivity().openSmsMsgAppFnc("9975075219", "Hi");
            }
        });


        return mView;
    }

}
