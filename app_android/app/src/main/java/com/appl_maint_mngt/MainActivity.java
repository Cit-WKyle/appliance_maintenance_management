package com.appl_maint_mngt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.appl_maint_mngt.views.account.LoginActivity;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends Activity {

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

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
