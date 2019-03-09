package com.geermank.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geermank.todoapp.Adapters.TasksAdapter;
import com.geermank.todoapp.Database.AppDatabase;
import com.geermank.todoapp.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks;

    private TasksAdapter adapter;

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

        //seteamos el listado de tareas
        tasks = new ArrayList<>();
        completeTasksList();

        ListView lvTasks = findViewById(R.id.lv_tasks);

        adapter = new TasksAdapter(tasks);

        lvTasks.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        completeTasksList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedItemId = item.getItemId();
        if (selectedItemId == R.id.action_add_task){
            startActivity(new Intent(this,AddTaskActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void completeTasksList() {

        /*
        Task t = new Task(0,"Pasear al perro","Media",23123,false,false);
        tasks.add(t);

        Task t1 = new Task(1,"Lavar la ropa","Alta",312312,false,false);
        tasks.add(t1);

        Task t2 = new Task(2,"Lavar los platos","Media",23123,false,false);
        tasks.add(t2);

        Task t3 = new Task(3,"Ir al super","Alta",23123,false,false);
        tasks.add(t3);

        Task t4 = new Task(4,"Llamar a la abuela","Baja",8989,false,false);
        tasks.add(t4);

        Task t5 = new Task(5,"Llamar al plomero","Alta",8997,false,false);
        tasks.add(t5);

        //repito solo para generar volumen en el listado
        tasks.add(t);

        tasks.add(t1);

        tasks.add(t2);

        tasks.add(t3);

        tasks.add(t4);

        tasks.add(t5);*/

        new Thread(new Runnable() {
            @Override
            public void run() {

                tasks.clear();

                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                tasks.addAll(db.getTasksDao().getAllTasks());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();


    }
}
