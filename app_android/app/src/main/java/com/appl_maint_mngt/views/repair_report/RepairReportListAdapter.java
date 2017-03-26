package com.appl_maint_mngt.views.repair_report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.repair_report.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 24/03/2017.
 */

public class RepairReportListAdapter extends ArrayAdapter<IRepairReportReadable> {
    public RepairReportListAdapter(@NonNull Context context, @NonNull List<IRepairReportReadable> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IRepairReportReadable reportReadable = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repair_report_list_item, parent, false);
        }

        TextView descTv = (TextView) convertView.findViewById(R.id.repair_report_list_item_textview_desc);
        descTv.setText(reportReadable.getDescription());

        return convertView;
    }
}
