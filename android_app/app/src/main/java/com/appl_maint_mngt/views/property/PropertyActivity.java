package com.appl_maint_mngt.views.property;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.controllers.property_appliance.IPropertyApplianceController;
import com.appl_maint_mngt.models.property.IPropertyReadable;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property_appliance.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.property_appliance.IPropertyApplianceViewConstants;
import com.appl_maint_mngt.views.property_appliance.PropertyApplianceActivity;
import com.appl_maint_mngt.views.property_manager.PropertyManagerDashboardActivity;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class PropertyActivity extends AppCompatActivity implements Observer {

    private static final String COMMA_SPACE = ", ";

    private IPropertyReadable property;

    private IPropertyApplianceReadableRepository propApplRepository;

    private ListView propertyApplianceListView;
    private PropertyApplianceListAdapter propertyApplianceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Long propertyId = extras.getLong(IPropertyViewConstants.ID_KEY);
            property = RepositoryFactory.getInstance().getReadablePropertyRepository().getForId(propertyId);
        }

        propApplRepository = RepositoryFactory.getInstance().getReadablePropertyApplianceRepository();
        RepositoryFactory.getInstance().observePropertyApplianceRepository(this);

        setupAddressViews();
        setupPropertyViews();
        setupPropertyApplianceListView();

        IPropertyApplianceController propApplController = ControllerFactory.getInstance().getPropertyApplianceController();
        propApplController.getPropertyAppliancesForProperty(property.getId(), new IErrorCallback() {
            @Override
            public void callback(ErrorPayload payload) {
                new ErrorAlertDialogBuilder().build(PropertyActivity.this, payload.getErrors()).show();
            }
        });
    }

    private void setupAddressViews() {
        TextView addrLineTv = (TextView) findViewById(R.id.property_addr_textview_addrline);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(property.getAddress().getAddressLine1());
        strBuilder.append(COMMA_SPACE);
        strBuilder.append(property.getAddress().getAddressLine2());
        addrLineTv.setText(strBuilder.toString());

        TextView cityTv = (TextView) findViewById(R.id.property_addr_textview_city);
        cityTv.setText(property.getAddress().getCity());

        TextView stateTv = (TextView) findViewById(R.id.property_addr_textview_state);
        stateTv.setText(property.getAddress().getState());

        TextView countryTv = (TextView) findViewById(R.id.property_addr_textview_country);
        countryTv.setText(property.getAddress().getCountry());

        TextView postalCodeTv = (TextView) findViewById(R.id.property_addr_textview_postalcode);
        postalCodeTv.setText(property.getAddress().getPostalCode());
    }

    private void setupPropertyViews() {
        TextView ageTv = (TextView) findViewById(R.id.property_details_textview_age);
        ageTv.setText(String.format(Locale.ENGLISH, "%d", property.getAge()));

        TextView bedCountTv = (TextView) findViewById(R.id.property_details_textview_bedcount);
        bedCountTv.setText(String.format(Locale.ENGLISH, "%d", property.getBedCount()));

        TextView bathroomCountTv = (TextView) findViewById(R.id.property_details_textview_bathroomcount);
        bathroomCountTv.setText(String.format(Locale.ENGLISH, "%d", property.getBathroomCount()));
    }

    private void setupPropertyApplianceListView() {
        propertyApplianceListView = (ListView) findViewById(R.id.property_appliances_listview_list);
        propertyApplianceListAdapter = new PropertyApplianceListAdapter(this, propApplRepository.getAll());
        propertyApplianceListView.setAdapter(propertyApplianceListAdapter);
        propertyApplianceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IPropertyApplianceReadable propAppl = (IPropertyApplianceReadable) propertyApplianceListView.getItemAtPosition(i);
                Intent intent = new Intent(PropertyActivity.this, PropertyApplianceActivity.class);
                intent.putExtra(IPropertyApplianceViewConstants.ID_KEY, propAppl.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IPropertyApplianceObserverUpdateTypes.MODEL_UPDATE)) {
                propertyApplianceListAdapter.clear();
                propertyApplianceListAdapter.addAll(propApplRepository.getAll());
                propertyApplianceListAdapter.notifyDataSetChanged();
            }
        }
    }
}
