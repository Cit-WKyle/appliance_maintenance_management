package com.appl_maint_mngt.views.diagnostic_request;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportReadableRepository;

import java.util.List;

/**
 * Created by Kyle on 25/03/2017.
 */

public class DiagnosticRequestsReceivedListAdapter extends ArrayAdapter<IDiagnosticRequestReadable> {

    private IDiagnosticReportReadableRepository diagnosticReportRepository;

    public DiagnosticRequestsReceivedListAdapter(@NonNull Context context, @NonNull List<IDiagnosticRequestReadable> objects) {
        super(context, 0, objects);
        diagnosticReportRepository = RepositoryFactory.getInstance().getReadableDiagnosticReportRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticRequestReadable diagReq = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diagnostic_request_list_item, parent, false);
        }

        TextView orgNameTv = (TextView) convertView.findViewById(R.id.diagreq_textview_name_value);
        diagnosticReportRepository.get(diagReq.getId());
        orgNameTv.setText(diagnosticReportRepository.get(diagReq.getDiagnosticReportId()).getDescription());
        TextView respTv = (TextView) convertView.findViewById(R.id.diagreq_textview_response_value);
        respTv.setText(diagReq.getResponseStatus().toString());

        return convertView;
    }
}
