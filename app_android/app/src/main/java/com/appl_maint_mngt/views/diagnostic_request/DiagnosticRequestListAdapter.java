package com.appl_maint_mngt.views.diagnostic_request;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.diagnostic_request.IDiagnosticRequestReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationReadableRepository;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticRequestListAdapter extends ArrayAdapter<IDiagnosticRequestReadable> {

    private IMaintenanceOrganisationReadableRepository maintOrgRepo;

    public DiagnosticRequestListAdapter(@NonNull Context context, List<IDiagnosticRequestReadable> items) {
        super(context, 0, items);
        maintOrgRepo = RepositoryFactory.getInstance().getReadableMaintenanceOrganisationRepository();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticRequestReadable diagReq = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diagnostic_request_list_item, parent, false);
        }

        TextView orgNameTv = (TextView) convertView.findViewById(R.id.diagreq_textview_org_value);
        orgNameTv.setText(maintOrgRepo.getForId(diagReq.getMaintenanceOrganisationId()).getName());
        TextView respTv = (TextView) convertView.findViewById(R.id.diagreq_textview_response_value);
        respTv.setText(diagReq.getResponseStatus().toString());

        return convertView;
    }
}
