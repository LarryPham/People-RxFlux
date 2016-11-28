package com.capsule.apps.people.action;

import com.capsule.apps.people.PeopleApp;
import com.capsule.apps.people.core.PeopleFactory;
import com.capsule.apps.people.core.PeopleService;
import com.capsule.apps.people.store.PeopleStore;
import com.capsule.apps.rxflux.action.RxAction;
import com.capsule.apps.rxflux.action.RxActionCreator;
import com.capsule.apps.rxflux.dispatcher.Dispatcher;
import com.capsule.apps.rxflux.util.SubscriptionManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PeopleActionCreator extends RxActionCreator implements Actions {


    public PeopleActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    @Override
    public void getPeopleList() {
        final String fetch_url = "http://api.randomuser.me/?results=10&nat=en";

        final RxAction action = newRxAction(Keys.PEOPLE);
        if (hasRxAction(action)) return;

        addRxAction(action, PeopleFactory.create().fetchPeople(fetch_url)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(peoples -> postRxAction(newRxAction(FETCH_PEOPLE, Keys.PEOPLE, peoples)), throwable -> postError(action, throwable)));
    }

    @Override
    public boolean retry(RxAction action) {
        if (hasRxAction(action)) return true;
        switch (action.getType()) {
            case FETCH_PEOPLE:
                getPeopleList();
                return true;
            default:
                return false;
        }
    }
}
