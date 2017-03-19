package com.appl_maint_mngt.views.property;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.property_appliance.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyApplianceListAdapter extends ArrayAdapter<IPropertyApplianceReadable> {

    public PropertyApplianceListAdapter(@NonNull Context context, List<IPropertyApplianceReadable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPropertyApplianceReadable propAppl = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.property_appliance_list_item, parent, false);
        }

        TextView nameTv = (TextView) convertView.findViewById(R.id.propertyappliancelist_item_textview_name);
        nameTv.setText(propAppl.getName());

        return convertView;
    }
}
