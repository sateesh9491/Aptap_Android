package com.app.aptap.activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.aptap.BaseCompactActivity;
import com.app.aptap.R;
import com.app.aptap.action.APICallback;
import com.app.aptap.fragment.AdministrationMasterFragment;
import com.app.aptap.fragment.HomeBaseFragment;
import com.app.aptap.fragment.HomeFragment;
import com.app.aptap.fragment.MasterFragment;
import com.app.aptap.fragment.SocietyFragment;
import com.app.aptap.fragment.WebLogin;
import com.app.aptap.model.HomeResponse;
import com.app.aptap.model.ResponseRegisterAPI;
import com.app.aptap.net.APICall;
import com.app.aptap.ui.CustomTextView;
import com.app.aptap.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class HomeActivity extends BaseCompactActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {

    public static String TAG = HomeActivity.class.getName();

    private String mFragmentTag = "";
    private MasterFragment mFragment = null;

    private boolean isSearchOpened = false;
    private EditText globalSearch;
    private Menu mMenu;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    private ImageView profileImageView;
    private TextView profileUserName, profileEmailId;
    //private PersonalDetails personalDetailsObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO SOMETHING HERE
                hideShowFabMenu();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //personalDetailsObj = bundle.getParcelable("PersonalDetails");
        }

        globalSearch = (EditText) findViewById(R.id.globalSearch);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerStateChanged(int newState) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
                super.onDrawerStateChanged(newState);
                hideShowFabMenu();
            }
        };
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);

        profileImageView = (ImageView) headView.findViewById(R.id.pImageView);

