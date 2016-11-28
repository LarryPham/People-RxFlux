package com.capsule.apps.people;

import android.app.Application;
import android.content.Context;

import com.capsule.apps.people.action.PeopleActionCreator;
import com.capsule.apps.rxflux.RxFlux;
import com.capsule.apps.rxflux.util.LogLevel;

public class PeopleApp extends Application {
    private RxFlux mRxFlux;

    private PeopleActionCreator mActionCreator;

    public static PeopleApp get(Context context) {
        return ((PeopleApp) context.getApplicationContext());
    }

    public void onCreate() {
        super.onCreate();

        RxFlux.LOG_LEVEL = BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE;
        mRxFlux = RxFlux.init(this);
        mActionCreator = new PeopleActionCreator(mRxFlux.getDispatcher(), mRxFlux.getManager());
    }

    public RxFlux getRxFlux() {
        return mRxFlux;
    }

    public PeopleActionCreator getActionCreator() {
        return mActionCreator;
    }
}
