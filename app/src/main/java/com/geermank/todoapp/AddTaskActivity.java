package com.geermank.todoapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geermank.todoapp.Database.AppDatabase;
import com.geermank.todoapp.Models.Task;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitle, etPriority;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle = findViewById(R.id.et_task_title);
        etPriority = findViewById(R.id.et_task_priority);
        btnAddTask = findViewById(R.id.btn_add_task);

        btnAddTask.setOnClickListener(this);

        actionBarSetUp();

    }

    private void actionBarSetUp() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(getString(R.string.add_task));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItem = item.getItemId();
        if (selectedItem == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == btnAddTask){

            String title = etTitle.getText().toString();
            String priority = etPriority.getText().toString();

            if (userInputIsNotValid(title,priority)){
                Toast.makeText(this, getString(R.string.verify_input),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Task task = createTask(title,priority);

            saveTask(task);

        }


    }

    private boolean userInputIsNotValid(String title, String priority) {

        boolean inputIsInvalid = false;

        if (title.isEmpty()) inputIsInvalid = true;
        else if (priority.isEmpty()) inputIsInvalid = true;

        return inputIsInvalid;
    }

    private Task createTask(String title, String priority) {
        return new Task(title,priority,0,false,false);
    }

    private void saveTask(final Task t){

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase database = AppDatabase.getInstance(AddTaskActivity.this);
                database.getTasksDao().createTask(t);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddTaskActivity.this, getString(R.string.task_saved),
                                Toast.LENGTH_SHORT).show();

                        etTitle.setText("");
                        etPriority.setText("");

                    }
                });

            }
        }).start();

    }

}
