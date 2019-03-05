package com.geermank.todoapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geermank.todoapp.Models.Task;
import com.geermank.todoapp.R;

import java.util.List;

public class TasksAdapter extends BaseAdapter {

    private List<Task> tasks;

    public TasksAdapter(List<Task> tasks){
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_tasks,parent,false);
        }else{
            view = convertView;
        }

        Task task = getItem(position);

        TextView tvTitle = view.findViewById(R.id.tv_task_title);
        TextView tvPriority = view.findViewById(R.id.tv_task_priority);

        tvTitle.setText(task.getTitle());
        tvPriority.setText(task.getPriority());

        return view;
    }
}
