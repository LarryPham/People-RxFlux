package com.capsule.apps.rxflux.store;

import com.capsule.apps.rxflux.dispatcher.Dispatcher;
import com.capsule.apps.rxflux.dispatcher.RxActionDispatch;

public abstract class RxStore implements RxActionDispatch {

    private final Dispatcher mDispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void register() {
        mDispatcher.subscribeRxStore(this);
    }

    public void unregister() {
        mDispatcher.unsubscribeRxStore(this);
    }

    protected void postChange(RxStoreChange change) {
        mDispatcher.postRxStoreChange(change);
    }
}
