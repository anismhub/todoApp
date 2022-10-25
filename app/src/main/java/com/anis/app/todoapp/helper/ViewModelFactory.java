package com.anis.app.todoapp.helper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anis.app.todoapp.ui.insert.ToDoAddUpdateViewModel;
import com.anis.app.todoapp.ui.main.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private ViewModelFactory(Application application) {
        mApplication = application;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(application);
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(ToDoAddUpdateViewModel.class)) {
            return (T) new ToDoAddUpdateViewModel(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class" +modelClass.getName());

    }
 }
