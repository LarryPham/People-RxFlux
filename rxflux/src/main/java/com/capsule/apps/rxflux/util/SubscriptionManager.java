package com.capsule.apps.rxflux.util;

import android.support.annotation.IntegerRes;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;

import com.capsule.apps.rxflux.action.RxAction;

import rx.Subscription;

public final class SubscriptionManager {

    private static SubscriptionManager sInstance;
    private ArrayMap<String, Pair<Integer, Subscription>> mArrayMap;

    private SubscriptionManager() {
        mArrayMap = new ArrayMap<>();
    }

    public static synchronized SubscriptionManager getInstance() {
        if (sInstance == null) sInstance = new SubscriptionManager();
        return sInstance;
    }

    public void add(RxAction action, Subscription subscription) {
        Pair<Integer, Subscription> old = mArrayMap.put(action.getType(), getPair(action, subscription));
        if (old != null && !old.second.isUnsubscribed()) old.second.unsubscribe();
    }

    public void remove(RxAction action) {
        Pair<Integer, Subscription> old = mArrayMap.remove(action.getType());
        if (old != null && !old.second.isUnsubscribed()) old.second.unsubscribe();
    }

    public boolean contains(RxAction action) {
        Pair<Integer, Subscription> old = mArrayMap.get(action.getType());
        return (old != null && old.first == action.hashCode() && !old.second.isUnsubscribed());
    }

    public synchronized void clear() {
        if (mArrayMap.isEmpty()) return;

        for (Pair<Integer, Subscription> pair : mArrayMap.values()) {
            if (!pair.second.isUnsubscribed()) pair.second.unsubscribe();
        }
    }
    private Pair<Integer, Subscription> getPair(RxAction action, Subscription subscription) {
        return new Pair<>(action.hashCode(), subscription);
    }
}
