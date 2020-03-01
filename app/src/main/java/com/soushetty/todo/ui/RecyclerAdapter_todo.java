package com.soushetty.todo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soushetty.todo.R;
import com.soushetty.todo.model.ToDo;

import java.util.List;
import java.util.zip.Inflater;

public class RecyclerAdapter_todo extends RecyclerView.Adapter<RecyclerAdapter_todo.todoviewholder>{

    private  TextView textView;
    private final LayoutInflater inflater;
    public List<ToDo> toDoList; //to save all the  items

    public RecyclerAdapter_todo(Context context) {
       inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public todoviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recyclierview_item,parent,false);

        return new todoviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull todoviewholder holder, int position) {
        //to bind the ui data and the object

        if(toDoList!=null){
            ToDo current=toDoList.get(position);
            textView.setText(current.getTodo());
        }else{
            textView.setText("No TODO's ");

        }

    }
    public void settodos(List<ToDo> todos){ //a method to call when required to notify changes
        toDoList=todos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(toDoList!=null){
            return toDoList.size();
        }else {
            return 0;
        }
    }

    public class todoviewholder extends RecyclerView.ViewHolder{

        public todoviewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textbox);
        }
    }
}
