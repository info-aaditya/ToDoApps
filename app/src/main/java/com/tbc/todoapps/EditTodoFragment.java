package com.tbc.todoapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tbc.todoapps.model.EToDo;
import com.tbc.todoapps.viewModel.TodoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditTodoFragment extends Fragment {

    View rootView;
    EditText txtTitle, txtDescription, txtDate;
    RadioGroup rgPriority;
    Button btnSave, btnCancel;
    CheckBox chComplete;

    public static final int HIGH_PRIORITY = 1;
    public static final int MEDIUM_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.fragment_edit_todo, container, false);

        txtTitle = rootView.findViewById(R.id.edit_fragment_txt_name);
        txtDescription = rootView.findViewById(R.id.edit_fragment_txt_description);
        txtDate = rootView.findViewById(R.id.edit_fragment_txt_date);
        rgPriority = rootView.findViewById(R.id.edit_fragment_rg_priority);
        chComplete = rootView.findViewById(R.id.edit_fragment_chk_complete);
        btnSave = rootView.findViewById(R.id.edit_fragment_btn_save);
        btnCancel = rootView.findViewById(R.id.edit_fragment_btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToDo();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    void SaveToDo(){
        EToDo eToDo = new EToDo();
        Date todoDate = new Date();
        int checkedPriority = -1;
        int priority = 0;
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            todoDate = format.parse(txtDate.getText().toString());
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        checkedPriority = rgPriority.getCheckedRadioButtonId();

        switch (checkedPriority) {
            case R.id.edit_fragment_rb_high:
                priority = HIGH_PRIORITY;
                break;
            case R.id.edit_fragment_rb_medium:
                priority = MEDIUM_PRIORITY;
                break;
            case R.id.edit_fragment_tb_low:
                priority = LOW_PRIORITY;
                break;
        }

        eToDo.setTitle(txtTitle.getText().toString());
        eToDo.setDescription(txtDescription.getText().toString());
        eToDo.setTodoDate(todoDate);
        eToDo.setPriority(priority);
        eToDo.setComplete(chComplete.isChecked());

        TodoViewModel viewModel  = new ViewModelProvider(this).get(TodoViewModel.class);
        viewModel.insert(eToDo);

        Toast.makeText(getActivity(), "Todo Saved",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}