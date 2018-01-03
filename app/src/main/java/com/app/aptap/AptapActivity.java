package com.app.aptap;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.app.aptap.action.APICallback;
import com.app.aptap.activity.HomeActivity;
import com.app.aptap.activity.SignUpActivity;
import com.app.aptap.broadcast.SmsReceiver;
import com.app.aptap.fcm.Config;
import com.app.aptap.fcm.NotificationUtils;
import com.app.aptap.model.CheckLoginStatus;
import com.app.aptap.model.OTPResponse;
import com.app.aptap.model.ResponseRegisterAPI;
import com.app.aptap.model.UserData;
import com.app.aptap.net.APICall;
import com.app.aptap.ui.CustomEditText;
import com.app.aptap.ui.CustomTextView;
import com.app.aptap.util.Constants;
import com.app.aptap.util.Utility;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by @dity@ on 8/26/2017.
 */
public class AptapActivity extends BaseCompactActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static String TAG = AptapActivity.class.getName();

    int PERMISSION_ALL = 124;
    private String[] PERMISSIONS = {Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR};

    private LinearLayout masterLayoutAptapActivity;

    // FCM
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private LinearLayout googleLoginButton;
    //Signing Options
    private GoogleSignInOptions gso;
    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    //facebook login
    private CallbackManager callbackManager;
    private LinearLayout facebookLoginButton;

    private static String TYPE_OF_LOGIN_GMAIL = "gmail";
    private static String TYPE_OF_LOGIN_FACEBOOK = "facebook";

    private String phoneNumber = null;
    private CheckLoginStatus checkLoginStatus = null;
    private String gender = null;
    private String profilePicURL = null;
    private String facebookId = null;
    private String birthDate = null;

    private CustomEditText mobileNo;
    private CustomTextView sendOTPButton;

    private CustomEditText otpNo;
    private CustomTextView verifyOTP;
    private String otpMessage;

    private LinearLayout fgLayout;
    private LinearLayout mobileNumberLayout;
    private LinearLayout otpLayout;

    private String loginType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aptap);
        Utility.showHashKey(this);

        masterLayoutAptapActivity = (LinearLayout) findViewById(R.id.masterLayoutAptapActivity);
        fgLayout = (LinearLayout) findViewById(R.id.fgLayout);
        mobileNumberLayout = (LinearLayout) findViewById(R.id.mobileNumberLayout);
        otpLayout = (LinearLayout) findViewById(R.id.otpLayout);

        //To bypass login
        //skipSignUpAction();

        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        //Toast.makeText(AptapActivity.this, "FB onSuccess", Toast.LENGTH_LONG).show();
                        //Snackbar.make(masterLayoutAptapActivity, "FB onSuccess", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        getFBUserDetails(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Snackbar.make(masterLayoutAptapActivity, "FB onCancel", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        //Toast.makeText(AptapActivity.this, "FB onCancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        exception.printStackTrace();
                        Snackbar.make(masterLayoutAptapActivity, "FB onError", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        //Toast.makeText(AptapActivity.this, "FB onError", Toast.LENGTH_LONG).show();
                    }
                });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    //Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    Snackbar.make(masterLayoutAptapActivity, "Push notification: " + message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                }
            }
        };

        displayFirebaseRegId();

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail().requestIdToken(getString(R.string.default_web_client_id))
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleLoginButton = (LinearLayout) findViewById(R.id.g_sign_in_button);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!Utility.hasPermissions(AptapActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(AptapActivity.this, PERMISSIONS, PERMISSION_ALL);
                    } else {
                        getPhoneNoDetails(TYPE_OF_LOGIN_GMAIL);
                    }
                } else {
                    getPhoneNoDetails(TYPE_OF_LOGIN_GMAIL);
                }
            }
        });

        facebookLoginButton = (LinearLayout) findViewById(R.id.f_sign_in_button);
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!Utility.hasPermissions(AptapActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(AptapActivity.this, PERMISSIONS, PERMISSION_ALL);
                    } else {
                        getPhoneNoDetails(TYPE_OF_LOGIN_FACEBOOK);
                        //LoginManager.getInstance().logInWithReadPermissions(AptapActivity.this, Arrays.asList("email", "public_profile"));
                    }
                } else {
                    getPhoneNoDetails(TYPE_OF_LOGIN_FACEBOOK);
                    //LoginManager.getInstance().logInWithReadPermissions(AptapActivity.this, Arrays.asList("email", "public_profile"));
                }

            }
        });


        mobileNo = (CustomEditText) findViewById(R.id.mobileNo);
        mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence pRequest, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence pRequest, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() == 10) {
                    if (android.util.Patterns.PHONE.matcher(mobileNo.getText().toString()).matches()) {

                    } else {
                        mobileNo.setError(getString(R.string.mobile_number_error_msg));
                    }
                }
            }
        });

        sendOTPButton = (CustomTextView) findViewById(R.id.sendOTPButton);
        sendOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobileNo.length() == 10 && android.util.Patterns.PHONE.matcher(mobileNo.getText().toString()).matches()) {
                    //send otp
                    phoneNumber = mobileNo.getText().toString();

                    new APICall().getOTPAPI(getAPIInterfaceService(), "India", Long.valueOf(91), phoneNumber, new APICallback() {
                        @Override
                        public void onSuccess(Response<ResponseBody> response) {
                            Log.d(TAG, "callAPIFunction:onSuccess:responseStr:sendotp:-" + response);
                            OTPResponse otpResponse = new OTPResponse();
                            if (response.isSuccessful()) {
                                try {
                                    String responseStr = response.body().string();
                                    Log.d(TAG, "callAPIFunction:onSuccess:responseStr:sendotp:-" + responseStr);
                                    Gson gson = new Gson();
                                    otpResponse = gson.fromJson(responseStr, OTPResponse.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JsonParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (otpResponse.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
                                fgLayout.setVisibility(View.GONE);
                                mobileNumberLayout.setVisibility(View.GONE);
                                otpLayout.setVisibility(View.VISIBLE);
                            } else {
                                //fails dialog will added lter.
                                Snackbar.make(masterLayoutAptapActivity, "OTP Failed" + otpResponse.getStatus(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Log.d(TAG, "callAPIFunction:onFailure:responseStr:sendotp:-" + throwable.getMessage().toString());
                            Snackbar.make(masterLayoutAptapActivity, throwable.getMessage(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                    });


                    //String tempReqData = "[{\"toNumber\":\"" + phoneNumber + "\"}]";


                    /*new APICall().callAllAPI(getAPIInterfaceService(), "sendotp", tempReqData, new APICallback() {
                        @Override
                        public void onSuccess(Response<ResponseBody> response) {
                            OTPResponse otpResponse = new OTPResponse();
                            if (response.isSuccessful()) {
                                try {
                                    String responseStr = response.body().string();
                                    Log.d(TAG, "callAPIFunction:onSuccess:responseStr:sendotp:-" + responseStr);
                                    Gson gson = new Gson();
                                    otpResponse = gson.fromJson(responseStr, OTPResponse.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JsonParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (otpResponse.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
                                fgLayout.setVisibility(View.GONE);
                                mobileNumberLayout.setVisibility(View.GONE);
                                otpLayout.setVisibility(View.VISIBLE);
                            } else {
                                //fails dialog will added lter.
                                Snackbar.make(masterLayoutAptapActivity, "OTP Failed" + otpResponse.getStatus(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
                            Snackbar.make(masterLayoutAptapActivity, throwable.getMessage(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                    });*/

                } else {
                    //Please enter valid mobile number alert shown here
                    mobileNo.setError(getString(R.string.mobile_number_error_msg));
                }
            }
        });

        otpNo = (CustomEditText) findViewById(R.id.otpNo);
        otpNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence pRequest, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence pRequest, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6) {
                    if (!otpNo.getText().toString().equals(otpMessage)) {
                        otpNo.setError(getString(R.string.otp_error_msg_wrong));
                    } else {
                        //storeStringValueInSP(Constants.SHARED_PREFERENCE_PHONE_NUMBER, phoneNumber);
                        if (loginType == TYPE_OF_LOGIN_GMAIL) {
                            signIn();
                        } else {
                            LoginManager.getInstance().logInWithReadPermissions(AptapActivity.this, Arrays.asList("email", "public_profile"));
                        }
                    }
                }
            }
        });

        verifyOTP = (CustomTextView) findViewById(R.id.verifyOTP);
        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpNo.getText() == null) {
                    otpNo.setError(getString(R.string.otp_error_msg_blank));
                    return;
                } else if (!otpNo.getText().toString().equals(otpMessage)) {
                    otpNo.setError(getString(R.string.otp_error_msg_wrong));
                    return;
                } else {
                    //storeStringValueInSP(Constants.SHARED_PREFERENCE_PHONE_NUMBER, phoneNumber);
                    if (loginType == TYPE_OF_LOGIN_GMAIL) {
                        signIn();
                    } else {
                        LoginManager.getInstance().logInWithReadPermissions(AptapActivity.this, Arrays.asList("email", "public_profile"));
                    }
                }
            }
        });

    }

    protected void onResume() {
        // Logs 'install' and 'app activate' App Events.
        //AppEventsLogger.activateApp(this);

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        LocalBroadcastManager.getInstance(this).
                registerReceiver(smsReceiver, new IntentFilter("otp"));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());

        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        //AppEventsLogger.deactivateApp(this);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(smsReceiver);
    }


    private SmsReceiver smsReceiver = new SmsReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                //Toast.makeText(AptapActivity.this, "Message: " + message, Toast.LENGTH_LONG).show();
                if (message != null) {
                    otpMessage = message;
                    otpNo.setText(otpMessage);
                }
            }
        }
    };


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        Log.d(TAG, "Firebase reg id: " + regId);
    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            getGoogleAccountDetails(acct);
        } else {
            //If login fails
            //Toast.makeText(this, "Login Failed-" + result.getStatus() + "|" + result.toString(), Toast.LENGTH_LONG).show();
            Log.d(TAG, "Gmail Faild:-" + result.toString());
            Snackbar.make(masterLayoutAptapActivity, "Login Failed-" + result.getStatus() + "|" + result.toString(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
        Snackbar.make(masterLayoutAptapActivity, "Connection Failed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

    }

    protected void getGoogleAccountDetails(GoogleSignInAccount acct) {

        String personName = acct.getDisplayName();
        if (acct.getPhotoUrl() != null)
            profilePicURL = acct.getPhotoUrl().toString();
        String email = acct.getEmail();

        checkLoginStatus = new CheckLoginStatus();

        if (phoneNumber != null)
            checkLoginStatus.setMobile(phoneNumber);
        checkLoginStatus.setFirstname(personName);
        checkLoginStatus.setGmailid(email);
        checkLoginStatus.setTypeoflogin(TYPE_OF_LOGIN_GMAIL);
        checkLoginStatus.setOtp(otpMessage);

        callRegisterUserAPI(checkLoginStatus);

        storeStringValueInSP(Constants.SHARED_PREFERENCE_PHONE_NUMBER, phoneNumber);
    }

    protected void getFBUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        //Toast.makeText(AptapActivity.this, "Access Token FB-" + json_object.toString(), Toast.LENGTH_LONG).show();
                        setProfileToView(json_object);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender, birthday");
        data_request.setParameters(parameters);
        data_request.executeAsync();
    }

    private void setProfileToView(JSONObject jsonObject) {
        try {
            checkLoginStatus = new CheckLoginStatus();
            checkLoginStatus.setTypeoflogin(TYPE_OF_LOGIN_FACEBOOK);
            facebookId = jsonObject.getString("id");
            checkLoginStatus.setFacebookid(facebookId);
            URL profile_pic = null;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + facebookId + "/picture?type=large");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            if (phoneNumber != null)
                checkLoginStatus.setMobile(phoneNumber);
            if (jsonObject.has("name"))
                checkLoginStatus.setFirstname(jsonObject.getString("name"));
            if (jsonObject.has("first_name"))
                checkLoginStatus.setFirstname(jsonObject.getString("first_name"));
            if (jsonObject.has("last_name"))
                checkLoginStatus.setLastname(jsonObject.getString("last_name"));
            if (jsonObject.has("email"))
                checkLoginStatus.setGmailid(jsonObject.getString("email"));
            if (jsonObject.has("gender"))
                gender = jsonObject.getString("gender");
            if (jsonObject.has("birthday"))
                birthDate = jsonObject.getString("birthday");
            if (profile_pic != null)
                profilePicURL = profile_pic.toString();
            checkLoginStatus.setOtp(otpMessage);

            callRegisterUserAPI(checkLoginStatus);

            storeStringValueInSP(Constants.SHARED_PREFERENCE_PHONE_NUMBER, phoneNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function is use to start signup activity.
     */
    private void startSignUpHomeActivity(ResponseRegisterAPI responseRegisterAPI, int activityIndex) {
        Intent intent = null;
        if (activityIndex == 1) {
            intent = new Intent(AptapActivity.this, SignUpActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("ResponseRegisterAPI", responseRegisterAPI);
            intent.putExtras(bundle);
            //intent.putParcelable("ResponseRegisterAPI", responseRegisterAPI);
        } else if (activityIndex == 2) {
            intent = new Intent(AptapActivity.this, HomeActivity.class);
        } else {
        }

        ActivityCompat.finishAffinity(this);
        startActivity(intent);
    }

    private void callRegisterUserAPI(CheckLoginStatus checkLoginStatus) {
//        new APICall().callAPIFunction(getAPIInterfaceService(), "userreg", checkLoginStatus, new APICallback() {
//            @Override
//            public void onSuccess(Response<ResponseBody> response) {
//                ResponseRegisterAPI responseRegisterAPI = new ResponseRegisterAPI();
//                if (response.isSuccessful()) {
//                    try {
//                        String responseStr = response.body().string();
//                        Log.d(TAG, "callRegisterUserAPI:onSuccess:responseStr:userreg:-" + responseStr);
//                        Gson gson = new Gson();
//                        responseRegisterAPI = gson.fromJson(responseStr, ResponseRegisterAPI.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JsonParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (responseRegisterAPI.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
//                    // store unique id into shared prefrences
//                    storeStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID, responseRegisterAPI.getUserbase().getUniqueId());
//                    startSignUpHomeActivity(responseRegisterAPI, 1);
//                } else if (responseRegisterAPI.getStatus().equals(Constants.API_STATUS_WARNING)) {
//                    // store unique id into shared prefrences
//                    storeStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID, responseRegisterAPI.getUserData().getUniqueId());
//                    //startSignUpHomeActivity(responseRegisterAPI, 1);
//                    final ResponseRegisterAPI finalResponseRegisterAPI = responseRegisterAPI;
//                    showMessageYesNo(responseRegisterAPI.getStatus(), responseRegisterAPI.getMessage(),
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (which == -1) {
//                                        onWarningMessageReceiveAPICall(finalResponseRegisterAPI.getUserData());
//                                    } else {
//                                        // If user press no button that we need to handle
//                                        startSignUpHomeActivity(null, 2);
//                                    }
//                                }
//                            });
//                    return;
//                } else {
//                    //fails
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
//            }
//        });
    }

    private void onWarningMessageReceiveAPICall(UserData userData) {

        Gson gson = new Gson();
        String json = "[" + gson.toJson(userData) + "]";
        Log.d(TAG, new StringBuilder().append("onWarningMessageReceiveAPICall:json:-").append(json).toString());

        new APICall().callAllAPI(getAPIInterfaceService(), "updateusersocialid", json, new APICallback() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                ResponseRegisterAPI responseRegisterAPI = new ResponseRegisterAPI();
                if (response.isSuccessful()) {
                    try {
                        String responseStr = response.body().string();
                        Log.d(TAG, "onWarningMessageReceiveAPICall:onSuccess:responseStr:userreg:-" + responseStr);
                        Gson gson = new Gson();
                        responseRegisterAPI = gson.fromJson(responseStr, ResponseRegisterAPI.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                }

                if (responseRegisterAPI.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
                    // store unique id into shared prefrences
                    storeStringValueInSP(Constants.SHARED_PREFERENCE_UNIQUE_ID, responseRegisterAPI.getUserbase().getUniqueId());
                    startSignUpHomeActivity(responseRegisterAPI, 2);
                } else {
                    //fails
                    Log.d(TAG, new StringBuilder().append("onWarningMessageReceiveAPICall:onFailure:-").append("Error").toString());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //Toast.makeText(AptapActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                    Snackbar.make(masterLayoutAptapActivity, "Permission Denied!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * @param pTypeOfLogin
     */
    private void getPhoneNoDetails(String pTypeOfLogin) {
        phoneNumber = Utility.getPhoneNumber(AptapActivity.this);
        loginType = pTypeOfLogin;
        fgLayout.setVisibility(View.GONE);
        mobileNumberLayout.setVisibility(View.VISIBLE);
        if (phoneNumber != null) {
            mobileNo.setText(phoneNumber);
        }
    }

    /*private void skipSignUpAction() {
        Intent intent = new Intent(AptapActivity.this, SignUpActivity.class);;
        PersonalDetails personalDetails = new PersonalDetails();
        intent.putExtra("PersonalDetails", personalDetails);
        ActivityCompat.finishAffinity(this);
        startActivity(intent);
    }*/

}