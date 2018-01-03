package com.app.aptap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import com.app.aptap.AptapActivity;
import com.app.aptap.BaseCompactActivity;
import com.app.aptap.R;
import com.app.aptap.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by @dity@ on 8/28/2017.
 */
public class SplashScreenActivity extends BaseCompactActivity {

    // Set Duration of the Splash Screen
    long delay = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get the view from activity_splash_screen.xml
        setContentView(R.layout.activity_splash_screen);

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();
                Intent myIntent;

                if(getStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID)!=null) {
                    myIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                } else {
                    myIntent = new Intent(SplashScreenActivity.this, AptapActivity.class);
                }
                // Start MainActivity.class
                //myIntent = new Intent(SplashScreenActivity.this, AptapActivity.class);
                ActivityCompat.finishAffinity(SplashScreenActivity.this);
                startActivity(myIntent);
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, delay);
    }
}
