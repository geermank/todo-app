package com.geermank.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recibe los extras que vienen con el Intent que inició la activity
        //utilizamos los mismos keys que en RegisterActivity
        String name = getIntent().getStringExtra(RegisterActivity.EXTRA_NAME);
        String email = getIntent().getStringExtra(RegisterActivity.EXTRA_EMAIL);

        TextView tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setText("Bienvenido" + " " + name + "," + " " + email);


    }
}
