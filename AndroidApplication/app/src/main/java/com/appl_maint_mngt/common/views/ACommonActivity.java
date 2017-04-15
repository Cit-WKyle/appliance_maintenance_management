package com.appl_maint_mngt.common.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public abstract class ACommonActivity extends AppCompatActivity implements Observer {
    private static final Logger logger = LoggerManager.getLogger(ACommonActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateModels();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopObserving();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.i("Activity onStart()");
        startObserving();
        updateModels();
        updateView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopObserving();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startObserving();
        updateModels();
        updateView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopObserving();
    }

    protected abstract void updateModels();

    protected abstract void startObserving();

    protected abstract void stopObserving();

    protected abstract void updateView();
}
