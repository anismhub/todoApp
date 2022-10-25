package com.anis.app.todoapp.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anis.app.todoapp.R;
import com.anis.app.todoapp.databinding.ActivityMainBinding;
import com.anis.app.todoapp.helper.ViewModelFactory;
import com.anis.app.todoapp.ui.insert.ToDoAddUpdateActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private ToDoAdapter adapter;

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("To Do List");

        adapter = new ToDoAdapter();

        binding.rvTodo.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTodo.setHasFixedSize(true);
        binding.rvTodo.setAdapter(adapter);

        mainViewModel = obtainViewModel(MainActivity.this);

        mainViewModel.getAllToDos().observe(this, toDos -> {
            if (toDos != null) {
                adapter.setListToDos(toDos);
            }
        });
        binding.fabAdd.setOnClickListener(v -> {
            if (v.getId() == R.id.fab_add) {
                Intent intent = new Intent(MainActivity.this, ToDoAddUpdateActivity.class);
                startActivity(intent);

            }
        });

        adapter.setOnItemClickCallback(toDo -> {
            mainViewModel.delete(toDo);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}