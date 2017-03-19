package com.appl_maint_mngt.views.property;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.property.IPropertyReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.property.IPropertyObserverUpdateTypes;
import com.appl_maint_mngt.repositories.property.IPropertyReadableRepository;

import java.util.Observable;
import java.util.Observer;

public class PropertyListActivity extends AppCompatActivity implements Observer {

    private ListView propertyListView;

    private IPropertyReadableRepository propertyRepo;
    private PropertyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        propertyRepo = RepositoryFactory.getInstance().getReadablePropertyRepository();
        RepositoryFactory.getInstance().observePropertyRepository(this);

        propertyListView = (ListView) findViewById(R.id.propertylist_listview_properties);
        listAdapter = new PropertyListAdapter(this, propertyRepo.getAll());
        propertyListView.setAdapter(listAdapter);
        propertyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IPropertyReadable prop = (IPropertyReadable) propertyListView.getItemAtPosition(i);
                Intent intent = new Intent(PropertyListActivity.this, PropertyActivity.class);
                intent.putExtra(IPropertyViewConstants.ID_KEY, prop.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE)) {
                listAdapter.clear();
                listAdapter.addAll(propertyRepo.getAll());
                listAdapter.notifyDataSetChanged();
            }
        }
    }
}
