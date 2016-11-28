package com.capsule.apps.rxflux.dispatcher;

import com.capsule.apps.rxflux.action.RxAction;

/**
 * The interface must be implemented by the store
 */
public interface RxActionDispatch {
    void onRxAction(RxAction action);
}
