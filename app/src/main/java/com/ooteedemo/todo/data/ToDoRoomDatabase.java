package com.ooteedemo.todo.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            ToDoRoomDatabase.class,"todo_db")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return DBINSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(DBINSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ToDoDao toDoDao;
        public PopulateDbAsync(ToDoRoomDatabase db) {
            toDoDao = db.toDoDao_abstract();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            testDBPopulation (toDoDao);
            return null;
        }
    }

    private static void testDBPopulation(ToDoDao toDoDao) {
        toDoDao.deleteAll();

        ToDo toDo = new ToDo("Wash the car");
        toDoDao.insert(toDo);

        toDo = new ToDo("Fill up car gas");
        toDoDao.insert(toDo);

        toDo = new ToDo("Get a car");
        toDoDao.insert(toDo);
    }
}
