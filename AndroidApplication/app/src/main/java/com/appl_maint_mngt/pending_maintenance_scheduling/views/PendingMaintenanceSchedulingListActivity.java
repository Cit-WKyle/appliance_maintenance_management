package com.appl_maint_mngt.pending_maintenance_scheduling.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.PendingMaintenanceSchedulingListFilter;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.interfaces.IPendingMaintenanceSchedulingListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PendingMaintenanceSchedulingListActivity extends ACommonActivity {

    private IPendingMaintenanceSchedulingListView pendingMaintenanceSchedulingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_maintenance_scheduling_list);
    }

    @Override
    protected void updateModels() {

    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void updateView() {
        List<IPendingMaintenanceScheduleReadable> pendingMaintenanceSchedules = new ArrayList<>();
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
        } else if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {

        }
        pendingMaintenanceSchedulingListView.update(pendingMaintenanceSchedules);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
