package com.geermank.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    /**
     * Si este check esta activo cuando el usuario hace click en iniciar sesión,
     * guardamos el usuario y la contraseña del usuario, y lo logueamos directamente la
     * próxima vez que ingrese a la app
     */
    private CheckBox cbRememberUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        cbRememberUser = findViewById(R.id.cb_remember);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this); //podemos escuchar los clicks de cualquier view

        //seteamos el toolbar para que muestre otro titulo
        //y despliegue la navegacion
        toolbarSetUp();
    }

    private void toolbarSetUp() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.login));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItemId = item.getItemId();
        if (selectedItemId == android.R.id.home){
            openRegisterActivity();
        }

        return super.onOptionsItemSelected(item);
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

        saveUserData(email, password);

        Intent mainIntent = new Intent(this,MainActivity.class);
        mainIntent.putExtra(RegisterActivity.EXTRA_EMAIL,email);

        startActivity(mainIntent);
        finish();
    }

    /**
     * Si el check de recordar credenciales está seleccionado, guardamos los datos del
     * usuario en SharedPreferences
     * @param email correo obtenido de etCorreo
     * @param password password obtenido de etPassword
     */
    private void saveUserData(String email, String password) {

        //verificamos si el check de recordar usuario está activo
        boolean rememberUser = cbRememberUser.isChecked();

        //si lo está, guardamos los valores ingresados en SharedPrefs
        if (rememberUser){

            SharedPreferences prefs = getSharedPreferences(RegisterActivity.SHARED_PREFS_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString(RegisterActivity.KEY_SHARED_PREFS_EMAIL,email);
            editor.putString(RegisterActivity.KEY_SHARED_PREFS_PASSWORD,password);

            editor.commit(); //pueden usar apply también
        }
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
