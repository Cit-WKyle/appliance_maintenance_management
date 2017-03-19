package com.appl_maint_mngt.views.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IGenericListViewDialogBuilder<T> {

    AlertDialog build(Activity context, ArrayAdapter<T> adapter, int titleResource, AdapterView.OnItemClickListener listener);
}
