package com.soushetty.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.soushetty.todo.model.ToDo;

import java.util.List;

// all the CRUD operations are mentioned below

@Dao
public interface todo_DAO {

    @Insert
    void insert(ToDo todo);

    @Query("DELETE FROM todo_table")
    int deleteAll();

    @Query("DELETE FROM todo_table WHERE id=:id")
    int deleteONEitem(int id);

    @Query("UPDATE todo_table SET todo_col=:text WHERE id=:id")
    int updateitem(int id,String text);

    @Query("SELECT * FROM todo_table ORDER BY todo_col DESC")
    LiveData<List<ToDo>> getallitems();  // Livedata layer here to keep it updated all the time with the real data sets

}
