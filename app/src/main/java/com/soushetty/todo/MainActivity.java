package com.soushetty.todo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.soushetty.todo.model.ToDo;
import com.soushetty.todo.model.todo_viewmodel;
import com.soushetty.todo.ui.RecyclerAdapter_todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_TODO_CODE = 1;
    RecyclerAdapter_todo recyclerAdapter;
    public todo_viewmodel viewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewmodel= ViewModelProviders.of(this).get(todo_viewmodel.class); //to get livedata from view model

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerAdapter=new RecyclerAdapter_todo(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NewitemActivity.class);
                startActivityForResult(intent,NEW_TODO_CODE);
            }
        });
       viewmodel.getListLiveData().observe(this, new Observer<List<ToDo>>() {
           @Override
           public void onChanged(List<ToDo> toDos) {
               //updating the saved copy of todo's data in adapter
              recyclerAdapter.settodos(toDos);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_TODO_CODE && resultCode==RESULT_OK){
            assert data!=null;
            ToDo toDo=new ToDo(data.getStringExtra(NewitemActivity.REPLY_INTENT));
            viewmodel.insert(toDo);

        }else{
            Toast.makeText(this,"Empty..Nothing saved",Toast.LENGTH_SHORT).show();
        }
    }
}
