package com.appl_maint_mngt.views.common;

import android.app.AlertDialog;
import android.content.Context;

import com.appl_maint_mngt.R;

import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ErrorAlertDialogBuilder implements IErrorAlertDialogBuilder {

    private static final char NEWLINE_CHAR = '\n';

    @Override
    public AlertDialog build(Context context, List<String> errors) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(R.string.common_errocc);
        StringBuilder strBuilder = new StringBuilder();
        for(String err : errors) {
            strBuilder.append(err);
            strBuilder.append(NEWLINE_CHAR);
        }
        builder.setMessage(strBuilder.toString());
        builder.setPositiveButton(R.string.common_ok, null);
        return builder.create();
    }
}
