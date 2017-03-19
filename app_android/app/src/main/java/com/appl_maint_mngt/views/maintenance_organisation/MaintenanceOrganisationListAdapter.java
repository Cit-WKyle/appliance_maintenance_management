package com.appl_maint_mngt.views.maintenance_organisation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public class MaintenanceOrganisationListAdapter extends ArrayAdapter<IMaintenanceOrganisationReadable> {
    public MaintenanceOrganisationListAdapter(@NonNull Context context, List<IMaintenanceOrganisationReadable> items) {
        super(context, 0, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMaintenanceOrganisationReadable org = getItem(position);

        if(convertView == null) {

        }

        return convertView;
    }
}
