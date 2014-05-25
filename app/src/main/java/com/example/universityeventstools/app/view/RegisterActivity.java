package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.registration.RegisterController;
import com.example.universityeventstools.app.controller.registration.providers.InstituteGroupsProvider;
import com.example.universityeventstools.app.model.ServicePerson;
import com.example.universityeventstools.app.util.EmailValidator;
import com.example.universityeventstools.app.util.PasswordValidator;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;
    private TextView message;

    private Button registerBtn;
    private Button cancelBtn;

    private RadioGroup userTypeRadioGroup;
    private RadioButton userTypeRadioButton;

    private StudentFragment studentFragment;
    private LecturerFragment lecturerFragment;

    private FragmentTransaction fragmentTransaction;
    private RegisterController registerController;
    private InstituteGroupsProvider instituteGroupsProvider;

    private Spinner groupSpinner;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        message = (TextView) findViewById(R.id.message1);

        registerController = new RegisterController();
        instituteGroupsProvider = new InstituteGroupsProvider();

        emailInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPasswordInput);

        studentFragment = new StudentFragment();
        lecturerFragment = new LecturerFragment();

        userTypeRadioGroup = (RadioGroup) findViewById(R.id.userTypeRadio);
        int selectedId = userTypeRadioGroup.getCheckedRadioButtonId();
        userTypeRadioButton = (RadioButton) findViewById(selectedId);

        groupSpinner = (Spinner) findViewById(R.id.groupInput);

        userTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.studentRadio:

                        fragmentTransaction.replace(R.id.fragmentContainer, studentFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.lecturerRadio:

                        fragmentTransaction.replace(R.id.fragmentContainer, lecturerFragment);
                        fragmentTransaction.commit();
                        break;

                }


            }
        });

        fragmentTransaction = getFragmentManager().beginTransaction();
        switch (userTypeRadioButton.getId()) {
            case R.id.studentRadio:
                fragmentTransaction.replace(R.id.fragmentContainer, studentFragment);
                break;
            case R.id.lecturerRadio:
                fragmentTransaction.replace(R.id.fragmentContainer, lecturerFragment);
                break;

        }
        fragmentTransaction.commit();

        registerBtn = (Button) findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(this);

        cancelBtn = (Button) findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                EmailValidator emailValidator = new EmailValidator();
                PasswordValidator passwordValidator = new PasswordValidator();
                if (userTypeRadioGroup.getCheckedRadioButtonId() == R.id.studentRadio) {
                    //StringBuilder validationErrors = new StringBuilder();

                    String sDepartment = "";
                    String sFirstName = "";
                    int sGroupId = instituteGroupsProvider.getGroupIdByGroupName(getIntent().getExtras().getString("group"));
                    String sLastName = "";
                    String sPersonId = emailInput.getText().toString();
                    String sPassword = passwordInput.getText().toString();
                    String sRole = userTypeRadioButton.getText().toString();
                    if (!emailValidator.validate(sPersonId)) {
                        emailInput.setError("Неправильний e-mail");
                    } else if (!passwordValidator.validate(sPassword)) {
                        passwordInput.setError("Некоректний пароль");
                    } else if (!sPassword.equals(confirmPasswordInput.getText().toString())) {
                        passwordInput.setError("Паролі мусять бути однакові");
                    } else {
                        ServicePerson servicePerson = new ServicePerson(sDepartment, sFirstName, sGroupId, sLastName, sPassword, sPersonId, sRole);
                        int responseCode = registerController.register(servicePerson);
                        if (responseCode == 201) {
                            Intent intent = new Intent(this, LoginActivity.class);
                            intent.putExtra("message", "Ви успішно зареєструвались");
                            intent.putExtra("messageColor", "green");
                            startActivity(intent);
                        } else {
                            message.setText("Користувач з таким e-mail вже існує");
                            message.setTextColor(Color.RED);
                        }
                    }
                }
                if (userTypeRadioGroup.getCheckedRadioButtonId() == R.id.lecturerRadio) {

                }
                break;
            case R.id.cancelButton:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
