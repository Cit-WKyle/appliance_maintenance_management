package com.appl_maint_mngt.views.property_tenant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.controllers.property_tenant.IPropertyTenantController;
import com.appl_maint_mngt.models.property.IPropertyReadable;
import com.appl_maint_mngt.models.property_tenant.IPropertyTenantReadable;
import com.appl_maint_mngt.models.property_tenant.ResidenceStatus;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property.IPropertyObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_tenant.IPropertyTenantReadableRepository;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.nfc.NFCActivity;
import com.appl_maint_mngt.views.property.IPropertyViewConstants;
import com.appl_maint_mngt.views.property.PropertyActivity;

import java.util.Observable;
import java.util.Observer;

public class PropertyTenantDashboardActivity extends NFCActivity implements Observer {

    private IPropertyTenantController controller;
    private IPropertyTenantReadableRepository repository;

    private Button toPropertyViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_tenant_dashboard);

        toPropertyViewBtn = (Button) findViewById(R.id.dashb_proptnt_button_property);
        toPropertyViewBtn.setVisibility(View.INVISIBLE);

        toPropertyViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPropertyTenantReadable tnt = repository.get();
                if(tnt.getResidenceStatus().equals(ResidenceStatus.OCCUPANT)) {
                    IPropertyReadable prop = RepositoryFactory.getInstance().getReadablePropertyRepository().getForId(tnt.getCurrentPropertyId());
                    Intent intent = new Intent(PropertyTenantDashboardActivity.this, PropertyActivity.class);
                    intent.putExtra(IPropertyViewConstants.ID_KEY, prop.getId());
                    startActivity(intent);
                } else if(tnt.getResidenceStatus().equals(ResidenceStatus.PENDING)) {
                    Toast.makeText(PropertyTenantDashboardActivity.this, ResidenceStatus.PENDING.toString(), Toast.LENGTH_LONG).show();
                } else if(tnt.getResidenceStatus().equals((ResidenceStatus.NO_RESIDENCE))) {
                    Intent intent  = new Intent(PropertyTenantDashboardActivity.this, TenantPropertySearchActivity.class);
                    startActivity(intent);
                }
            }
        });

        controller = ControllerFactory.getInstance().getPropertyTenantController();
        repository = RepositoryFactory.getInstance().getReadablePropertyTenantRepository();
        RepositoryFactory.getInstance().observerPropertyTenantRepository(this);

        IAccountReadableRepository accRepo = RepositoryFactory.getInstance().getReadableAccountRepository();
        controller.getPropertyTenant(accRepo.get().getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyTenantDashboardActivity.this, payload.getErrors()).show();
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IPropertyTenantObserverUpdateTypes.MODEL_UPDATE)) {
                IPropertyTenantReadable tnt = repository.get();
                if(tnt.getResidenceStatus().equals(ResidenceStatus.OCCUPANT)) {
                    RepositoryFactory.getInstance().observePropertyRepository(this);
                    ControllerFactory.getInstance().getPropertyController().getProperty(tnt.getCurrentPropertyId(), new IErrorCallback() {
                        @Override
                        public void callback(ErrorPayload payload) {
                            new ErrorAlertDialogBuilder().build(PropertyTenantDashboardActivity.this, payload.getErrors()).show();
                        }
                    });
                } else {
                    toPropertyViewBtn.setVisibility(View.VISIBLE);
                }
            } else if(o.equals(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE)) {
                toPropertyViewBtn.setVisibility(View.VISIBLE);
                ControllerFactory.getInstance().getPropertyApplianceController().getPropertyAppliancesForProperty(repository.get().getCurrentPropertyId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(PropertyTenantDashboardActivity.this, payload.getErrors()).show();
                    }
                });
            }
        }
    }
}
