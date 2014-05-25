package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.login.LoginController;
import com.example.universityeventstools.app.model.Schedule;
import com.example.universityeventstools.app.model.ServicePerson;
import com.example.universityeventstools.app.util.EmailValidator;

import java.io.IOException;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    private Button loginBtn;
    private Button registerBtn;
    private EditText usernameField;
    private EditText passwordField;
    private ImageView logoImg;
    private TextView message;


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        usernameField = (EditText) findViewById(R.id.usernameInput);
        usernameField.setHint("E-mail");
        passwordField = (EditText) findViewById(R.id.passwordInput);
        passwordField.setHint("Пароль");
        logoImg = (ImageView) findViewById(R.id.logoImg);
        logoImg.setImageResource(R.drawable.ic_logo);
        message = (TextView) findViewById(R.id.message);

        if (getIntent().hasExtra("message") && getIntent().hasExtra("messageColor")) {
            message.setText(getIntent().getExtras().getString("message"));
            if (getIntent().getExtras().getString("messageColor").equals("green")) {
                message.setTextColor(Color.GREEN);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                EmailValidator emailValidator = new EmailValidator();
                LoginController loginController = new LoginController();

                if (!emailValidator.validate(usernameField.getText().toString())) {
                    usernameField.setError("Введіть правильний e-mail");
                } else {
                    ServicePerson servicePerson = new ServicePerson();
                    servicePerson.setPersonID(usernameField.getText().toString());
                    servicePerson.setPassword(passwordField.getText().toString());
                    Integer callBack = 0;
                    try {
                        callBack = loginController.login(servicePerson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!callBack.equals(0)) {
                        Intent intent = new Intent(this, ScheduleActivity.class);
                        intent.putExtra("groupId",callBack);
                        startActivity(intent);
                    } else {
                        message.setText("Неправильний E-mail чи пароль");
                        message.setTextColor(Color.RED);
                    }
                }
                break;
            case R.id.registerBtn:
                Intent intent = new Intent(this, RegisterActivity.class);
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
