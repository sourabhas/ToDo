package com.soushetty.todo.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.soushetty.todo.model.ToDo;

@Database(entities = {ToDo.class},version = 1)
public abstract class todo_roomDatabase extends RoomDatabase {

    public static volatile todo_roomDatabase INSTANCE;  //inorder make it a singleton i.e only one instance exists

    //to use instance of DAO-create an abstract method of it to be implemented where ever required
    public abstract todo_DAO todo_dao();

    //creating a singleton instance of class so that we donnot run into issues and whenever we need it,only then we create it
    public static todo_roomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (todo_roomDatabase.class){
                if(INSTANCE==null){
                    //create database

                    INSTANCE= Room.databaseBuilder(context.getApplicationContext()
                            ,todo_roomDatabase.class,"todo_db")
                            .addCallback(roomdatabase_callback)
                            .build();

                }

            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomdatabase_callback=new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDBasync(INSTANCE).execute();
        }
    };

    private static class populateDBasync extends AsyncTask<Void, Void, Void> {
        private final todo_DAO dao;
        public populateDBasync(todo_roomDatabase instance) {
            dao=instance.todo_dao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();

            ToDo toDo=new ToDo("call the docotr");
            dao.insert(toDo );

            return null;
        }
    }
}
