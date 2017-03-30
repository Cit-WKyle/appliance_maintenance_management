package com.appl_maint_mngt.views.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.appl_maint_mngt.R;

/**
 * Created by Kyle on 22/03/2017.
 */

public class GenericDialog {

    private int titleId;
    private int bodyId;

    private Activity context;

    private DialogInterface.OnClickListener okListener;
    private AlertDialog dialog;

    public GenericDialog(Activity context, int titleId, int bodyId) {
        this.context = context;
        this.titleId = titleId;
        this.bodyId = bodyId;
    }

    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titleId);
        alertDialog.setMessage(bodyId);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);
        alertDialog.setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog = alertDialog.create();
    }

    public void onPositiveClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    public void show() {
        dialog.show();
    }

    public void close() {
        dialog.cancel();
    }
}
