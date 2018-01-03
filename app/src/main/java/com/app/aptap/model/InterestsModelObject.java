package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 29-10-2017.
 */

public class InterestsModelObject implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("intrestType")
    @Expose
    private String intrestType;
    @SerializedName("intrested")
    @Expose
    private Boolean intrested = false;
    public final static Parcelable.Creator<InterestsModelObject> CREATOR = new Creator<InterestsModelObject>() {
        @SuppressWarnings({
                "unchecked"
        })
        public InterestsModelObject createFromParcel(Parcel in) {
            return new InterestsModelObject(in);
        }

        public InterestsModelObject[] newArray(int size) {
            return (new InterestsModelObject[size]);
        }

    }
            ;

    protected InterestsModelObject(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.intrestType = ((String) in.readValue((String.class.getClassLoader())));
        this.intrested = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public InterestsModelObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntrestType() {
        return intrestType;
    }

    public void setIntrestType(String intrestType) {
        this.intrestType = intrestType;
    }

    public Boolean getIntrested() {
        return intrested;
    }

    public void setIntrested(Boolean intrested) {
        this.intrested = intrested;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(intrestType);
        dest.writeValue(intrested);
    }

    public int describeContents() {
        return 0;
    }

}