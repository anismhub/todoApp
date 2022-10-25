package com.anis.app.todoapp.ui.insert;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anis.app.todoapp.database.ToDo;
import com.anis.app.todoapp.databinding.ActivityToDoAddUpdateBinding;
import com.anis.app.todoapp.helper.ViewModelFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToDoAddUpdateActivity extends AppCompatActivity {

    public static final String EXTRA_TODO = "extra_todo";

    private boolean isEdit = false;
    private ToDo toDo;

    private  ToDoAddUpdateViewModel toDoAddUpdateViewModel;
    private ActivityToDoAddUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityToDoAddUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toDoAddUpdateViewModel = obtainViewModel(ToDoAddUpdateActivity.this);

        toDo = getIntent().getParcelableExtra(EXTRA_TODO);
        if (toDo != null) {
            isEdit = true;
        } else {
            toDo = new ToDo();
        }

        String actionBarTitle;

        if (isEdit) {
            actionBarTitle = "Edit Task";
            binding.btnAdd.setText("Update");

            if (toDo != null) {
                binding.inputDesc.getEditText().setText(toDo.getDesc());
            }
        } else {
            actionBarTitle = "Add Task";
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.btnAdd.setOnClickListener(v -> {
            String desc =  binding.inputDesc.getEditText().getText().toString();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dateNow = new Date();
            String date = dateFormat.format(dateNow).toString();

            if (desc.isEmpty()) {
                binding.inputDesc.setError("Description Kosong");
            } else {
                toDo.setDesc(desc);
                toDo.setDate(date);

                if (isEdit) {
                    toDoAddUpdateViewModel.update(toDo);
                } else {
                    toDoAddUpdateViewModel.insert(toDo);
                }

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @NonNull
    private static ToDoAddUpdateViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity,factory).get(ToDoAddUpdateViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}