package com.appl_maint_mngt.views.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.callbacks.error.ErrorPayload;
import com.appl_maint_mngt.common.callbacks.error.IErrorCallback;
import com.appl_maint_mngt.controllers.account.IAccountController;
import com.appl_maint_mngt.controllers.common.ControllerFactory;
import com.appl_maint_mngt.forms.account.LoginForm;
import com.appl_maint_mngt.models.account.IAccountReadable;
import com.appl_maint_mngt.repositories.account.IAccountObserverUpdateTypes;
import com.appl_maint_mngt.repositories.account.IAccountReadableRepository;
import com.appl_maint_mngt.repositories.common.RepositoryFactory;
import com.appl_maint_mngt.validation.account.ILoginFormValidator;
import com.appl_maint_mngt.validation.account.LoginFormValidator;
import com.appl_maint_mngt.validation.common.IValidatorResponse;
import com.appl_maint_mngt.views.common.ErrorAlertDialogBuilder;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LoginActivity extends AppCompatActivity implements Observer {

    private ILoginFormValidator loginFormValidator;
    private IAccountController accountController;
    private IAccountReadableRepository repo;

    private EditText emailField;
    private EditText passwordField;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.login_edittext_email);
        passwordField = (EditText) findViewById(R.id.login_edittext_password);
        loginBtn = (Button) findViewById(R.id.login_button_submit);

        accountController = ControllerFactory.getInstance().getAccountController();

        loginFormValidator = new LoginFormValidator();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginForm form = new LoginForm();
                form.setEmail(emailField.getEditableText().toString());
                form.setPassword(passwordField.getEditableText().toString());

                IValidatorResponse response = loginFormValidator.validate(form);
                if(response.isValid()) accountController.login(form, new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        respondWithErrors(payload.getErrors());
                    }
                });
                else {
                    respondWithErrors(response.getErrors());
                }
            }
        });
        repo = RepositoryFactory.getInstance().getReadableAccountRepository();
        RepositoryFactory.getInstance().observeAccountRepository(this);

        Button regBtn = (Button) findViewById(R.id.login_button_register);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regView = new Intent(LoginActivity.this, AccountRegisterActivity.class);
                startActivity(regView);
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data instanceof String) {
            if(data.equals(IAccountObserverUpdateTypes.AUTH_UPDATE)) {
                IAccountReadable acc = repo.get();
                accountController.getProfile(acc.getId(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        respondWithErrors(payload.getErrors());
                    }
                });
            } else if(data.equals(IAccountObserverUpdateTypes.PROFILE_UPDATE)) {
                IAccountReadable acc = repo.get();
                Intent dashboard = new Intent(this, new DashboardForUserTypeRetriever().get(acc.getUserType()));
                startActivity(dashboard);
            } else if(data.equals(IAccountObserverUpdateTypes.TOKEN_UPDATE)) {
                IAccountReadable acc = repo.get();
                accountController.getAuthDetails(acc.getToken(), new IErrorCallback() {
                    @Override
                    public void callback(ErrorPayload payload) {
                        respondWithErrors(payload.getErrors());
                    }
                });
            }
        }
    }

    private void respondWithErrors(List<String> errors) {
        passwordField.getEditableText().clear();
        new ErrorAlertDialogBuilder().build(LoginActivity.this, errors).show();
    }
}
