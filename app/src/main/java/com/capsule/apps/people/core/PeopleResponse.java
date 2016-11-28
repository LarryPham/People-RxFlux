package com.capsule.apps.people.core;

import com.capsule.apps.people.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PeopleResponse {

    @SerializedName("results")
    private ArrayList<People> mPeopleList;

    public ArrayList<People> getPeopleList() {
        return mPeopleList;
    }

    public void setPeopleList(ArrayList<People> peopleList) {
        mPeopleList = peopleList;
    }
}
