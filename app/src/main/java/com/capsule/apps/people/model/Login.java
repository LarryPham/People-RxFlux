package com.capsule.apps.people.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Login implements Serializable {

    @SerializedName("username")
    public String mUserName;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public Login() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        return mUserName != null ? mUserName.equals(login.mUserName) : login.mUserName == null;

    }

    @Override
    public int hashCode() {
        return mUserName != null ? mUserName.hashCode() : 0;
    }
}
