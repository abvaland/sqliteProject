package com.example.ajay.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etEmail,etPassword;
    Button btnRegister;
    TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Registration Screen");
        initUIControls();
        registerForListeners();
    }

    private void registerForListeners() {
        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    private void initUIControls() {
        etName= (EditText) findViewById(R.id.etName);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnRegister= (Button) findViewById(R.id.btnRegister);
        txtLogin= (TextView) findViewById(R.id.txtLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRegister:
                //validate name,email and password...
                if(MyValidation.isEmpty(etName.getText().toString()))
                {
                    Toast.makeText(this,"Name is Empty...!",Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                }
                else if(MyValidation.isEmpty(etEmail.getText().toString()) || !MyValidation.isValidEmail(etEmail.getText().toString()))
                {
                    Toast.makeText(this,"Enter Currect Email Id",Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                }
                else if (MyValidation.isEmpty(etPassword.getText().toString()) || !MyValidation.isValidPassword(etPassword.getText().toString()))
                {
                    Toast.makeText(this,"Enter Valid Password(Min 6 digit)",Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                }
                else
                {
                   // Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();

                    DataHelper dataHelper=new DataHelper(this);
                    boolean b=dataHelper.isEmailExist(etEmail.getText().toString());
                    if(b)
                    {
                        Toast.makeText(this,"Email Address Already Exist...",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        User user=new User();
                        user.setName(etName.getText().toString());
                        user.setEmail(etEmail.getText().toString());
                        user.setPassword(etPassword.getText().toString());

                        if(dataHelper.insertUsertDetails(user)>0)
                        {
                            Toast.makeText(this,"SuccessFully Register...!",Toast.LENGTH_SHORT).show();
                            SharedPreferences myPref=getSharedPreferences("myPref",MODE_PRIVATE);
                            SharedPreferences.Editor editor=myPref.edit();
                            editor.putString("name",user.getName());
                            editor.putString("email",user.getEmail());
                            editor.putString("password",user.getPassword());
                            editor.commit();

                            Intent dashBoardIntent=new Intent(this,DashBoardActivity.class);
                            startActivity(dashBoardIntent);
                        }
                        else
                        {
                            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;

            case R.id.txtLogin:
                Intent loginIntent=new Intent(this,LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
    }
}
