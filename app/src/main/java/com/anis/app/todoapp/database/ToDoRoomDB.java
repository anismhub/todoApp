package com.anis.app.todoapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoRoomDB extends RoomDatabase {
    public abstract ToDoDao toDoDao();

    private static volatile ToDoRoomDB INSTANCE;

    public static ToDoRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoRoomDB.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ToDoRoomDB.class, "todo_db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
