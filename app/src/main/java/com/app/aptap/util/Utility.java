package com.app.aptap.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.app.aptap.AptapActivity;
import com.app.aptap.BaseCompactActivity;
import com.app.aptap.R;
import com.app.aptap.action.KeyboardVisibilityListener;
import com.app.aptap.model.InterestsModelObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

/**
 * Created by aditya on 8/26/2017.
 */

public class Utility {


    /**
     * Function is use to get/assign all permission.
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Function is use to get 1st phone number of device but most of the case it return null/empty
     * @param context
     * @return
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tMgr.getLine1Number()!=null && tMgr.getLine1Number().length()>=10) {
            if(tMgr.getLine1Number().contains("+91")) {
                return tMgr.getLine1Number().substring(3, tMgr.getLine1Number().length());
            } else if(tMgr.getLine1Number().contains("91")) {
                return tMgr.getLine1Number().substring(2, tMgr.getLine1Number().length());
            } else {
                return tMgr.getLine1Number();
            }
            //return tMgr.getLine1Number();
        } else {
            SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            String value = sharedpreferences.getString(Constants.SHARED_PREFERENCE_PHONE_NUMBER, null);
            if(value!=null) {
                return value;
            } else {
                return null;
            }
        }
    }

    /**
     * Function is use to get UUID of mobile device.
     * @return
     */
    /*public static String getUUIDMobile(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("Utility", "Android ID:-" + androidId);
        TelephonyManager tMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tMgr.getDeviceId()!=null && tMgr.getDeviceId().length()>0) {
            Log.d("Y=Utility", "UUID:-" + tMgr.getDeviceId());
            return tMgr.getDeviceId();
        } else {
            return null;
        }
    }*/

    public static void setKeyboardVisibilityListener(Activity activity, final KeyboardVisibilityListener keyboardVisibilityListener) {
        final View contentView = activity.findViewById(android.R.id.content);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int mPreviousHeight;

            @Override
            public void onGlobalLayout() {
                int newHeight = contentView.getHeight();
                if (mPreviousHeight != 0) {
                    if (mPreviousHeight > newHeight) {
                        // Height decreased: keyboard was shown
                        keyboardVisibilityListener.onKeyboardVisibilityChanged(true);
                    } else if (mPreviousHeight < newHeight) {
                        // Height increased: keyboard was hidden
                        keyboardVisibilityListener.onKeyboardVisibilityChanged(false);
                    } else {
                        // No change
                    }
                }
                mPreviousHeight = newHeight;
            }
        });
    }

    /**
     * Temp Function is use to get SHA key for facebook in android.
     * @param context
     */
    public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("com.app.aptap", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param ctx
     * @param v
     */
    public static void slideUp(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }


    /**
     *
     * @param ctx
     * @param v
     */
    public static void slideDown(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    // To animate view slide out from left to right
    public void slideToRight(View view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    // To animate view slide out from right to left
    public void slideToLeft(View view){
        TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from top to bottom
    public void slideToBottom(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from bottom to top
    public void slideToTop(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public static List<InterestsModelObject> checkInterest(List<InterestsModelObject> c, List<InterestsModelObject> t) {
        for(InterestsModelObject i : t) {
            for(InterestsModelObject o : c) {
                if(o != null && o.getId().equals(i.getId())) {
                    o.setIntrested(true);
                    break;
                } else {
                    o.setIntrested(false);
                }
            }
        }
        return c;
    }

}
