package com.geermank.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Comportamiento similar a RegisterActivity, realizando casi las mismas validaciones
 * Más adelante modificaremos algunas cosas dentro de esta activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this); //podemos escuchar los clicks de cualquier view
    }

    @Override
    public void onClick(View v) {

        if (v == btnLogin){

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if (!isUserInputValid(email,password)){
                return;
            }

            loginUser(email,password);

        }else if (v == tvRegister){
            openRegisterActivity();
        }


    }

    private boolean isUserInputValid(String email, String password) {

        boolean userInputValid = true;

        if (email.isEmpty()){
            userInputValid = false;
            showToast("Completa con tu correo electrónico");
        }else if (password.isEmpty()){
            userInputValid = false;
            showToast("Completa con tu contraseña");
        }

        return userInputValid;

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void loginUser(String email, String password) {
        Intent mainIntent = new Intent(this,MainActivity.class);
        mainIntent.putExtra(RegisterActivity.EXTRA_EMAIL,email);

        startActivity(mainIntent);
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
