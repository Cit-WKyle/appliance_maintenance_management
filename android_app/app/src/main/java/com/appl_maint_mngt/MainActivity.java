package com.appl_maint_mngt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appl_maint_mngt.views.account.LoginActivity;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        toLoginView();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    private void toLoginView() {
        Intent loginView = new Intent(this, LoginActivity.class);
        startActivity(loginView);
    }
}
