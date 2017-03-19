package com.appl_maint_mngt.views.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appl_maint_mngt.R;

/**
 * Created by Kyle on 19/03/2017.
 */

public class GenericListViewDialogBuilder implements IGenericListViewDialogBuilder {

    @Override
    public AlertDialog build(Activity context, ArrayAdapter adapter, int titleResource, AdapterView.OnItemClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_listview, null);
        alertDialog.setView(convertView);
        alertDialog.setCancelable(false);
        alertDialog.setTitle(titleResource);
        ListView lv = (ListView) convertView.findViewById(R.id.generic_dialog_listview);
        lv.setOnItemClickListener(listener);
        lv.setAdapter(adapter);
        return alertDialog.create();
    }
}
