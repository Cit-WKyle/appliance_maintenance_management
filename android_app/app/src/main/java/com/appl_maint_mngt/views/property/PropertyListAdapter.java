package com.appl_maint_mngt.views.property;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.property.IPropertyReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyListAdapter extends ArrayAdapter<IPropertyReadable> {

    private static final char COMMA = ',';
    private static final char BLANK_SPACE = ' ';

    public PropertyListAdapter(@NonNull Context context, List<IPropertyReadable> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPropertyReadable propertyReadable = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.property_list_item, parent, false);
        }
        TextView addrLinesTv = (TextView) convertView.findViewById(R.id.propertylist_item_textview_addrlines);

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(propertyReadable.getAddress().getAddressLine1());
        strBuilder.append(COMMA);
        strBuilder.append(BLANK_SPACE);
        strBuilder.append(propertyReadable.getAddress().getAddressLine2());

        addrLinesTv.setText(strBuilder.toString());

        return convertView;
    }
}
