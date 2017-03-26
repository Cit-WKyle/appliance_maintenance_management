package com.appl_maint_mngt.views.pending_maintenance_scheduling;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.views.common.DateTimeDialog;
import com.appl_maint_mngt.views.common.GenericDialog;
import com.appl_maint_mngt.views.repair_report.IRepairReportViewConstants;
import com.appl_maint_mngt.views.repair_report.RepairReportActivity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class CreatePendingMaintenanceScheduleActivity extends AppCompatActivity {

    private Long repairReportId;

    private List<SelectedSchedule> selectedSchedules;
    private ArrayAdapter<SelectedSchedule> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pending_maintenance_schedule);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            repairReportId = extras.getLong(IRepairReportViewConstants.ID_KEY);
        }

        selectedSchedules = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.generic_list_item, R.id.generic_list_textview, selectedSchedules);

        ListView selectedTimes = (ListView) findViewById(R.id.pend_maint_sched_listview_added_times);
        selectedTimes.setAdapter(adapter);
        selectedTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SelectedSchedule sched = (SelectedSchedule) parent.getItemAtPosition(position);
                final GenericDialog dialog = new GenericDialog(CreatePendingMaintenanceScheduleActivity.this, R.string.action_delete, R.string.delete_confirmation);
                dialog.onPositiveClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {
                        selectedSchedules.remove(sched);
                        adapter.remove(sched);
                        adapter.notifyDataSetChanged();
                        dialog.close();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        Button addTime = (Button) findViewById(R.id.pend_maint_sched_button_add_time);
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DateTimeDialog dateTimeDialog = new DateTimeDialog(CreatePendingMaintenanceScheduleActivity.this);
                dateTimeDialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DateTime startTime = dateTimeDialog.getSelectedDateTime();
                        dateTimeDialog.close();
                        final DateTimeDialog endDateTimeDialog = new DateTimeDialog(CreatePendingMaintenanceScheduleActivity.this);
                        endDateTimeDialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DateTime endTime = endDateTimeDialog.getSelectedDateTime();
                                SelectedSchedule selectedSchedule = new SelectedSchedule(startTime, endTime);
                                selectedSchedules.add(selectedSchedule);
                                adapter.add(selectedSchedule);
                                adapter.notifyDataSetChanged();
                                endDateTimeDialog.close();
                            }
                        });
                        endDateTimeDialog.create();
                        endDateTimeDialog.show();
                    }
                });
                dateTimeDialog.create();
                dateTimeDialog.show();
            }
        });

        Button submit = (Button) findViewById(R.id.pend_maint_sched_button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePendingMaintenanceScheduleActivity.this, RepairReportActivity.class);
                intent.putExtra(IRepairReportViewConstants.ID_KEY, repairReportId);
                startActivity(intent);
            }
        });
    }
}
