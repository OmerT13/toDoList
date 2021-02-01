package com.ooteedemo.todo.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ooteedemo.todo.model.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void insert(ToDo toDo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("DELETE FROM todo_table where id=:id")
    int deleteToDo(int id);

    @Query("UPDATE todo_table SET todo_col=:toDoText WHERE id=:id")
    int updateToDoItem(int id,String toDoText);

    @Query("SELECT * FROM todo_table")
    List<ToDo> getAllToDos();
}
