package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by am40619 on 12-11-2017.
 */

public class IntrestResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("resdata")
    @Expose
    private List<InterestsModelObject> resdata = null;
    public final static Parcelable.Creator<IntrestResponse> CREATOR = new Creator<IntrestResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IntrestResponse createFromParcel(Parcel in) {
            return new IntrestResponse(in);
        }

        public IntrestResponse[] newArray(int size) {
            return (new IntrestResponse[size]);
        }

    };

    protected IntrestResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.resdata, (InterestsModelObject.class.getClassLoader()));
    }

    public IntrestResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InterestsModelObject> getResdata() {
        return resdata;
    }

    public void setResdata(List<InterestsModelObject> resdata) {
        this.resdata = resdata;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(resdata);
    }

    public int describeContents() {
        return 0;
    }

}