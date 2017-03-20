package com.appl_maint_mngt.views.property_appliance_status_update;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;

import java.util.List;

/**
 * Created by Kyle on 20/03/2017.
 */

public class ApplianceStatusListAdapter extends ArrayAdapter<IApplianceStatusReadable> {
    public ApplianceStatusListAdapter(@NonNull Context context, List<IApplianceStatusReadable> statuses) {
        super(context, 0, statuses);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IApplianceStatusReadable applStat = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.appliance_status_list_item, parent, false);
        }

        TextView typeTv = (TextView) convertView.findViewById(R.id.appl_stat_list_item_textview_type);
        typeTv.setText(applStat.getType().toString());

        return convertView;
    }
}
