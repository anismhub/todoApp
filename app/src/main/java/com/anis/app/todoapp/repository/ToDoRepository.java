package com.anis.app.todoapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.anis.app.todoapp.database.ToDo;
import com.anis.app.todoapp.database.ToDoDao;
import com.anis.app.todoapp.database.ToDoRoomDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToDoRepository {
    private  final ToDoDao mToDoDAO;
    private final ExecutorService executorService;

    public ToDoRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        ToDoRoomDB db = ToDoRoomDB.getDatabase(application);
        mToDoDAO = db.toDoDao();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return mToDoDAO.getAllToDos();
    }

    public void insert(final ToDo toDo) {
        executorService.execute(() -> mToDoDAO.insert(toDo));
    }

    public void delete(final ToDo toDo) {
        executorService.execute(() -> mToDoDAO.delete(toDo));
    }

    public void update(final ToDo toDo) {
        executorService.execute(() -> mToDoDAO.update(toDo));
    }
}
