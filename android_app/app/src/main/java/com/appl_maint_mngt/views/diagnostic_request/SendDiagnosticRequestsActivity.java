package com.appl_maint_mngt.views.diagnostic_request;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.maintenance_organisation.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.maintenance_organisation.IMaintenanceOrganisationObserverUpdateTypes;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.maintenance_organisation.MaintenanceOrganisationListAdapter;
import com.appl_maint_mngt.views.property_appliance.IPropertyApplianceViewConstants;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SendDiagnosticRequestsActivity extends AppCompatActivity implements Observer {

    private Long diagnosticReportId;

    private LongSparseArray<IMaintenanceOrganisationReadable> selectedOrganisations;

    private ListView maintOrgLv;
    private MaintenanceOrganisationListAdapter maintOrgListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_diagnostic_requests);

        selectedOrganisations = new LongSparseArray<>();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            diagnosticReportId = extras.getLong(IDiagnosticReportViewConstants.ID_KEY);
        }

        maintOrgListAdapter = new MaintenanceOrganisationListAdapter(this, new ArrayList<IMaintenanceOrganisationReadable>());
        maintOrgLv.setAdapter(maintOrgListAdapter);

        Button sendRequestsBtn = (Button) findViewById(R.id.senddiagreq_button_send);
        sendRequestsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        RepositoryFactory.getInstance().observerMaintenanceOrganisationRepository(this);

        maintOrgLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IMaintenanceOrganisationReadable maintOrg = (IMaintenanceOrganisationReadable) maintOrgLv.getItemAtPosition(i);
                if(selectedOrganisations.get(maintOrg.getId(), null) == null) {
                    selectedOrganisations.put(maintOrg.getId(), maintOrg);
                    view.setBackgroundColor(Color.CYAN);
                } else {
                    selectedOrganisations.remove(maintOrg.getId());
                    view.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof  String) {
            if(o.equals(IMaintenanceOrganisationObserverUpdateTypes.MODEL_UPDATE)) {
                maintOrgListAdapter.clear();
                maintOrgListAdapter.addAll(RepositoryFactory.getInstance().getReadableMaintenanceOrganisationRepository().getAll());
                maintOrgListAdapter.notifyDataSetChanged();
            }
        }
    }
}