//        profileUserName = (TextView) headView.findViewById(R.id.pUserName);
//
//        profileEmailId = (TextView) headView.findViewById(R.id.pEmailId);

        if(getStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID)!=null) {
            Gson gson = new Gson();
            String jsonReqData = "[{\"uniqueId\":" + gson.toJson(getStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID)) + "}]";
            Log.d(TAG, "jsonReqData-HomeActivity:-" + jsonReqData);
            new APICall().callInterestAPI(getAPIInterfaceService(), "getHomepageUserInfo", jsonReqData, new APICallback() {
                @Override
                public void onSuccess(Response<ResponseBody> response) {
                    HomeResponse homeResponse = new HomeResponse();
                    if (response.isSuccessful()) {
                        try {
                            String responseStr = response.body().string();
                            Log.d(TAG, "callInterestAPI:onSuccess:HomeActivity:-" + responseStr);
                            Gson gson = new Gson();
                            homeResponse = gson.fromJson(responseStr, HomeResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (homeResponse.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
                        setValueToUI(homeResponse);
                    } else {
                        //fails dialog will added lter.
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
                }
            });

        }


    }

    private void setValueToUI(HomeResponse homeResponse) {
        if (homeResponse.getUserbase().getProfilepic() != null) {
            Picasso.with(HomeActivity.this)
                    .load(homeResponse.getUserbase().getProfilepic())
                    .into(profileImageView);
        }

        String usereName = null;
        if(homeResponse.getUserbase().getFirstname()!=null) {
            usereName = homeResponse.getUserbase().getFirstname();
        }
        if(homeResponse.getUserbase().getLastname()!=null) {
            usereName = usereName + " " + homeResponse.getUserbase().getLastname();
        }
        profileUserName.setText(usereName);

        if(homeResponse.getUserbase().getGmailid()!=null) {
            profileEmailId.setText(homeResponse.getUserbase().getGmailid());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() invoked");
        if (mFragment == null) {
            initFragment(Constants.MENU_HOME_INDES);
        }
    }

    @Override
    public void onBackPressed() {
        hideShowFabMenu();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (isSearchOpened) {
            toggleFabMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        hideShowFabMenu();
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            return true;
        }

        if (id == R.id.action_search) {
            toggleFabMenu();
            return true;
        }

        if (id == R.id.action_clear) {
            isSearchOpened = true;
            toggleFabMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            initFragment(Constants.MENU_HOME_INDES);
        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_archives) {

        } else if (id == R.id.nav_admin) {
            initFragment(Constants.MENU_ADMIN_INDES);
        } else if (id == R.id.nav_visiter) {

        } else if (id == R.id.nav_ice) {


        } else if (id == R.id.nav_logout) {

        }else if (id == R.id.nav_web_login){
            initFragment(Constants.MENU_WEBLOGIN_INDES);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * initialize fragment depends on selected navigation drawer menu
     *
     * @param position position/index for selected navigation drawer menu
     */
    private void initFragment(int position) {
        Log.d(TAG, "initFragment() invoked");

        if (position == Constants.MENU_HOME_INDES) {
            mFragment = new HomeBaseFragment();
            mFragmentTag = HomeBaseFragment.TAG;
        } else if (position == Constants.MENU_ADMIN_INDES) {
            mFragment = new AdministrationMasterFragment();
            mFragmentTag = AdministrationMasterFragment.TAG;
        } else if (position == Constants.MENU_WEBLOGIN_INDES) {
            mFragment = new WebLogin();
            mFragmentTag = WebLogin.TAG;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.popBackStackImmediate();

        fragmentTransaction.setCustomAnimations(R.anim.popup_show,
                R.anim.popup_hide, R.anim.popup_show,
                R.anim.popup_hide);
        fragmentTransaction.replace(R.id.fragmentContainer, mFragment, mFragmentTag);
        fragmentTransaction.commit();
        Log.d(TAG, new StringBuilder("TEST getBackStackEntryCount() ").append(fragmentManager.getBackStackEntryCount()).toString());

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toggleFabMenu() {
        if (!isSearchOpened) {
            globalSearch.setVisibility(View.VISIBLE);
            mMenu.findItem(R.id.action_notification).setVisible(false);
            mMenu.findItem(R.id.action_search).setVisible(false);
            mMenu.findItem(R.id.action_clear).setVisible(true);
        } else {
            globalSearch.setVisibility(View.GONE);
            mMenu.findItem(R.id.action_notification).setVisible(true);
            mMenu.findItem(R.id.action_search).setVisible(true);
            mMenu.findItem(R.id.action_clear).setVisible(false);
        }
        isSearchOpened = !isSearchOpened;
        enableViews(isSearchOpened);
    }

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     * <p>
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    private void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }
    }

    public void hideShowFabMenu() {
        if (HomeFragment.floatingHomeActionMenu.isOpen()) {
            HomeFragment.floatingHomeActionMenu.close(true);
        }
        if (SocietyFragment.floatingSocietyActionMenu.isOpen()) {
            SocietyFragment.floatingSocietyActionMenu.close(true);
        }
    }

    public void hideHomeFooterMenu() {
        int duration = 500;
        HomeFragment.fabHomePlus.animate().setDuration(duration).translationY(HomeFragment.footerHomeTabLayout.getHeight() + 200).setInterpolator(new AccelerateInterpolator(1)).start();
        HomeFragment.footerHomeTabLayout.animate().setDuration(duration).translationY(HomeFragment.footerHomeTabLayout.getHeight() + 60).setInterpolator(new AccelerateInterpolator(1)).start();
    }

    public void showHomeFooterMenu() {
        HomeFragment.fabHomePlus.animate().translationY(0).setInterpolator(new DecelerateInterpolator(1)).start();
        HomeFragment.footerHomeTabLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(1)).start();
    }

    public void hideSocietyFooterMenu() {
        int duration = 1000;
        SocietyFragment.fabSocietyPlus.animate().setDuration(duration).translationY(SocietyFragment.footerSocietyTabLayout.getHeight() + 200).setInterpolator(new AccelerateInterpolator(1)).start();
        SocietyFragment.footerSocietyTabLayout.animate().setDuration(duration).translationY(SocietyFragment.footerSocietyTabLayout.getHeight() + 60).setInterpolator(new AccelerateInterpolator(1)).start();
    }

    public void showSocietyFooterMenu() {
        SocietyFragment.fabSocietyPlus.animate().translationY(0).setInterpolator(new DecelerateInterpolator(1)).start();
        SocietyFragment.footerSocietyTabLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(1)).start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public void showTimerPickerDialog(final CustomTextView customTextView) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        int mSecond = c.get(Calendar.SECOND);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        customTextView.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void showDatePickerDialog(final CustomTextView customTextView) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        customTextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    public void openSmsMsgAppFnc(String mblNumVar, String smsMsgVar) {
        Intent smsMsgAppVar = new Intent(Intent.ACTION_VIEW);
        smsMsgAppVar.setData(Uri.parse("sms:" + mblNumVar));
        smsMsgAppVar.putExtra("sms_body", smsMsgVar);
        startActivity(smsMsgAppVar);
    }

}
