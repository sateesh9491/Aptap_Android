package com.app.aptap.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.app.aptap.R;
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
 * Use the {@link AdminManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminManagementFragment extends MasterFragment {
    public static String TAG = AdminManagementFragment.class.getName();


    private View mView;
    private LineChart mLineChart;

    public AdminManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AdminManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminManagementFragment newInstance() {
        AdminManagementFragment fragment = new AdminManagementFragment();
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
        mView = inflater.inflate(R.layout.fragment_admin_management, container, false);

        mLineChart = (LineChart) mView.findViewById(R.id.adminChart);
        mLineChart.getAxisRight().setEnabled(false);

        mLineChart.setBackgroundColor(Color.rgb(104, 241, 175));
        // add data
        setData(45, 100);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.animateXY(2000, 2000);
        // dont forget to refresh the drawing
        mLineChart.invalidate();

        RelativeLayout vendorCircularRL = (RelativeLayout) mView.findViewById(R.id.vendorCircularRL);
        vendorCircularRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(VendorManagementFragment.newInstance(), true, VendorManagementFragment.TAG);
            }
        });

        RelativeLayout memberCircularRL = (RelativeLayout) mView.findViewById(R.id.memberCircularRL);
        memberCircularRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(MemberManagementFragment.newInstance(), true, MemberManagementFragment.TAG);
            }
        });

        RelativeLayout issueCircularRL = (RelativeLayout) mView.findViewById(R.id.issueCircularRL);
        issueCircularRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(IssueManagementFragment.newInstance(), true, IssueManagementFragment.TAG);
            }
        });

        RelativeLayout assetCircularRL = (RelativeLayout) mView.findViewById(R.id.assetCircularRL);
        assetCircularRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(AssetsManagementFragment.newInstance(), true, AssetsManagementFragment.TAG);
            }
        });


        Spinner sp=(Spinner) mView.findViewById(R.id.graphFilter);
        SpinnerAdapter adapter=new SpinnerAdapter(getHomeActivity() ,R.layout.spinner_layout,R.id.txt, Constants.spinnerDumyData());
        sp.setAdapter(adapter);

        return mView;
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
            set1 = (LineDataSet)mLineChart.getData().getDataSetByIndex(0);
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

    // This is used to store x-axis values
}
