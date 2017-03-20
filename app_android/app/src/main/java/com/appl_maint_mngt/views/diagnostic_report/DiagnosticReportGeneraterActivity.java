package com.appl_maint_mngt.views.diagnostic_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.forms.diagnostic_report.DiagnosticReportForm;
import com.appl_maint_mngt.models.diagnostic_report.IDiagnosticReportReadable;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.repositories.diagnostic_report.IDiagnosticReportObserverUpdateTypes;
import com.appl_maint_mngt.validation.common.IValidatorResponse;
import com.appl_maint_mngt.validation.diagnostic_report.DiagnosticReportFormValidator;
import com.appl_maint_mngt.validation.diagnostic_report.IDiagnosticReportFormValidator;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;
import com.appl_maint_mngt.views.diagnostic_request.SendDiagnosticRequestsActivity;
import com.appl_maint_mngt.views.property_appliance.IPropertyApplianceViewConstants;

import java.sql.Timestamp;
import java.util.Observable;
import java.util.Observer;

public class DiagnosticReportGeneraterActivity extends AppCompatActivity implements Observer {

    private Long propertyApplianceId;
    private EditText descEditText;

    private IDiagnosticReportFormValidator formValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_report_generater);

        descEditText = (EditText) findViewById(R.id.diagrep_gen_edittext_desc_value);

        formValidator = new DiagnosticReportFormValidator();

        Bundle extras = getIntent().getExtras();
        String pApplName = "";
        if(extras != null) {
            propertyApplianceId = extras.getLong(IPropertyApplianceViewConstants.ID_KEY);
            pApplName = RepositoryFactory.getInstance().getReadablePropertyApplianceRepository().getForId(propertyApplianceId).getName();
        }

        TextView pApplNameTv = (TextView) findViewById(R.id.diagrep_gen_textview_pappl_name);
        pApplNameTv.setText(pApplName);

        Button diagRepGenBtn = (Button) findViewById(R.id.diagrep_gen_button_generate);
        diagRepGenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiagnosticReportForm form = new DiagnosticReportForm();
                form.setDescription(descEditText.getEditableText().toString());
                form.setIssuedTime(new Timestamp(System.currentTimeMillis()));
                form.setPropApplId(propertyApplianceId);

                IValidatorResponse response =formValidator.validate(form);
                if(response.isValid()) ControllerFactory.getInstance().getDiagnosticReportController().generateDiagnosticReport(form, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        new ErrorAlertDialogBuilder().build(DiagnosticReportGeneraterActivity.this, payload.getErrors()).show();
                    }
                });
                else {
                    new ErrorAlertDialogBuilder().build(DiagnosticReportGeneraterActivity.this, response.getErrors()).show();
                }
            }
        });

        RepositoryFactory.getInstance().observeDiagnosticReportRepository(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof String) {
            if(o.equals(IDiagnosticReportObserverUpdateTypes.MODEL_UPDATE)) {
                Intent reqIntent = new Intent(this, SendDiagnosticRequestsActivity.class);
                IDiagnosticReportReadable diagRep = RepositoryFactory.getInstance().getReadableDiagnosticReportRepository().getMostRecent();
                reqIntent.putExtra(IDiagnosticReportViewConstants.ID_KEY, diagRep.getId());
                startActivity(reqIntent);
            }
        }
    }
}
