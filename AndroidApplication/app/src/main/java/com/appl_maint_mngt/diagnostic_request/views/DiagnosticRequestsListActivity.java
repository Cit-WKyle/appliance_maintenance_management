package com.appl_maint_mngt.diagnostic_request.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.errors.interfaces.DialogErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestsRetriever;
import com.appl_maint_mngt.diagnostic_request.views.interfaces.IDiagnosticRequestListView;
import com.appl_maint_mngt.diagnostic_request.views.utility.DiagnosticRequestListAdapterDiagnosticReport;
import com.appl_maint_mngt.diagnostic_request.views.utility.DiagnosticRequestListAdapterMaintenanceOrganisation;
import com.appl_maint_mngt.diagnostic_request.views.utility.MaintenanceEngineerDiagnosticRequestListitemClickListener;
import com.appl_maint_mngt.diagnostic_request.views.utility.PropertyManagerDiagnosticRequestListItemClickListener;
import com.appl_maint_mngt.maintenance_engineer.models.interfaces.IMaintenanceEngineerReadable;

import java.util.ArrayList;
import java.util.Observable;

public class DiagnosticRequestsListActivity extends ACommonActivity {

    private IDiagnosticRequestListView diagnosticRequestListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_requests_list);
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            diagnosticRequestListView = new DiagnosticRequestListView(this, new DiagnosticRequestListAdapterMaintenanceOrganisation(this, new ArrayList<IDiagnosticRequestReadable>()));
            diagnosticRequestListView.setOnDiagnosticRequestSelelectListener(new PropertyManagerDiagnosticRequestListItemClickListener(this));
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            diagnosticRequestListView = new DiagnosticRequestListView(this, new DiagnosticRequestListAdapterDiagnosticReport(this, new ArrayList<IDiagnosticRequestReadable>()));
            diagnosticRequestListView.setOnDiagnosticRequestSelelectListener(new MaintenanceEngineerDiagnosticRequestListitemClickListener(this));
        }
    }

    @Override
    protected void updateModels() {
        IMaintenanceEngineerReadable maintEng = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceEngineerRepository().get();
        IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().getForMaintenanceOrgId(maintEng.getCurrentOrganisationId(), new DialogErrorCallback(this));
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticRequestRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticRequestRepository(this);
    }

    @Override
    protected void updateView() {
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            diagnosticRequestListView.update(new DiagnosticRequestsRetriever().getPendingAndResponded());
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            diagnosticRequestListView.update(new DiagnosticRequestsRetriever().getPending());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
