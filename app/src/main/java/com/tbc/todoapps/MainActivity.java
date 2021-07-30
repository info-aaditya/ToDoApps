package com.tbc.todoapps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tbc.todoapps.viewModel.TodoViewModel;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragment = new ListTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.list_activity_container, fragment)
                .commit();

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_all:
                ShowAlertDeleteAll();
                break;
            case R.id.menu_delete_completed:
                ShowAlertDeleteCompleted();
                break;
            case R.id.menu_logout:
                ShowAlertLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Alert Dialog Box For Delete  Completed
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//for get drawable method
    void ShowAlertDeleteAll(){
        new MaterialAlertDialogBuilder(MainActivity.this,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("To Do List")
                .setMessage(getString(R.string.alert_delete_all))
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Delete all", Toast.LENGTH_LONG).show();
                        new ViewModelProvider(MainActivity.this).get(TodoViewModel.class).deleteAll();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    //Alert Dialog Box For Delete All
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//for get drawable method
    void ShowAlertDeleteCompleted(){
        new MaterialAlertDialogBuilder(MainActivity.this,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("To Do List")
                .setMessage(getString(R.string.alert_delete_comp))
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Delete Completed", Toast.LENGTH_LONG).show();
                        new ViewModelProvider(MainActivity.this).get(TodoViewModel.class).deleteCompleted();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    //Alert Dialog Box For Delete All
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//for get drawable method
    void ShowAlertLogout(){
        new MaterialAlertDialogBuilder(MainActivity.this,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("To Do List")
                .setMessage(getString(R.string.alert_logout))
                .setIcon(R.drawable.ic_logout)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}