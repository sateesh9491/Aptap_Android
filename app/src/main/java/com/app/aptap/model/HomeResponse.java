package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 20-11-2017.
 */

public class HomeResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userbase")
    @Expose
    private Userbase userbase;
    public final static Parcelable.Creator<HomeResponse> CREATOR = new Creator<HomeResponse>() {
        @SuppressWarnings({
                "unchecked"
        })
        public HomeResponse createFromParcel(Parcel in) {
            return new HomeResponse(in);
        }

        public HomeResponse[] newArray(int size) {
            return (new HomeResponse[size]);
        }
    }
            ;

    protected HomeResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.userbase = ((Userbase) in.readValue((Userbase.class.getClassLoader())));
    }

    public HomeResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Userbase getUserbase() {
        return userbase;
    }

    public void setUserbase(Userbase userbase) {
        this.userbase = userbase;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(userbase);
    }

    public int describeContents() {
        return 0;
    }

}