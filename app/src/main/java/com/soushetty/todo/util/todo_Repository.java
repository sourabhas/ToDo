package com.soushetty.todo.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.soushetty.todo.data.todo_DAO;
import com.soushetty.todo.data.todo_roomDatabase;
import com.soushetty.todo.model.ToDo;

import java.util.List;

public class todo_Repository {

    private todo_DAO toDoDAO;

    private LiveData<List<ToDo>> liveDatalist;

    public todo_Repository(Application application){ //we are passing the whole application and not just any context here

        todo_roomDatabase db=todo_roomDatabase.getDatabase(application);//creating and using one instance of roomdatabase class
        toDoDAO=db.todo_dao();
        liveDatalist=toDoDAO.getallitems();

        //to use data from any remote API we just have to create another List to hold it here and handle
    }

    public LiveData<List<ToDo>> getLiveDatalist(){
        return liveDatalist;
    }

    public void insert(ToDo item){
        //we need to insert through AScyn task
        new insertAscynTask(toDoDAO).execute(item);

    }


    private class insertAscynTask extends AsyncTask<ToDo,Void,Void> {
        private todo_DAO acynctask_dao;
        public insertAscynTask (todo_DAO toDoDAO) {
            acynctask_dao=toDoDAO;

        }

        @Override
        protected Void doInBackground(ToDo... params) { //lot of parameters of type ToDo can be passed here
            //parameters come as arrays [obj1,obj2,obj3.....]

            acynctask_dao.insert(params[0]);
            return null;
        }
    }
}
