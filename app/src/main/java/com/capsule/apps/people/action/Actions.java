package com.capsule.apps.people.action;

import com.capsule.apps.rxflux.action.RxAction;

public interface Actions {

    String FETCH_PEOPLE = "fetch_people";

    void getPeopleList();

    boolean retry(RxAction action);
}
