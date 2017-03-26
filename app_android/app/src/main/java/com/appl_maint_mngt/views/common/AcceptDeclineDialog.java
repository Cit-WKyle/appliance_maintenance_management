package com.appl_maint_mngt.views.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.appl_maint_mngt.R;

/**
 * Created by Kyle on 24/03/2017.
 */

public class AcceptDeclineDialog {

    private  int titleId;

    private Activity context;

    private DialogInterface.OnClickListener acceptListener;
    private DialogInterface.OnClickListener declineListener;

    private AlertDialog dialog;

    public AcceptDeclineDialog(Activity context, int titleResId) {
        this.context = context;
        this.titleId = titleResId;
    }

    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titleId);
        alertDialog.setPositiveButton(R.string.accept_action, acceptListener);
        alertDialog.setNeutralButton(R.string.decline_action, declineListener);
        this.dialog = alertDialog.create();
    }

    public void setOnAcceptListener(DialogInterface.OnClickListener listener) {
        this.acceptListener = listener;
    }

    public void setOnDeclineListener(DialogInterface.OnClickListener listener) {
        this.declineListener = listener;
    }

    public void show() {
        dialog.show();
    }

    public void close() {
        dialog.cancel();
    }
}
