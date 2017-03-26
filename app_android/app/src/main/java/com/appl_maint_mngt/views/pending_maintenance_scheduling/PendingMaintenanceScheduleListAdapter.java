package com.appl_maint_mngt.views.pending_maintenance_scheduling;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.pending_maintenance_scheduling.IPendingMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 24/03/2017.
 */

public class PendingMaintenanceScheduleListAdapter extends ArrayAdapter<IPendingMaintenanceScheduleReadable> {

    public PendingMaintenanceScheduleListAdapter(@NonNull Context context, @NonNull List<IPendingMaintenanceScheduleReadable> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPendingMaintenanceScheduleReadable sched = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending_maintenance_schedule_list_item, parent, false);
        }

        TextView startTimeTv = (TextView) convertView.findViewById(R.id.pend_maint_sched_list_item_textview_starttime);
        startTimeTv.setText(sched.getStartTime().toString());

        TextView endTimeTv = (TextView) convertView.findViewById(R.id.pend_maint_sched_list_item_textview_endtime);
        endTimeTv.setText(sched.getEndTime().toString());

        TextView schedStatTv = (TextView) convertView.findViewById(R.id.pend_maint_sched_list_item_textview_status);
        schedStatTv.setText(sched.getScheduleStatus().toString());

        return convertView;
    }
}
