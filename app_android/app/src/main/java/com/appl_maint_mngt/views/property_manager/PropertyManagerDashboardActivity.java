package com.appl_maint_mngt.views.property_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.controllers.property.IPropertyController;
import com.appl_maint_mngt.controllers.property_manager.IPropertyManagerController;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_manager.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.views.account.DashboardForUserTypeRetriever;
import com.appl_maint_mngt.views.account.LoginActivity;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.property.PropertyListActivity;

import java.util.Observable;
import java.util.Observer;

public class PropertyManagerDashboardActivity extends AppCompatActivity implements Observer {

    private IPropertyManagerController controller;
    private IPropertyController propertyController;
    private IPropertyManagerReadableRepository propertyManagerRepo;

    private Button toPropertyViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_manager_dashboard);
        toPropertyViewBtn = (Button) findViewById(R.id.dashb_propmng_button_properties);
        toPropertyViewBtn.setVisibility(View.INVISIBLE);

        toPropertyViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent propertyListView = new Intent(PropertyManagerDashboardActivity.this, PropertyListActivity.class);
                startActivity(propertyListView);
            }
        });

        controller = ControllerFactory.getInstance().getPropertyManagerController();
        IAccountReadableRepository repo = RepositoryFactory.getInstance().getReadableAccountRepository();
        controller.getPropertyManager(repo.get().getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyManagerDashboardActivity.this, payload.getErrors()).show();
            }
        });

        propertyManagerRepo = RepositoryFactory.getInstance().getReadablePropertyManagerRepository();
        RepositoryFactory.getInstance().observePropertyManagerRepository(this);

        propertyController = ControllerFactory.getInstance().getPropertyController();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IPropertyManagerObserverUpdateTypes.MODEL_UPDATE)) {
                propertyController.getProperties(propertyManagerRepo.get().getCurrentPropertyIds(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(PropertyManagerDashboardActivity.this, payload.getErrors()).show();
                    }
                });
                toPropertyViewBtn.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
