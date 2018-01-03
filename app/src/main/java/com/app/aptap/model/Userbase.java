package com.app.aptap.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by am40619 on 15-11-2017.
 */

public class Userbase implements Parcelable {

    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("societyId")
    @Expose
    private String societyId;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("facebookid")
    @Expose
    private String facebookid;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("gmailid")
    @Expose
    private String gmailid;
    @SerializedName("userhashtag")
    @Expose
    private String userhashtag;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("profilepic")
    @Expose
    private String profilepic;
    public final static Parcelable.Creator<Userbase> CREATOR = new Creator<Userbase>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Userbase createFromParcel(Parcel in) {
            return new Userbase(in);
        }

        public Userbase[] newArray(int size) {
            return (new Userbase[size]);
        }

    }
            ;

    protected Userbase(Parcel in) {
        this.uniqueId = ((String) in.readValue((String.class.getClassLoader())));
        this.societyId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastname = ((String) in.readValue((String.class.getClassLoader())));
        this.facebookid = ((String) in.readValue((String.class.getClassLoader())));
        this.dateofbirth = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.profile = ((String) in.readValue((String.class.getClassLoader())));
        this.gmailid = ((String) in.readValue((String.class.getClassLoader())));
        this.userhashtag = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceId = ((String) in.readValue((String.class.getClassLoader())));
        this.profilepic = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Userbase() {
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
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

    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGmailid() {
        return gmailid;
    }

    public void setGmailid(String gmailid) {
        this.gmailid = gmailid;
    }

    public String getUserhashtag() {
        return userhashtag;
    }

    public void setUserhashtag(String userhashtag) {
        this.userhashtag = userhashtag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(uniqueId);
        dest.writeValue(societyId);
        dest.writeValue(firstname);
        dest.writeValue(lastname);
        dest.writeValue(facebookid);
        dest.writeValue(dateofbirth);
        dest.writeValue(location);
        dest.writeValue(profile);
        dest.writeValue(gmailid);
        dest.writeValue(userhashtag);
        dest.writeValue(gender);
        dest.writeValue(mobile);
        dest.writeValue(deviceId);
        dest.writeValue(profilepic);
    }

    public int describeContents() {
        return 0;
    }

}