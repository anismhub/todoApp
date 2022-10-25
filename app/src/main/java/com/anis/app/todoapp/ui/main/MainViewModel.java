package com.anis.app.todoapp.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anis.app.todoapp.database.ToDo;
import com.anis.app.todoapp.repository.ToDoRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final ToDoRepository mToDoRepository;

    public MainViewModel(Application application){
    mToDoRepository = new ToDoRepository(application);
    }

    public void delete(ToDo toDo) { mToDoRepository.delete(toDo);}
    LiveData<List<ToDo>> getAllToDos() {
        return mToDoRepository.getAllToDos();
    }
}
