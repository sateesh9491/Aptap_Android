package com.app.aptap.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.aptap.R;
import com.app.aptap.action.SelectedImageBitmapAndPath;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePostFragment extends MasterFragment {

    public static String TAG = CreatePostFragment.class.getName();

    private View mView;
    private RadioGroup radioGroup;
    private RadioButton sound, vibration, silent;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreatePostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePostFragment newInstance() {
        CreatePostFragment fragment = new CreatePostFragment();
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
        mView = inflater.inflate(R.layout.fragment_create_post, container, false);

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

        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.uploadPicLL);
        linearLayout.setOnClickListener(new View.OnClickListener() {
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
