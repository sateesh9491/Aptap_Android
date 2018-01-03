package com.app.aptap.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.app.aptap.R;
import com.app.aptap.adapter.ServiceUtilityBookingAdapter;
import com.app.aptap.model.InterestsModelObject;
import com.app.aptap.model.LatestGroupObject;
import com.app.aptap.model.ServiceUtilityBookingObject;
import com.app.aptap.model.SpinnerItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aditya on 9/3/2017.
 */

public class Constants {

    public static int MENU_HOME_INDES = 0;
    public static int MENU_ADMIN_INDES = 3;
    public static int MENU_MEMBER_INDES = 4;
    public static int MENU_WEBLOGIN_INDES = 9;
    public static boolean MENU_HOME = true;

    public static String DATE_FORMAT = "dd-MM-yyyy";

    public static String API_STATUS_SUCCESS = "success";
    public static String API_STATUS_WARNING = "warning";
    public static String API_STATUS_FAIL = "failed";

    //SHARED_PREFERENCE

    public static String SHARED_PREFERENCE_NAME = "APTAP";
    public static String SHARED_PREFERENCE_UNIQUE_ID = "UNIQUE_ID";
    public static String SHARED_PREFERENCE_PHONE_NUMBER = "PHONE_NUMBER";

    public static int OTP_LENGTH = 6;


    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static void disableEventBubbling(View... views){
        for(View view : views){
            if(view != null){
                view.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View view, MotionEvent event){
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                });
            }
        }
    }


    public static List<String> createItemList() {
        List<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");
        strings.add("F");
        strings.add("G");
        strings.add("H");
        strings.add("I");
        strings.add("J");
        strings.add("H");
        strings.add("I");
        strings.add("J");
        strings.add("H");
        strings.add("I");
        strings.add("J");

        return strings;
    }

    public static List<SpinnerItemData> spinnerDumyData() {
        ArrayList<SpinnerItemData> list=new ArrayList<>();
        list.add(new SpinnerItemData(1, "Khr", ""));
        list.add(new SpinnerItemData(2, "Usd",""));
        list.add(new SpinnerItemData(3, "Jpy",""));
        list.add(new SpinnerItemData(4, "Aud",""));
        return list;
    }


    public static List<ServiceUtilityBookingObject> getServicesData() {
        ArrayList<ServiceUtilityBookingObject> list=new ArrayList<>();
        ServiceUtilityBookingObject serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(1);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Cabs");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(2);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Food");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(3);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Groceries");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(4);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("NearBuy");
        list.add(serviceUtilityBookingObject);

        return list;
    }


    public static List<LatestGroupObject> getGroupData() {
        ArrayList<LatestGroupObject> list=new ArrayList<>();
        LatestGroupObject serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(1);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Cabs");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(2);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Food");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(3);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Groceries");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(4);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("NearBuy");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(5);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("NearBuy");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(6);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("NearBuy");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new LatestGroupObject();
        serviceUtilityBookingObject.setId(7);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("NearBuy");
        list.add(serviceUtilityBookingObject);

        return list;
    }


    public static List<ServiceUtilityBookingObject> getUtilityData() {
        ArrayList<ServiceUtilityBookingObject> list=new ArrayList<>();
        ServiceUtilityBookingObject serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(1);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Power");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(2);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Radio");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(3);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("DTH");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(4);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("GAS");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(5);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Internet");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(6);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Mobile");
        list.add(serviceUtilityBookingObject);

        return list;
    }

    public static List<ServiceUtilityBookingObject> getBookingData() {
        ArrayList<ServiceUtilityBookingObject> list=new ArrayList<>();
        ServiceUtilityBookingObject serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(1);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Movies");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(2);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Flights");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(3);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Train");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(4);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Bus");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(5);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("Show");
        list.add(serviceUtilityBookingObject);

        serviceUtilityBookingObject = new ServiceUtilityBookingObject();
        serviceUtilityBookingObject.setId(6);
        serviceUtilityBookingObject.setServiceImageURL(null);
        serviceUtilityBookingObject.setServiceName("TV");
        list.add(serviceUtilityBookingObject);

        return list;
    }


}
