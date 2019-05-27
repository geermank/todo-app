package com.geermank.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements OnClickListener{


    /**
     * Nombre del archivo donde almacenaremos la info del usuario en caso
     * de que desee que recordemos sus credenciales
     *
     * El trabajo de sharedPreferences podríamos delegarlo en una clase auxiliar, a modo
     * de ejemplo, declaramos todas las constantes en LoginActivity
     *
     */
    public static final String SHARED_PREFS_NAME = "PREFS";

    /**
     * Usamos estas constantes para acceder al archivo de SharedPreferences y
     * ver si habíamos guardado información sobre el usuario. Si tenemos algo
     * guardado, iniciamos sesión directamente
     */
    public static final String KEY_SHARED_PREFS_EMAIL = "KEY_SHARED_PREFS_EMAIL";
    public static final String KEY_SHARED_PREFS_PASSWORD = "KEY_SHARED_PREFS_PASSWORD";

    /**
     * Usados como key en los extra del intent a pasar al MainActivity
     * Ver método registerUser(String name, String email, String password);
     */
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_EMAIL = "EXTRA_EMAIL";

    /**
     * Usado para validar la longitud de la contraseña ingresada por el usuario
     */
    private static final int MIN_PASSWORD_LENGTH = 7;

    private EditText etName, etEmail, etPassword;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);

        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

        verifyUserSavedCredentials();

    }

    /**
     * Verificamos si el usuario guardó sus credenciales la última vez
     * que inició sesión. Si lo hizo, lo dirigimos directamente a la pantalla
     * principal
     */
    private void verifyUserSavedCredentials() {

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_NAME,MODE_PRIVATE);

        //aca podríamos usar contains también, para verificar si el archivo posee
        //algo en las claves que le pasamos por parámetro
        String email = prefs.getString(KEY_SHARED_PREFS_EMAIL,null);
        String password = prefs.getString(KEY_SHARED_PREFS_PASSWORD,null);

        if (email == null && password == null){
            return; //no hacemos nada, quiere decir que el usr no guardó sus credenciales
        }

        //si guardó sus credenciales, abrimos directamente el MainActivity
        //podemos utilizar el método registerUser, ya que simplemente crea un intent donde
        //pasa algunos valores como extras, y abre esta pantalla. Para efectos prácticos,
        //no le pasamos nada en el nombre.
        registerUser("",email,password);

    }

    @Override
    public void onClick(View v) {

        if (v == btnRegister){

            //verificar que el input del usuario es valido
            //si lo es, registarlo, sino mostrarle un mensaje y no dejarlo continuar
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            //como el metodo userInputIsValid() devuelve un boolean, lo puedo utilizar
            //dentro del if como condición a cumplir. Es decir, que si el método devuelve
            //false, como está negado, se cambiará a true e ingresará en el if. Caso contrario,
            //continuará la ejecución del programa.
            if (!userInputIsValid(name,email,password)){
                return;
            }

            registerUser(name,email,password);

        }else if (v == tvLogin){
            openLoginActivity();
        }

    }

    /**
     * Este método lo usaremos para verificar que los String obtenidos a partir de los EditText
     * sean válidos
     *
     * Las validaciones a realizar son:
     * - El nombre no debe estar vacío
     * - El correo no debe estar vacío
     * - La contraseña no debe estar vacía
     * - El correo debe contener un @
     * - La contraseña debe tener al menos 7 caracteres
     *
     *
     * @param name obtenido del EditText Nombre.
     * @param email obtenido del EditText Email
     * @param password obtenido del EditText Password
     * @return devuelve true si se cumplen las validaciones, false si hay algún error
     */
    private boolean userInputIsValid(String name, String email, String password){

        boolean inputIsValid = true;

        if (name.isEmpty()){
            inputIsValid = false;
            showToast("Completa con tu nombre para seguir");
        }else if (email.isEmpty()){
            inputIsValid = false;
            showToast("Completa con tu correo para seguir");
        }else if (password.isEmpty()){
            inputIsValid = false;
            showToast("Completa con tu contraseña para seguir");
        }else if (!email.contains("@")){
            inputIsValid = false;
            showToast("Completa con un correo valido");
        }else if (password.length() < MIN_PASSWORD_LENGTH){
            inputIsValid = false;
            showToast("La contraseña es demasiado corta");
        }

        return inputIsValid;

    }

    /**
     * Muestra un toast con el texto recibido por parámetro
     * @param msg Mensaje a mostrar en el Toast
     */
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Abrimos MainActivity, y enviamos como extra del Intent el nombre y el correo
     *
     * @param name Nombre validado, obtenido del EditText Name
     * @param email Correo validado, obtenido del EditText Email
     * @param password Password validado, obtenido del EditText Password
     */
    private void registerUser(String name, String email, String password){

        Intent mainIntent = new Intent(this,MainActivity.class);
        mainIntent.putExtra(EXTRA_NAME,name);
        mainIntent.putExtra(EXTRA_EMAIL,email);

        startActivity(mainIntent);
        finish();
    }

    private void openLoginActivity(){
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

}
