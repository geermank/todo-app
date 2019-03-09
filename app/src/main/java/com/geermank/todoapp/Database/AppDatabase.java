package com.geermank.todoapp.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.geermank.todoapp.Models.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TasksDao getTasksDao();

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){

        if (sInstance == null){
            //esto es por si se llama de varios threads diferentes,
            //permite que sólo un subproceso por vez pueda acceder a él
            synchronized (AppDatabase.class){

                if (sInstance == null){

                    sInstance = Room.databaseBuilder(context,
                            AppDatabase.class,
                            "AppDatabase")
                            .build();

                }

            }

        }

        return sInstance;

    }

}
