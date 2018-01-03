package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ke41342 on 08-10-2017.
 */

public class ResponseRegisterAPI implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userbase")
    @Expose
    private Userbase userbase;
    @SerializedName("userInterest")
    @Expose
    private List<InterestsModelObject> userInterest = new ArrayList<>();
    @SerializedName("userData")
    @Expose
    private UserData userData;

    public final static Parcelable.Creator<ResponseRegisterAPI> CREATOR = new Creator<ResponseRegisterAPI>() {
        @SuppressWarnings({
                "unchecked"
        })
        public ResponseRegisterAPI createFromParcel(Parcel in) {
            return new ResponseRegisterAPI(in);
        }

        public ResponseRegisterAPI[] newArray(int size) {
            return (new ResponseRegisterAPI[size]);
        }

    };

    protected ResponseRegisterAPI(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.userbase = ((Userbase) in.readValue((Userbase.class.getClassLoader())));
        in.readList(this.userInterest, (InterestsModelObject.class.getClassLoader()));
        this.userData = ((UserData) in.readValue((UserData.class.getClassLoader())));
    }

    public ResponseRegisterAPI() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Userbase getUserbase() {
        return userbase;
    }

    public void setUserbase(Userbase userbase) {
        this.userbase = userbase;
    }

    public List<InterestsModelObject> getUserInterest() {
        return userInterest;
    }

    public void setUserInterest(List<InterestsModelObject> userInterest) {
        this.userInterest = userInterest;
    }
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(userbase);
        dest.writeList(userInterest);
        dest.writeValue(userData);
    }

    public int describeContents() {
        return 0;
    }
}