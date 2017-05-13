package com.example.ajay.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etLoginEmail,etLoginPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Screen");

        initUIControls();
        registerForListeners();
    }

    private void registerForListeners() {
        btnLogin.setOnClickListener(this);
    }

    private void initUIControls() {
        etLoginEmail= (EditText) findViewById(R.id.etLoginEmail);
        etLoginPassword= (EditText) findViewById(R.id.etLoginPassword);
        btnLogin= (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {
        if (MyValidation.isEmpty(etLoginEmail.getText().toString()) || !MyValidation.isValidEmail(etLoginEmail.getText().toString()))
        {
            Toast.makeText(this,"Enter Valid Email Id...!",Toast.LENGTH_SHORT).show();
        }
        else if(MyValidation.isEmpty(etLoginPassword.getText().toString()))
        {
            Toast.makeText(this,"Password is Empty...!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            DataHelper dataHelper=new DataHelper(this);
            String email=etLoginEmail.getText().toString();
            String pass=etLoginPassword.getText().toString();
            if(dataHelper.requestForLogin(email,pass))
            {
                Toast.makeText(this,"Login SuccessFull...!",Toast.LENGTH_SHORT).show();
                Intent dashBoardIntent=new Intent(this,DashBoardActivity.class);
                SharedPreferences myPref=getSharedPreferences("myPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = myPref.edit();

                String name=dataHelper.getNameOfUser(email);//get name from DB using email id
                Log.i("User Name:",name);

                editor.putString("name",name);
                editor.commit();

                startActivity(dashBoardIntent);

            }
            else
            {
                Toast.makeText(this,"Email/Password Invalid...!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
