package com.appl_maint_mngt.views.property_appliance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.appliance_status.IApplianceStatusReadable;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.models.status_history.StatusHistory;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusObserverUpdateTypes;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class PropertyApplianceActivity extends AppCompatActivity implements Observer {

    private IPropertyApplianceReadable propertyAppliance;

    private TextView currentStatusTv;

    private StatusHistoryListAdapter statusHistoryListAdapter;
    private ListView statusHistoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_appliance);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Long propertyApplianceId = extras.getLong(IPropertyApplianceViewConstants.ID_KEY);
            propertyAppliance = RepositoryFactory.getInstance().getReadablePropertyApplianceRepository().getForId(propertyApplianceId);
        }

        setupPropertyApplianceView();

        statusHistoryListAdapter = new StatusHistoryListAdapter(this, new ArrayList<IStatusHistoryReadable>());
        statusHistoryListView = (ListView) findViewById(R.id.propertyappliance_listview_stathistory_list);
        statusHistoryListView.setAdapter(statusHistoryListAdapter);
    }

    private void setupPropertyApplianceView() {
        TextView nameTitleTv = (TextView) findViewById(R.id.propertyappliance_textview_title);
        nameTitleTv.setText(propertyAppliance.getName());

        TextView ageValueTv = (TextView) findViewById(R.id.propertyappliance_textview_age_value);
        ageValueTv.setText(String.format(Locale.ENGLISH, "%d", propertyAppliance.getAge()));

        currentStatusTv = (TextView) findViewById(R.id.propertyappliance_textview_status_label);
        currentStatusTv.setVisibility(View.INVISIBLE);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IApplianceStatusObserverUpdateTypes.CURRENT_STATUS_UPDATE)) {
                IApplianceStatusReadable applStat = RepositoryFactory.getInstance().getReadableApplianceStatusRepository().getForId(propertyAppliance.getStatusId());
                currentStatusTv.setText(applStat.getType().toString());
                currentStatusTv.setVisibility(View.VISIBLE);
            } else if(o.equals(IApplianceStatusObserverUpdateTypes.STATUS_HISTORY_UPDATE)) {
                statusHistoryListAdapter.clear();
                statusHistoryListAdapter.addAll(propertyAppliance.getStatusHistory());
                statusHistoryListAdapter.notifyDataSetChanged();
            }
        }
    }
}
