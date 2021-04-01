package com.tbc.todoapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tbc.todoapps.model.UserHelper;

public class RegistrationActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button btn_sign_up;
    TextView text_login;
    UserHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repassword =(EditText)findViewById(R.id.repassword);

        btn_sign_up = (Button)findViewById(R.id.btn_sign_up);
        text_login = (TextView)findViewById(R.id.text_login);

        DB = new UserHelper(this);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                {

                    if (username.getText().length()==0){
                        username.requestFocus();
                        username.setError("Enter your Name");
                    }
                    if (password.getText().length()==0){
                        password.requestFocus();
                        password.setError("Enter Password");
                    }
                    if (repassword.getText().length()==0){
                        repassword.requestFocus();
                        repassword.setError("Re-enter Password");
                    }
                    return ;
                }
                else {
                    if(pass.equals(repass))
                    {
                        boolean usercheckResult = DB.checkusername(user);
                        if(usercheckResult == false)
                        {
                            boolean reResult = DB.insertDate(user,pass);
                            if(reResult == true)
                            {
                                Toast.makeText(RegistrationActivity.this, "Register Succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "User Already Exist. \n Please Sign In.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}