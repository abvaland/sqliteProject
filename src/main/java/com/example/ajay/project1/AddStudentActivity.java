package com.example.ajay.project1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtNameOfUser;
    ImageButton imgBtnBack;
    EditText etStudRoll,etStudName,etStudStandard;
    Button btnAddStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initUIControls();
        SharedPreferences myPref=getSharedPreferences("myPref",MODE_PRIVATE);
        txtNameOfUser.setText("Hello "+myPref.getString("name","empty"));

        registerForListeners();
    }

    private void registerForListeners() {
        imgBtnBack.setOnClickListener(this);
        btnAddStudent.setOnClickListener(this);
    }

    private void initUIControls() {
        txtNameOfUser= (TextView) findViewById(R.id.txtNameOfUser);
        imgBtnBack= (ImageButton) findViewById(R.id.imgBtnBack);
        etStudRoll= (EditText) findViewById(R.id.etStudRollno);
        etStudName= (EditText) findViewById(R.id.etStudName);
        etStudStandard= (EditText) findViewById(R.id.etStudStandard);
        btnAddStudent= (Button) findViewById(R.id.btnAddStudent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAddStudent:
                if(MyValidation.isEmpty(etStudRoll.getText().toString()))
                {
                    Toast.makeText(this,"Rollno is Empty....!",Toast.LENGTH_SHORT).show();
                    etStudRoll.requestFocus();
                }
                else if(MyValidation.isEmpty(etStudName.getText().toString()))
                {
                    Toast.makeText(this,"Name is Empty....!",Toast.LENGTH_SHORT).show();
                    etStudName.requestFocus();
                }
                else if(MyValidation.isEmpty(etStudStandard.getText().toString()))
                {
                    Toast.makeText(this,"Std is Empty....!",Toast.LENGTH_SHORT).show();
                    etStudStandard.requestFocus();
                }
                else
                {

                        insertStudentRecord();
                }
                break;

            case R.id.imgBtnBack:
                this.finish();
                break;
        }
    }

    private void insertStudentRecord() {

        Student s=new Student();
        s.setRollno(Integer.parseInt(etStudRoll.getText().toString()));
        s.setName(etStudName.getText().toString());
        s.setStd(etStudStandard.getText().toString());

        DataHelper dataHelper=new DataHelper(this);
       if( dataHelper.insertStudent(s)>0 )
       {
            Toast.makeText(this,"Suceess....!",Toast.LENGTH_SHORT).show();
            this.finish();
       }
        else
       {
            Toast.makeText(this,"Error....!",Toast.LENGTH_SHORT).show();
       }
    }
}
