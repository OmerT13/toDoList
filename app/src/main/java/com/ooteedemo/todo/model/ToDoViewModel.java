package com.ooteedemo.todo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ooteedemo.todo.util.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {
    private ToDoRepository toDoRepository;
    private LiveData<List<ToDo>> allToDos;
    public ToDoViewModel(@NonNull Application application) {
        super(application);
        toDoRepository = new ToDoRepository(application);
        allToDos = toDoRepository.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void insert(ToDo todo) {
        toDoRepository.insert(todo);

    }
}
