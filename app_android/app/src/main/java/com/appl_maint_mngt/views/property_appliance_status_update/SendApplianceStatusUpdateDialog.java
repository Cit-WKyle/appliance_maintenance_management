package com.appl_maint_mngt.views.property_appliance_status_update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 20/03/2017.
 */

public class SendApplianceStatusUpdateDialog {

    private Activity context;
    private List<IApplianceStatusReadable> statuses;
    private IApplianceStatusReadable currentStatus;

    private ApplianceStatusListAdapter adapter;
    private DialogInterface.OnClickListener okListener;
    private IApplianceStatusReadable selectedStatus;

    private AlertDialog dialog;

    public SendApplianceStatusUpdateDialog(Activity context, List<IApplianceStatusReadable> statuses, IApplianceStatusReadable currentStatus) {
        this.context = context;
        this.statuses = statuses;
        this.currentStatus = currentStatus;
    }

    public void setOnOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    public IApplianceStatusReadable getSelected() {
        return selectedStatus;
    }

    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.send_appliance_status_update_dialog, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.send_status_update_dialog_title);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);

        TextView curVal = (TextView) convertView.findViewById(R.id.appliance_status_update_dialog_textview_current_value);
        curVal.setText(currentStatus.getType().toString());

        Spinner spinner = (Spinner) convertView.findViewById(R.id.appliance_status_update_dialog_spinner_new);
        ApplianceStatusListAdapter adapter = new ApplianceStatusListAdapter(convertView.getContext(), R.layout.appliance_status_list_item, R.id.appl_stat_list_item_textview_type, statuses);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStatus = (IApplianceStatusReadable) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog = alertDialog.create();
    }

    public void show() {
        dialog.show();
    }
    public void close() {
        dialog.cancel();
    }

}
