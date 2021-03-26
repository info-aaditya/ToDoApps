package com.tbc.todoapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tbc.todoapps.model.EToDo;
import com.tbc.todoapps.viewModel.TodoViewModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListTodoFragment extends Fragment {

    View rootView;
    RecyclerView rvListTodo;
    TodoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_list_todo, container, false);
        rvListTodo = rootView.findViewById(R.id.listitem_todo_rv_listtodo);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        LinearLayoutManager manager= new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListTodo.setLayoutManager(manager);
        updateRV();

        return  rootView;
    }

    void updateRV(){
        viewModel.getAllToDos().observe(this, new Observer<List<EToDo>>() {
            @Override
            public void onChanged(List<EToDo> eTodos) {
                ToDoAdaptor adaptor = new ToDoAdaptor(eTodos);
                rvListTodo.setAdapter(adaptor);
            }
        });
    }

    private class ToDoHolder extends RecyclerView.ViewHolder{
        TextView title, date;
        public ToDoHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.listitem_todo, parent, false));
            title = itemView.findViewById(R.id.listitem_tv_title);
            date = itemView.findViewById(R.id.listitem_tv_date);
        }

        public void bind (EToDo toDo){
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd");
            title.setText(toDo.getTitle());
            date.setText(sdf.format(toDo.getTodoDate()));
        }
    }

    private class ToDoAdaptor extends RecyclerView.Adapter<ToDoHolder>{
        List<EToDo> eToDoList;
        public ToDoAdaptor(List<EToDo> toDoList){
            eToDoList =toDoList;
        }

        @NonNull
        @Override
        public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ToDoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ToDoHolder holder, int position){
            EToDo toDo = eToDoList.get(position);
            holder.bind(toDo);
        }

        @Override
        public int getItemCount(){
            return eToDoList.size();
        }
    }
}