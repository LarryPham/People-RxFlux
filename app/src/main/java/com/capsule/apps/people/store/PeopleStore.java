package com.capsule.apps.people.store;

import com.capsule.apps.people.action.Actions;
import com.capsule.apps.people.action.Keys;
import com.capsule.apps.people.core.PeopleResponse;
import com.capsule.apps.people.model.People;
import com.capsule.apps.rxflux.action.RxAction;
import com.capsule.apps.rxflux.dispatcher.Dispatcher;
import com.capsule.apps.rxflux.store.RxStore;
import com.capsule.apps.rxflux.store.RxStoreChange;

import java.util.ArrayList;

public class PeopleStore extends RxStore implements PeopleStoreInterface {

    public static final String ID = "PeopleList";
    private static PeopleStore sInstance;
    private ArrayList<People> mPeoples;

    public PeopleStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static synchronized PeopleStore get(Dispatcher dispatcher) {
        if (sInstance == null) {
            sInstance = new PeopleStore(dispatcher);
        }
        return sInstance;
    }

    @Override
    public ArrayList<People> getPeopleList() {
        return mPeoples == null ? new ArrayList<>() : mPeoples;
    }

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.FETCH_PEOPLE:
                this.mPeoples = ((PeopleResponse)action.get(Keys.PEOPLE)).getPeopleList();
                break;
            default:
                return;
        }
        postChange(new RxStoreChange(ID, action));
    }
}
