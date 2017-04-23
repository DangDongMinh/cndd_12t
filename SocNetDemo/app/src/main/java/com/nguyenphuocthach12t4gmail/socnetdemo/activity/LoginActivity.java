package com.nguyenphuocthach12t4gmail.socnetdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nguyenphuocthach12t4gmail.socnetdemo.R;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btbLogin;
    private Button btbRegister;
    private LoginController loginController;
    private SessionLoginController sessionLoginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        username = (EditText) findViewById(R.id.txt_user_name);
        password = (EditText) findViewById(R.id.txt_password);
        btbLogin = (Button) findViewById(R.id.btnLogin);
        btbRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        loginController = new LoginController(this);
        sessionLoginController = new SessionLoginController(this);
        checkLogged();
        registerAccount(btbRegister);
        HandlerLogin(btbLogin);

    }

    private void registerAccount(Button btbRegister) {
        btbRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });

    }

    public void HandlerLogin(Button btbLogin) {
        btbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                if ("".equals(userName) || "".equals(passWord)) {
                    Toast.makeText(getApplicationContext(), "Missing Input", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean isLogin = loginController.isValidLogin(userName, passWord);
                    if (isLogin) {
                        sessionLoginController.setLogin(true, loginController.getYourId(userName), loginController.getYourType(userName));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void checkLogged() {
        if (sessionLoginController.isLoggedIn()) {
            // User is already logged in. Take him to activity_main activity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


}
