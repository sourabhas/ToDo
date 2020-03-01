package com.soushetty.todo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.soushetty.todo.util.todo_Repository;

import java.util.List;

public class todo_viewmodel extends AndroidViewModel {

    todo_Repository todo_repository;
    LiveData<List<ToDo>> listLiveData;

    public todo_viewmodel(@NonNull Application application) {
        super(application);

        todo_repository=new todo_Repository(application);
        listLiveData=todo_repository.getLiveDatalist();
    }

    public LiveData<List<ToDo>> getListLiveData() {
        return listLiveData;
    }

    public void insert(ToDo item){
        todo_repository.insert(item);
    }
}
