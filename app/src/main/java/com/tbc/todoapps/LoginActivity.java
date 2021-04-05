package com.tbc.todoapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tbc.todoapps.model.UserHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username, password ;
    Button btn_login;
    TextView text_sign_up;
    UserHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        btn_login = (Button)findViewById(R.id.btn_login);
        text_sign_up = (TextView)findViewById(R.id.text_sign_up);

        DB = new UserHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    if (username.getText().length()==0){
                        username.requestFocus();
                        username.setError("Name is required");
                    }
                    if (password.getText().length()==0){
                        password.requestFocus();
                        password.setError("Password is required");
                    }
                    return;
                }
                else {
                    boolean result = DB.checkusernamePassword(user,pass);
                    if(result == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        text_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}