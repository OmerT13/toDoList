package com.ooteedemo.todo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ooteedemo.todo.R;
import com.ooteedemo.todo.model.ToDo;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder>{

    private final LayoutInflater toDoInflater;
    private List<ToDo> toDoList;

    public ToDoListAdapter(Context context) {
        toDoInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = toDoInflater.inflate(R.layout.recyclerview_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListAdapter.ViewHolder viewHolder, int position) {
        if (toDoList!=null) {
            ToDo current = toDoList.get(position);
            viewHolder.toDoTextView.setText(current.getToDo());
        } else {
            viewHolder.toDoTextView.setText(R.string.no_todo);
        }
    }

    public void setToDos(List<ToDo> toDos) {
        toDoList = toDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (toDoList!=null) return toDoList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView toDoTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTextView = itemView.findViewById(R.id.textView);
        }
    }
}
