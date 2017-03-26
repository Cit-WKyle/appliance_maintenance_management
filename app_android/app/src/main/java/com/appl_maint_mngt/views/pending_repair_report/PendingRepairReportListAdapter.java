package com.appl_maint_mngt.views.pending_repair_report;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.pending_repair_report.IPendingRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 25/03/2017.
 */

public class PendingRepairReportListAdapter extends ArrayAdapter<IPendingRepairReportReadable> {
    public PendingRepairReportListAdapter(@NonNull Context context, @NonNull List<IPendingRepairReportReadable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPendingRepairReportReadable pendRepairReport = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending_repair_report_list_item, parent, false);
        }

        TextView descTv = (TextView) convertView.findViewById(R.id.pend_rep_rep_textview_desc_value);
        descTv.setText(pendRepairReport.getDescription());

        TextView statusTv = (TextView) convertView.findViewById(R.id.pend_rep_rep_textview_status_value);
        statusTv.setText(pendRepairReport.getResponseStatus().toString());

        return convertView;
    }
}
