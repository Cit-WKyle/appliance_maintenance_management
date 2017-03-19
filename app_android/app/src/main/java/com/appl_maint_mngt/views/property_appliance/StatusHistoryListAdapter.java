package com.appl_maint_mngt.views.property_appliance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.repositories.appliance_status.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public class StatusHistoryListAdapter extends ArrayAdapter<IStatusHistoryReadable> {

    private IApplianceStatusReadableRepository repository;

    public StatusHistoryListAdapter(@NonNull Context context, List<IStatusHistoryReadable> items) {
        super(context, 0, items);
        repository = RepositoryFactory.getInstance().getReadableApplianceStatusRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IStatusHistoryReadable statusHistoryReadable = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.status_history_list_item, parent, false);
        }

        TextView nameTv = (TextView) convertView.findViewById(R.id.statushistory_textview_name);
        nameTv.setText(repository.getForId(statusHistoryReadable.getStatusId()).getType().toString());

        TextView timestampTv = (TextView) convertView.findViewById(R.id.statushistory_textview_loggedtime);
        timestampTv.setText(new DateTime(statusHistoryReadable.getLoggedTime()).toString());

        return convertView;
    }
}
