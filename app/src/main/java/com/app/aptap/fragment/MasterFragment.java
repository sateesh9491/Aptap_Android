package com.app.aptap.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.app.aptap.R;
import com.app.aptap.activity.HomeActivity;
import com.app.aptap.ui.CustomTextView;


public abstract class MasterFragment extends Fragment {
    private static final String TAG = "MasterFragment";
    protected Application mApplicationContext = null;
    protected Activity mActivityInstance = null;

    /**
     * @return APPTAP instance/context
     */
    public HomeActivity getHomeActivity() {
        return (HomeActivity) getActivity();
    }

    /**
     * Constructor
     */
    public MasterFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mApplicationContext = activity.getApplication();
        mActivityInstance = getHomeActivity();
    }

    /**
     * @param fragment
     * @param addToBackStack
     */
    public void replaceFragment(MasterFragment fragment, boolean addToBackStack, String tagName) {
        FragmentTransaction transaction = getHomeActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.popup_show,
                R.anim.popup_hide, R.anim.popup_show,
                R.anim.popup_hide);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.fragmentContainer, fragment, tagName);
        transaction.commit();
    }


    /**
     * @param fragment
     * @param addToBackStack
     */
    public void replaceChildHomeFragment(MasterFragment fragment, boolean addToBackStack, String tagName) {
        FragmentTransaction transaction = getHomeActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.popup_show,
                R.anim.popup_hide, R.anim.popup_show,
                R.anim.popup_hide);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.childFragmentHomeContainer, fragment, tagName);
        transaction.commit();
    }

    /**
     * @param fragment
     * @param addToBackStack
     */
    public void replaceChildSocietyFragment(MasterFragment fragment, boolean addToBackStack, String tagName) {
        FragmentTransaction transaction = getHomeActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.popup_show,
                R.anim.popup_hide, R.anim.popup_show,
                R.anim.popup_hide);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.childFragmentSocietyContainer, fragment, tagName);
        transaction.commit();
    }



    /**
     * @param currentFragment
     * @param newFragment
     * @param addToBackStack
     * @param tagName
     * @return
     */
    public boolean hideFragment(MasterFragment currentFragment, MasterFragment newFragment, boolean addToBackStack, String tagName) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.popup_show,
                R.anim.popup_hide, R.anim.popup_show,
                R.anim.popup_hide);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.hide(currentFragment);
        transaction.add(R.id.fragmentContainer, newFragment, tagName);
        transaction.commit();
        return false;
    }

    /**
     *
     */
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getHomeActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = getHomeActivity().getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

    /**
     * Checks whether an internet connection is available
     *
     * @return
     */
    public boolean isInternetConnected() {
        boolean hasInternetConnection = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiConnection != null) {
            if (wifiConnection.isConnected()) {
                hasInternetConnection = true;
            }
        }
        if (mobileConnection != null) {
            if (mobileConnection.isConnected()) {
                hasInternetConnection = true;
            }
        }
        return hasInternetConnection;
    }

    protected void showFlatWingSelecationDialog(final int id, String title, final CharSequence[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                CustomTextView customTextView=(CustomTextView)getActivity().findViewById(id);
                customTextView.setText(items[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected CharSequence[] getBlockData() {
        CharSequence[] items = {
                getString(R.string.create_member_dummy_data_1), getString(R.string.create_member_dummy_data_2)
        };
        return items;
    }
}
