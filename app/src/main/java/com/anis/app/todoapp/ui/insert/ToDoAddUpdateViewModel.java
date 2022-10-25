package com.anis.app.todoapp.ui.insert;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.anis.app.todoapp.database.ToDo;
import com.anis.app.todoapp.repository.ToDoRepository;

public class ToDoAddUpdateViewModel extends ViewModel {
    private final ToDoRepository mToDoRepository;

    public ToDoAddUpdateViewModel(Application application) {
        mToDoRepository = new ToDoRepository(application);
    }

    public void insert(ToDo toDo) {
        mToDoRepository.insert(toDo);
    }

    public void update(ToDo toDo) {
        mToDoRepository.update(toDo);
    }
}
