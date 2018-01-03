package com.app.aptap.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.aptap.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateGroupFragment extends MasterFragment {

    public static String TAG = CreateGroupFragment.class.getName();

    private View mView;
    private RadioGroup radioGroup;
    private RadioButton societyGroup, privateGroup, publicGroup;


    public CreateGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CreateGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateGroupFragment newInstance() {
        CreateGroupFragment fragment = new CreateGroupFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_group, container, false);

        radioGroup = (RadioGroup) mView.findViewById(R.id.radioPostGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.postPublic) {
                    Toast.makeText(getHomeActivity(), "choice: Silent",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.postPrivate) {
                    Toast.makeText(getHomeActivity(), "choice: Sound",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getHomeActivity(), "choice: Vibration",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        return mView;
    }

}
