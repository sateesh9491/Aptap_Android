package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 01-12-2017.
 */

public class ServiceUtilityBookingObject implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("serviceImageURL")
    @Expose
    private String serviceImageURL;

    public final static Parcelable.Creator<ServiceUtilityBookingObject> CREATOR = new Creator<ServiceUtilityBookingObject>() {
        @SuppressWarnings({
                "unchecked"
        })
        public ServiceUtilityBookingObject createFromParcel(Parcel in) {
            return new ServiceUtilityBookingObject(in);
        }
        public ServiceUtilityBookingObject[] newArray(int size) {
            return (new ServiceUtilityBookingObject[size]);
        }
    };

    protected ServiceUtilityBookingObject(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.serviceName = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceImageURL = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ServiceUtilityBookingObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImageURL() {
        return serviceImageURL;
    }

    public void setServiceImageURL(String serviceImageURL) {
        this.serviceImageURL = serviceImageURL;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(serviceImageURL);
        dest.writeValue(serviceName);
    }

    public int describeContents() {
        return 0;
    }

}