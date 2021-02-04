package com.ooteedemo.todo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ooteedemo.todo.model.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoRoomDatabase extends RoomDatabase {

//    DBINSTANCE is a singleton that we create to make sure we don't instantiate the DB more than once
    public static volatile ToDoRoomDatabase DBINSTANCE;
    public abstract ToDoDao toDoDao_abstract();

    public static ToDoRoomDatabase getDatabase(final Context context) {
        if (DBINSTANCE==null) {
            synchronized (ToDoRoomDatabase.class) {
                if (DBINSTANCE==null) {
                    DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class,"todo_db").build();
                }
            }
        }
        return DBINSTANCE;
    }
}
