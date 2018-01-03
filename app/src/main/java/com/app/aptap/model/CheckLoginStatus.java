package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ke41342 on 05-10-2017.
 */

public class CheckLoginStatus implements Parcelable {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gmailid")
    @Expose
    private String gmailid;
    @SerializedName("typeoflogin")
    @Expose
    private String typeoflogin;
    @SerializedName("facebookid")
    @Expose
    private String facebookid;
    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("otp")
    @Expose
    private String otp;


    public final static Parcelable.Creator<CheckLoginStatus> CREATOR = new Creator<CheckLoginStatus>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CheckLoginStatus createFromParcel(Parcel in) {
            return new CheckLoginStatus(in);
        }

        public CheckLoginStatus[] newArray(int size) {
            return (new CheckLoginStatus[size]);
        }

    };

    protected CheckLoginStatus(Parcel in) {
        this.firstname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastname = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.gmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.typeoflogin = ((String) in.readValue((String.class.getClassLoader())));
        this.facebookid = ((String) in.readValue((String.class.getClassLoader())));
        this.uniqueId = ((String) in.readValue((String.class.getClassLoader())));
        this.otp = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CheckLoginStatus() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGmailid() {
        return gmailid;
    }

    public void setGmailid(String gmailid) {
        this.gmailid = gmailid;
    }

    public String getTypeoflogin() {
        return typeoflogin;
    }

    public void setTypeoflogin(String typeoflogin) {
        this.typeoflogin = typeoflogin;
    }

    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(firstname);
        dest.writeValue(lastname);
        dest.writeValue(mobile);
        dest.writeValue(gmailid);
        dest.writeValue(typeoflogin);
        dest.writeValue(facebookid);
        dest.writeValue(uniqueId);
        dest.writeValue(otp);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [typeoflogin = "+typeoflogin+", lastname = "+lastname+", gmailid = "+gmailid+", firstname = "+firstname+", mobile = "+mobile+", facebookid="+facebookid+", uniqueId = "+uniqueId+", otp = "+otp+"]";
    }

}
