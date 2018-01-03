package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 19-11-2017.
 */

public class UserData  implements Parcelable {

    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("typeoflogin")
    @Expose
    private String typeoflogin;
    @SerializedName("accountmailid")
    @Expose
    private String accountmailid;
    @SerializedName("typeofupdate")
    @Expose
    private String typeofupdate;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    public final static Parcelable.Creator<UserData> CREATOR = new Creator<UserData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return (new UserData[size]);
        }

    }
            ;

    protected UserData(Parcel in) {
        this.uniqueId = ((String) in.readValue((String.class.getClassLoader())));
        this.typeoflogin = ((String) in.readValue((String.class.getClassLoader())));
        this.accountmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.typeofupdate = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserData() {
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTypeoflogin() {
        return typeoflogin;
    }

    public void setTypeoflogin(String typeoflogin) {
        this.typeoflogin = typeoflogin;
    }

    public String getAccountmailid() {
        return accountmailid;
    }

    public void setAccountmailid(String accountmailid) {
        this.accountmailid = accountmailid;
    }

    public String getTypeofupdate() {
        return typeofupdate;
    }

    public void setTypeofupdate(String typeofupdate) {
        this.typeofupdate = typeofupdate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uniqueId);
        dest.writeValue(typeoflogin);
        dest.writeValue(accountmailid);
        dest.writeValue(typeofupdate);
        dest.writeValue(mobile);
    }

    public int describeContents() {
        return 0;
    }

}