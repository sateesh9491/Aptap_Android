package com.app.aptap.model;


import android.support.v4.app.Fragment;

/**
 * Created by aditya on 9/3/2017.
 */

public class FragmentModelObject {

    private Fragment fragment;
    private String title;

    public FragmentModelObject(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
