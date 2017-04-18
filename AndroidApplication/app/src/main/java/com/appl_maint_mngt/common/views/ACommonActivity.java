package com.appl_maint_mngt.common.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appl_maint_mngt.common.utility.CommonActivityIntervalUpdater;
import com.appl_maint_mngt.common.utility.interfaces.ICommonActivityIntervalUpdater;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public abstract class ACommonActivity extends AppCompatActivity implements Observer {
    private static final Logger logger = LoggerManager.getLogger(ACommonActivity.class);

    private ICommonActivityIntervalUpdater updater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updater = new CommonActivityIntervalUpdater(this);
        startObserving();
        updater.startUpdating();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopObserving();
        updater.stopUpdating();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.i("Activity onStart()");
        startObserving();
        updater.startUpdating();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopObserving();
        updater.stopUpdating();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startObserving();
        updater.startUpdating();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopObserving();
        updater.stopUpdating();
    }

    public abstract void updateModels();

    protected abstract void startObserving();

    protected abstract void stopObserving();

    protected abstract void updateView();
}
