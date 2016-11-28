package com.capsule.apps.rxflux.util;

import com.capsule.apps.rxflux.action.RxAction;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class SubscriptionManagerTest {

    private static final String TYPE = "type";
    private static final String KEY1 = "key1";
    private SubscriptionManager mManager;

    @Before
    public void setUp() throws Exception {
        mManager = SubscriptionManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        mManager.clear();
        mManager = null;
    }

    @Test
    public void testManager() throws Exception {
        RxAction action = RxAction.type(TYPE).bundle(KEY1, 1).build();
        mManager.add(action, Observable.just(0).subscribe());

        Assert.assertFalse(mManager.contains(action));

        mManager.add(action, Observable.just(0).delay(2, TimeUnit.SECONDS).subscribe());
        Assert.assertTrue(mManager.contains(action));

        mManager.remove(action);
        Assert.assertFalse(mManager.contains(action));

        Subscription subscription = Observable.just(0).delay(2, TimeUnit.SECONDS).subscribe();
        mManager.add(action, subscription);
        Assert.assertTrue(mManager.contains(action));

        Subscription subscription1 = Observable.just(1).delay(2, TimeUnit.SECONDS).subscribe();
        mManager.add(action, subscription1);
        Assert.assertTrue(subscription.isUnsubscribed());
        mManager.clear();
        Assert.assertFalse(mManager.contains(action));
    }
}
