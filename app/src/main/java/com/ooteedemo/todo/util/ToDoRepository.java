package com.ooteedemo.todo.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ooteedemo.todo.data.ToDoDao;
import com.ooteedemo.todo.data.ToDoRoomDatabase;
import com.ooteedemo.todo.model.ToDo;

import java.util.List;

public class ToDoRepository {
    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;

    public ToDoRepository(Application application) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        toDoDao = db.toDoDao_abstract();
        allToDos = toDoDao.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void insert(ToDo todo) {
        new insertAsyncTask(toDoDao).execute(todo);
    }

    private class insertAsyncTask extends AsyncTask<ToDo,Void,Void> {
        private ToDoDao asyncTaskDao;
        public insertAsyncTask(ToDoDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ToDo... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
