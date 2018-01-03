package com.app.aptap.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.app.aptap.R;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.RecyclerAdapter;
import com.app.aptap.adapter.ServiceUtilityBookingAdapter;
import com.app.aptap.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServicesFragment} interface
 * to handle interaction events.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends MasterFragment implements View.OnClickListener {

    public static String TAG = ServicesFragment.class.getName();

    private View view;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
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
        view = inflater.inflate(R.layout.fragment_services, container, false);

//        initServicesRecyclerView();
//
//        initUtilityRecyclerView();
//
//        initBookingRecyclerView();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


//    private void initServicesRecyclerView() {
//        RecyclerView recyclerViewService = (RecyclerView) view.findViewById(R.id.recyclerViewService);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//        recyclerViewService.setLayoutManager(mLayoutManager);
//        recyclerViewService.setItemAnimator(new DefaultItemAnimator());
//        ServiceUtilityBookingAdapter recyclerAdapter = new ServiceUtilityBookingAdapter(getHomeActivity(), this, Constants.getServicesData());
//        recyclerViewService.setAdapter(recyclerAdapter);
//    }
//
//    private void initUtilityRecyclerView() {
//        RecyclerView recyclerViewService = (RecyclerView) view.findViewById(R.id.recyclerViewUtilityBill);
//        recyclerViewService.setNestedScrollingEnabled(false);
//        recyclerViewService.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        ServiceUtilityBookingAdapter recyclerAdapter = new ServiceUtilityBookingAdapter(getHomeActivity(), this, Constants.getUtilityData());
//        recyclerViewService.setAdapter(recyclerAdapter);
//    }
//
//    private void initBookingRecyclerView() {
//        RecyclerView recyclerViewService = (RecyclerView) view.findViewById(R.id.recyclerViewBooking);
//        recyclerViewService.setNestedScrollingEnabled(false);
//        recyclerViewService.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        ServiceUtilityBookingAdapter recyclerAdapter = new ServiceUtilityBookingAdapter(getHomeActivity(), this, Constants.getBookingData());
//        recyclerViewService.setAdapter(recyclerAdapter);
//    }

    @Override
    public void onClick(View v) {

    }
}
