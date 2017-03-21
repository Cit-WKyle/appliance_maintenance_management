package com.appl_maint_mngt.views.diagnostic_report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.diagnostic_report.IDiagnosticReportReadable;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticReportListAdapter extends ArrayAdapter<IDiagnosticReportReadable> {

    public DiagnosticReportListAdapter(@NonNull Context context, List<IDiagnosticReportReadable> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticReportReadable report = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diagnostic_report_list_item, parent, false);
        }

        TextView timestampTv = (TextView) convertView.findViewById(R.id.diagrep_textview_timestamp_value);
        timestampTv.setText(new DateTime(report.getIssuedTime()).toString());

        return convertView;
    }
}
