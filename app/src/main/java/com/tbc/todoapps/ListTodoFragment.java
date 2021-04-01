package com.tbc.todoapps;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        List<EToDo> toDoList = viewModel.getAllToDos().getValue();
                        ToDoAdaptor adaptor = new ToDoAdaptor(toDoList);
                        EToDo toDo = adaptor.getToDoAt(viewHolder.getAdapterPosition());
                        viewModel.deleteById(toDo);
                    }
                })
                .attachToRecyclerView(rvListTodo);
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
        public ToDoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.listitem_todo, parent, false));
            title = itemView.findViewById(R.id.listitem_tv_title);
            date = itemView.findViewById(R.id.listitem_tv_date);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadUpdateItem();
                }
            });
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadUpdateItem();
                }
            });
        }

        void loadUpdateItem(){
            ToDoAdaptor adaptor = new ToDoAdaptor(viewModel.getAllToDos().getValue());
            int i = getAdapterPosition();
            EToDo toDo = adaptor.getToDoAt(i);
            Intent intent = new Intent(getActivity(), EditActivity.class);
            intent.putExtra("TodoId", toDo.getId());
            startActivity(intent);
            Toast.makeText(getContext(),"Update Item: " + toDo.getId(), Toast.LENGTH_LONG).show();
        }

        public void bind (EToDo toDo){
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
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
            LinearLayout layout = (LinearLayout)((ViewGroup)holder.title.getParent());
            switch (toDo.getPriority()){
                case    1:
                    layout.setBackgroundColor(getResources().getColor(R.color.Bright_Red));
                    break;
                case 2:
                    layout.setBackgroundColor(getResources().getColor(R.color.Turbo));
                    break;
                case 3:
                    layout.setBackgroundColor(getResources().getColor(R.color.Fruit_Salad));
                    break;
            }
            holder.bind(toDo);
        }

        @Override
        public int getItemCount(){
            return eToDoList.size();
        }

        public EToDo getToDoAt(int position) {
            return eToDoList.get(position);
        }
    }
}