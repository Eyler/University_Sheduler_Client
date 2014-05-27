package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.LoginController;
import com.example.universityeventstools.app.model.ServicePerson;
import com.example.universityeventstools.app.util.EmailValidator;
import com.example.universityeventstools.app.util.PasswordValidator;

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
    private ProgressBar progress;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        progress = (ProgressBar) findViewById(R.id.progressbar_loading);
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
            } else if (getIntent().getExtras().getString("messageColor").equals("red")) {
                message.setTextColor(Color.RED);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                EmailValidator emailValidator = new EmailValidator();
                PasswordValidator passwordValidator = new PasswordValidator();
                //LoginController loginController = new LoginController();

                if (!emailValidator.validate(usernameField.getText().toString())) {
                    usernameField.setError("Введіть правильний e-mail");
                    usernameField.setLines(1);
                } else if (!passwordValidator.validate(passwordField.getText().toString())) {
                    passwordField.setError("Введіть правильний пароль");
                    passwordField.setLines(1);
                } else {
                    Login mt = new Login();
                    mt.execute();
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

    class Login extends AsyncTask<Void, Void, Integer> {
        Integer callBack = 0;
        String tmp = "1";

        @Override
        protected void onPreExecute() {
            loginBtn.setVisibility(View.GONE);
            registerBtn.setVisibility(View.GONE);
            usernameField.setVisibility(View.GONE);
            passwordField.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            LoginController loginController = new LoginController();
            ServicePerson servicePerson = new ServicePerson();
            servicePerson.setPersonID(usernameField.getText().toString());
            servicePerson.setPassword(passwordField.getText().toString());

            try {
                callBack = loginController.login(servicePerson);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return callBack;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (callBack.equals(-1)) {
                loginBtn.setVisibility(View.VISIBLE);
                registerBtn.setVisibility(View.VISIBLE);
                usernameField.setVisibility(View.VISIBLE);
                passwordField.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                message.setText("Неправильний E-mail чи пароль");
                message.setTextColor(Color.RED);
            } else {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("groupId", callBack);
                intent.putExtra("username", usernameField.getText().toString());
                startActivity(intent);
            }


        }
    }
}
