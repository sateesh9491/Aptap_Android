package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 15-11-2017.
 */

public class OTPResponse implements Parcelable {

    @SerializedName("status")
    private String status;

    public final static Parcelable.Creator<OTPResponse> CREATOR = new Creator<OTPResponse>() {
        @SuppressWarnings({
                "unchecked"
        })
        public OTPResponse createFromParcel(Parcel in) {
            return new OTPResponse(in);
        }

        public OTPResponse[] newArray(int size) {
            return (new OTPResponse[size]);
        }
    };

    protected OTPResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OTPResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+"]";
    }

}