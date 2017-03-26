package com.appl_maint_mngt.views.pending_repair_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.forms.pending_repair_report.PendingRepairReportForm;
import com.appl_maint_mngt.models.diagnostic_request.DiagnosticRequest;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.validation.common.IValidatorResponse;
import com.appl_maint_mngt.validation.pending_repair_report.PendingRepairReportValidator;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_report.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.views.maintenance_engineer.MaintenanceEngineerDashboardActivity;
import com.appl_maint_mngt.views.repair_report.IRepairReportViewConstants;
import com.appl_maint_mngt.views.repair_report.RepairReportActivity;
import com.appl_maint_mngt.web.constants.maintenance_organisation.IMaintenanceOrganisationViewConstants;
import com.appl_maint_mngt.web.models.pending_repair_report.PendingRepairReportPayload;

import java.math.BigDecimal;

public class CreatePendingRepairReportActivity extends AppCompatActivity {

    private Long diagnosticReportId;

    private EditText durationField;
    private EditText costField;
    private EditText descField;

    private CheckBox onsiteCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pending_repair_report);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            diagnosticReportId = extras.getLong(IDiagnosticReportViewConstants.ID_KEY);
        }

        durationField = (EditText) findViewById(R.id.create_pend_repair_rep_edittext_duration);
        costField = (EditText) findViewById(R.id.create_pend_repair_rep_edittext_cost);
        descField = (EditText) findViewById(R.id.create_pend_repair_rep_edittext_desc);
        onsiteCheckBox = (CheckBox) findViewById(R.id.create_pend_repair_rep_checkbox_onsite);

        Button generate = (Button) findViewById(R.id.create_pend_repair_rep_button_generate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingRepairReportForm form = new PendingRepairReportForm();
                form.setCost(new BigDecimal(costField.getEditableText().toString()));
                form.setDescription(descField.getEditableText().toString());
                form.setDuration(Integer.valueOf(durationField.getEditableText().toString()));
                form.setOnSite(onsiteCheckBox.isChecked());

                IValidatorResponse response = new PendingRepairReportValidator().validate(form);
                if(response.isValid()) {
                    PendingRepairReportPayload payload = new PendingRepairReportPayload();
                    payload.setCost(form.getCost());
                    payload.setOnSite(form.isOnSite());
                    payload.setDescription(form.getDescription());
                    payload.setEstimatedDurationHours(form.getDuration());
                    payload.setEngineerId(RepositoryFactory.getInstance().getReadableAccountRepository().get().getId());
                    payload.setDiagnosticReportId(diagnosticReportId);
                    payload.setOrganisationId(RepositoryFactory.getInstance().getReadableMaintenanceEngineerRepository().get().getCurrentOrganisationId());
                    ControllerFactory.getInstance().getPendingRepairReportController().createPendingRepairReport(payload, new IErrorCallback() {
                        @Override
                        public void callback(ErrorPayload payload) {
                            new ErrorAlertDialogBuilder().build(CreatePendingRepairReportActivity.this, payload.getErrors()).show();
                        }
                    });
                    Intent intent = new Intent(CreatePendingRepairReportActivity.this, MaintenanceEngineerDashboardActivity.class);
                    startActivity(intent);
                } else {
                    new ErrorAlertDialogBuilder().build(CreatePendingRepairReportActivity.this, response.getErrors()).show();
                }
            }
        });

    }
}
