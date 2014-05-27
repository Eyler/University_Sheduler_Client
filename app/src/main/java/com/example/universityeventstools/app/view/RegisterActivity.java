package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.InstituteGroupsProvider;
import com.example.universityeventstools.app.controller.InstituteLecturerProvider;
import com.example.universityeventstools.app.controller.LoginController;
import com.example.universityeventstools.app.controller.RegisterController;
import com.example.universityeventstools.app.model.ServicePerson;
import com.example.universityeventstools.app.util.EmailValidator;
import com.example.universityeventstools.app.util.PasswordValidator;

import java.io.IOException;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class RegisterActivity extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
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
    private Spinner surnameSpinner;
    private Spinner departmentSpinner;
    private FrameLayout frameLayout;
    private ProgressBar progress;
    //ChangeSpinners changeSpinners = new ChangeSpinners();


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        frameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);
        message = (TextView) findViewById(R.id.message1);
        progress = (ProgressBar) findViewById(R.id.progressbar1_loading);


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
        surnameSpinner = (Spinner) findViewById(R.id.surnameInput);


        userTypeRadioGroup.setOnCheckedChangeListener(this);

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


                    String sDepartment = "";
                    String sFirstName = "";
                    int sGroupId = instituteGroupsProvider.getGroupIdByGroupName(getIntent().getExtras().getString("group"));
                    String sLastName = "";
                    String sPersonId = emailInput.getText().toString();
                    String sPassword = passwordInput.getText().toString();
                    //String sRole = userTypeRadioButton.getText().toString();
                    if (!emailValidator.validate(sPersonId)) {
                        emailInput.setError("Неправильний e-mail");
                    } else if (!passwordValidator.validate(sPassword)) {
                        passwordInput.setError("Некоректний пароль");
                    } else if (!sPassword.equals(confirmPasswordInput.getText().toString())) {
                        passwordInput.setError("Паролі мусять бути однакові");
                    } else {
                        ServicePerson servicePerson = null;

                            servicePerson = new ServicePerson(sDepartment, sFirstName, sGroupId, sLastName, sPassword, sPersonId, "student");




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
                    String sDepartment = "";
                    String sFirstName = "";
                    //int sGroupId = new InstituteLecturerProvider().getLecturerIdByName(getIntent().getExtras().getString("lectur"));
                    String sLastName = getIntent().getExtras().getString("lectur");
                    String sPersonId = emailInput.getText().toString();
                    String sPassword = passwordInput.getText().toString();
                    if (!emailValidator.validate(sPersonId)) {
                        emailInput.setError("Неправильний e-mail");
                    } else if (!passwordValidator.validate(sPassword)) {
                        passwordInput.setError("Некоректний пароль");
                    } else if (!sPassword.equals(confirmPasswordInput.getText().toString())) {
                        passwordInput.setError("Паролі мусять бути однакові");
                    } else {
                        ServicePerson servicePerson = null;



                        servicePerson = new ServicePerson(sDepartment, sFirstName, 0, sLastName, sPassword, sPersonId, "lecturer");


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
                break;
            case R.id.cancelButton:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        new ChangeSpinners().execute(checkedId);

}

    class ChangeSpinners extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            registerBtn.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            frameLayout.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Integer... params) {

            fragmentTransaction = getFragmentManager().beginTransaction();
            Integer[] a = params;
            if (a[0] == R.id.studentRadio) {
                fragmentTransaction.replace(R.id.fragmentContainer, studentFragment);

            } else if (a[0]==R.id.lecturerRadio){

                fragmentTransaction.replace(R.id.fragmentContainer, lecturerFragment);

            }
            fragmentTransaction.commit();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            progress.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            registerBtn.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
        }
    }
}
