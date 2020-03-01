package com.soushetty.todo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {
    /*this is an entity which represents table.In this database everything is an object.
    using annotations to depict how the table should be */
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "todo_col")
    private String todo;

    public ToDo(@NonNull String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTodo(@NonNull String todo) {
        this.todo = todo;
    }
}
