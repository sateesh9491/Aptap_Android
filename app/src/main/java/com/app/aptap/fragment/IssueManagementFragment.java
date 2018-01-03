package com.app.aptap.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.app.aptap.R;
import com.app.aptap.adapter.HidingScrollListener;
import com.app.aptap.adapter.IssueListAdapter;
import com.app.aptap.adapter.SpinnerAdapter;
import com.app.aptap.util.Constants;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IssueManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IssueManagementFragment extends MasterFragment implements View.OnClickListener {
    public static String TAG = IssueManagementFragment.class.getName();

    private View mView;
    private LineChart mLineChart;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public IssueManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IssueManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IssueManagementFragment newInstance() {
        IssueManagementFragment fragment = new IssueManagementFragment();
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
        mView = inflater.inflate(R.layout.fragment_issue_management, container, false);

        mLineChart = (LineChart) mView.findViewById(R.id.issueChart);
        mLineChart.getAxisRight().setEnabled(false);

        mLineChart.setBackgroundColor(Color.rgb(104, 241, 175));
        // add data
        setData(45, 100);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.animateXY(2000, 2000);
        // dont forget to refresh the drawing
        mLineChart.invalidate();

        initRecyclerView();

        initPullDownRefresh();

        Spinner sp = (Spinner) mView.findViewById(R.id.issueGraphFilter);
        SpinnerAdapter adapter = new SpinnerAdapter(getHomeActivity(), R.layout.spinner_layout, R.id.txt, Constants.spinnerDumyData());
        sp.setAdapter(adapter);

        return mView;
    }

    private void initRecyclerView() {
        RecyclerView issueManagementList = (RecyclerView) mView.findViewById(R.id.issueManagementList);
        issueManagementList.setLayoutManager(new LinearLayoutManager(getActivity()));
        IssueListAdapter issueListAdapter = new IssueListAdapter(getHomeActivity(), this, Constants.createItemList());
        issueManagementList.setAdapter(issueListAdapter);
        //setting up our OnScrollListener
        issueManagementList.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
            }

            @Override
            public void onShow() {
            }
        });
    }

    private void initPullDownRefresh() {
        // enable pull down to refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something
                // after refresh is done, remember to call the following code
                if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);  // This hides the spinner
                }
            }
        });
    }

    private void setData(int count, float range) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 20;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mLineChart.setData(data);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.issueRowId:
                replaceFragment(CreateIssueFragment.newInstance(), true, CreateIssueFragment.TAG);
                break;
        }
    }
}
