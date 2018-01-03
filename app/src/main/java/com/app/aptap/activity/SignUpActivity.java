package com.app.aptap.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.aptap.BaseCompactActivity;
import com.app.aptap.R;
import com.app.aptap.action.APICallback;
import com.app.aptap.adapter.InterestsAdapter;
import com.app.aptap.model.InterestsModelObject;
import com.app.aptap.model.IntrestResponse;
import com.app.aptap.model.ResponseRegisterAPI;
import com.app.aptap.model.SignUpRequestModelObject;
import com.app.aptap.net.APICall;
import com.app.aptap.ui.CustomEditText;
import com.app.aptap.ui.CustomTextView;
import com.app.aptap.util.Constants;
import com.app.aptap.util.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignUpActivity extends BaseCompactActivity implements View.OnClickListener {
    public static String TAG = SignUpActivity.class.getName();

    private CustomTextView signUpButton;
    private ImageView dateOfBirthIcon, genderIcon, locationIcon;
    private CustomTextView dateOfBirth, gender, location;

    private LinearLayout personalDetails, societyDetails, securityDetails, utilitiesDetails, interestsDetails;
    private ImageView personalDetailsArrow, securityDetailsArrow, utilitiesDetailsArrow, interestsDetailsArrow;

    private ResponseRegisterAPI responseRegisterAPIObj = null;

    private CustomEditText firstName, lastName, phoneNumber, gmailId, facebookId, userHashTag;

    private RecyclerView mRecyclerView;
    private InterestsAdapter interestsAdapter;
    private List<InterestsModelObject> interestsModelObjectList;// = Constants.createInterestItemList();

    private SignUpRequestModelObject signUpRequestModelObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpRequestModelObject = new SignUpRequestModelObject();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            responseRegisterAPIObj = bundle.getParcelable("ResponseRegisterAPI");
        }

        personalDetails = (LinearLayout) findViewById(R.id.personalDetailsScrollView);
        personalDetailsArrow = (ImageView) findViewById(R.id.personalDetailsArrow);

        societyDetails = (LinearLayout) findViewById(R.id.societyDetailsScrollView);

        securityDetails = (LinearLayout) findViewById(R.id.securityDetailsScrollView);
        securityDetailsArrow = (ImageView) findViewById(R.id.securityDetailsArrow);

        utilitiesDetails = (LinearLayout) findViewById(R.id.utilitiesDetailsScrollView);
        utilitiesDetailsArrow = (ImageView) findViewById(R.id.utilitiesDetailsArrow);

        interestsDetails = (LinearLayout) findViewById(R.id.interestsDetailsScrollView);
        interestsDetailsArrow = (ImageView) findViewById(R.id.interestsDetailsArrow);

        signUpButton = (CustomTextView) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start Activity
                setAllRequestDataForSignUp();
                signUpRequestModelObject.setIntrest(interestsModelObjectList);

                Gson gson = new Gson();
                String jsonReqData = "[" + gson.toJson(signUpRequestModelObject) + "]";
                Log.d(TAG, "jsonReqData-signUpButton:-" + jsonReqData);

                new APICall().callAllAPI(getAPIInterfaceService(), "useradditionalinputsreg", jsonReqData, new APICallback() {
                    @Override
                    public void onSuccess(Response<ResponseBody> response) {
                        ResponseRegisterAPI responseRegisterAPI = new ResponseRegisterAPI();
                        if (response.isSuccessful()) {
                            try {
                                String responseStr = response.body().string();
                                Log.d(TAG, "callAPIFunction:onSuccess:signUpButton:-" + responseStr);
                                Gson gson = new Gson();
                                responseRegisterAPI = gson.fromJson(responseStr, ResponseRegisterAPI.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            }
                        }

                        if (responseRegisterAPI.getStatus().equals(Constants.API_STATUS_SUCCESS)) {
                            Intent homeActivity = new Intent(SignUpActivity.this, HomeActivity.class);
                            ActivityCompat.finishAffinity(SignUpActivity.this);
                            startActivity(homeActivity);
                        } else {
                            //fails dialog will added lter.
                            Log.d(TAG, new StringBuilder().append("useradditionalinputsreg:onFailure:-").append("Error").toString());
                        }

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
                    }
                });


            }
        });

        dateOfBirthIcon = (ImageView) findViewById(R.id.dateOfBirthIcon);
        dateOfBirthIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showDialog(dateOfBirth.getId());
            }
        });

        dateOfBirth = (CustomTextView) findViewById(R.id.dateOfBirth);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showDialog(dateOfBirth.getId());
            }
        });

        genderIcon = (ImageView) findViewById(R.id.genderIcon);
        genderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showGenderAndLocationSelecationDialog(gender.getId(), getString(R.string.signup_gender), getGenderData());
            }
        });

        gender = (CustomTextView) findViewById(R.id.gender);
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showGenderAndLocationSelecationDialog(gender.getId(), getString(R.string.signup_gender), getGenderData());
            }
        });

        locationIcon = (ImageView) findViewById(R.id.locationIcon);
        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showGenderAndLocationSelecationDialog(location.getId(), getString(R.string.signup_location), getLocationData());
            }
        });

        location = (CustomTextView) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id here
                showGenderAndLocationSelecationDialog(location.getId(), getString(R.string.signup_location), getLocationData());
            }
        });

        setPersonalDataInToUI();

        if(responseRegisterAPIObj==null) {
            return;
        }
        String tempReqData = "[{\"uniqueId\":\"" + responseRegisterAPIObj.getUserbase().getUniqueId() + "\"}]";
        new APICall().callInterestAPI(getAPIInterfaceService(), "userIntrests", tempReqData, new APICallback() {
            @Override
            public void onSuccess(Response<ResponseBody> response) {
                IntrestResponse intrestResponse = new IntrestResponse();
                if (response.isSuccessful()) {
                    try {
                        String responseStr = response.body().string();
                        Log.d(TAG, "callInterestAPI:onSuccess:responseStr:-" + responseStr);
                        Gson gson = new Gson();
                        intrestResponse = gson.fromJson(responseStr, IntrestResponse.class);
                        interestsModelObjectList = intrestResponse.getResdata();
                        //match and make it true
                        //interestsModelObjectList = Utility.checkInterest(intrestResponse.getResdata(), responseRegisterAPIObj.getUserInterest());

                        for(int k=0; k<interestsModelObjectList.size();k++) {
                            for(InterestsModelObject p : responseRegisterAPIObj.getUserInterest()) {
                                if(interestsModelObjectList.get(k).getId()==p.getId()) {
                                    interestsModelObjectList.get(k).setIntrested(true);
                                }
                            }
                            //Log.d(TAG, "Result:-" + interestsModelObjectList.get(k).getId()+"|"+interestsModelObjectList.get(k).getIntrestType()+"|"+interestsModelObjectList.get(k).getIntrested());
                        }


                        setInterestDataAndView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, new StringBuilder().append("callAPIFunction:onFailure:-").append(throwable.getMessage()).toString());
            }
        });


    }

    private void setPersonalDataInToUI() {
        firstName = (CustomEditText) findViewById(R.id.firstName);
        if(responseRegisterAPIObj.getUserbase().getFirstname()!=null) {
            firstName.setText(responseRegisterAPIObj.getUserbase().getFirstname());
        }

        lastName = (CustomEditText) findViewById(R.id.lastName);
        if (responseRegisterAPIObj.getUserbase().getLastname() != null) {
            lastName.setText(responseRegisterAPIObj.getUserbase().getLastname());
        }

        phoneNumber = (CustomEditText) findViewById(R.id.phoneNumber);
        if (responseRegisterAPIObj.getUserbase().getMobile() != null) {
            phoneNumber.setText(responseRegisterAPIObj.getUserbase().getMobile());
        }

        gmailId = (CustomEditText) findViewById(R.id.gmailId);
        if (responseRegisterAPIObj.getUserbase().getGmailid() != null) {
            gmailId.setText(responseRegisterAPIObj.getUserbase().getGmailid());
        }

        facebookId = (CustomEditText) findViewById(R.id.facebookId);
        if (responseRegisterAPIObj.getUserbase().getFacebookid() != null) {
            facebookId.setText(responseRegisterAPIObj.getUserbase().getFacebookid());
        }

        userHashTag = (CustomEditText) findViewById(R.id.userHashTag);
        if (responseRegisterAPIObj.getUserbase().getUserhashtag() != null) {
            userHashTag.setText(responseRegisterAPIObj.getUserbase().getUserhashtag());
        }

        if (responseRegisterAPIObj.getUserbase().getDateofbirth() != null) {
            dateOfBirth.setText(responseRegisterAPIObj.getUserbase().getDateofbirth());
        }

        if (responseRegisterAPIObj.getUserbase().getGender() != null) {
            gender.setText(responseRegisterAPIObj.getUserbase().getGender());
        }

        if (responseRegisterAPIObj.getUserbase().getLocation() != null) {
            location.setText(responseRegisterAPIObj.getUserbase().getLocation());
        }
    }

    private void setAllRequestDataForSignUp() {
        if(responseRegisterAPIObj.getUserbase().getUniqueId()!=null) {
            signUpRequestModelObject.setUniqueId(responseRegisterAPIObj.getUserbase().getUniqueId());
        }
        if(firstName.getText()!=null) {
            signUpRequestModelObject.setFirstname(firstName.getText().toString());
        }
        if(lastName.getText()!=null) {
            signUpRequestModelObject.setLastname(lastName.getText().toString());
        }
        if(phoneNumber.getText()!=null) {
            signUpRequestModelObject.setMobile(phoneNumber.getText().toString());
        }
        if(gmailId.getText()!=null) {
            signUpRequestModelObject.setGmailid(gmailId.getText().toString());
        }
        if(facebookId.getText()!=null) {
            signUpRequestModelObject.setFacebookid(facebookId.getText().toString());
        }
        if(userHashTag.getText()!=null) {
            signUpRequestModelObject.setUserhashtag(userHashTag.getText().toString());
        }
        if(dateOfBirth.getText()!=null) {
            signUpRequestModelObject.setDateofbirth(dateOfBirth.getText().toString().replace("-","/"));
        }
        if(gender.getText()!=null) {
            signUpRequestModelObject.setGender(gender.getText().toString());
        }
        if(location.getText()!=null) {
            signUpRequestModelObject.setLocation(location.getText().toString());
        }


    }


    /**
     * onClick handler
     */
    public void togglePersonalContents(View v) {
        if (personalDetails.isShown()) {
            Utility.slideUp(this, personalDetails);
            personalDetails.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                personalDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline, getApplicationContext().getTheme()));
            } else {
                personalDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline));
            }
        } else {
            personalDetails.setVisibility(View.VISIBLE);
            Utility.slideDown(this, personalDetails);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                personalDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline, getApplicationContext().getTheme()));
            } else {
                personalDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline));
            }
        }
    }

    public void toggleSecurityContents(View v) {
        if (securityDetails.isShown()) {
            Utility.slideUp(this, securityDetails);
            securityDetails.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                securityDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline, getApplicationContext().getTheme()));
            } else {
                securityDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline));
            }
        } else {
            securityDetails.setVisibility(View.VISIBLE);
            Utility.slideDown(this, securityDetails);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                securityDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline, getApplicationContext().getTheme()));
            } else {
                securityDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline));
            }
        }
    }

    public void toggleUtilitiesContents(View v) {
        if (utilitiesDetails.isShown()) {
            Utility.slideUp(this, utilitiesDetails);
            utilitiesDetails.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                utilitiesDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline, getApplicationContext().getTheme()));
            } else {
                utilitiesDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline));
            }
        } else {
            utilitiesDetails.setVisibility(View.VISIBLE);
            Utility.slideDown(this, utilitiesDetails);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                utilitiesDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline, getApplicationContext().getTheme()));
            } else {
                utilitiesDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline));
            }
        }

    }

    /*public void toggleInterestsContents(View v) {
        CustomTextView interestsDetailsTextView = (CustomTextView) findViewById(R.id.interestsDetailsTextView);
        //RecyclerView interestRecyclerView
        //showGenderAndLocationSelecationDialog(interestsDetailsTextView.getId(), getString(R.string.signup_interests_details), getInterestsData());
    }*/


    public void toggleInterestsContents(View v) {
        if (interestsDetails.isShown()) {
            Utility.slideUp(this, interestsDetails);
            interestsDetails.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                interestsDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline, getApplicationContext().getTheme()));
            } else {
                interestsDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.right_arrow_outline));
            }
        } else {
            interestsDetails.setVisibility(View.VISIBLE);
            Utility.slideDown(this, interestsDetails);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                interestsDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline, getApplicationContext().getTheme()));
            } else {
                interestsDetailsArrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow_outline));
            }
        }
    }

    private void setInterestDataAndView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.interestRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        interestsAdapter = new InterestsAdapter(SignUpActivity.this, this, interestsModelObjectList);
        mRecyclerView.setAdapter(interestsAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
