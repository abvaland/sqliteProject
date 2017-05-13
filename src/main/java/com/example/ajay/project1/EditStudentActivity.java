package com.example.ajay.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etStudRollnoEdit,etStudNameEdit,etStudStdEdit;
    Button btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        setTitle("Edit Student");

        initUIControls();

        Bundle studBundle=getIntent().getExtras();
        if(studBundle!=null)
        {
            //Log.i("name",studBundle.getString("name","empty"));
            etStudRollnoEdit.setText(studBundle.getInt("rollno",0)+"");
            etStudNameEdit.setText(studBundle.getString("name","empty"));
            etStudStdEdit.setText(studBundle.getString("std","empty"));

            etStudRollnoEdit.setSelection((studBundle.getInt("rollno",0)+"").length());
            etStudNameEdit.setSelection(studBundle.getString("name",null).length());
            etStudStdEdit.setSelection(studBundle.getString("std",null).length());
        }

        registerForListeners();
    }

    private void registerForListeners() {
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void initUIControls() {
        etStudRollnoEdit= (EditText) findViewById(R.id.etStudRollnoEdit);
        etStudNameEdit= (EditText) findViewById(R.id.etStudNameEdit);
        etStudStdEdit= (EditText) findViewById(R.id.etStudStdEdit);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnDelete= (Button) findViewById(R.id.btnDelete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnUpdate:
                if( MyValidation.isEmpty(etStudRollnoEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Rollno is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else if( MyValidation.isEmpty(etStudNameEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Name is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else if( MyValidation.isEmpty(etStudStdEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Std is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    updateStudent();
                }
                break;

            case R.id.btnDelete:
                if( MyValidation.isEmpty(etStudRollnoEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Rollno is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else if( MyValidation.isEmpty(etStudNameEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Name is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else if( MyValidation.isEmpty(etStudStdEdit.getText().toString()) )
                {
                    Toast.makeText(this,"Std is Empty...!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    deleteStudent();
                }
                break;
        }
    }

    private void deleteStudent() {
        Student student=new Student();
        student.setRollno(Integer.parseInt(etStudRollnoEdit.getText().toString()));
        student.setName(etStudNameEdit.getText().toString());
        student.setStd(etStudStdEdit.getText().toString());

        DataHelper dataHelper=new DataHelper(this);
        if( dataHelper.deleteStudent(student)>0 )
        {
            Toast.makeText(this,"Success...!",Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else
        {
            Toast.makeText(this,"Error in Updating...!",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudent() {
        Student student=new Student();
        student.setRollno(Integer.parseInt(etStudRollnoEdit.getText().toString()));
        student.setName(etStudNameEdit.getText().toString());
        student.setStd(etStudStdEdit.getText().toString());

        DataHelper dataHelper=new DataHelper(this);

            if( dataHelper.updateStudent(student)>0 )
            {
                Toast.makeText(this,"Success...!",Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else
            {
                Toast.makeText(this,"Error in Updating...!",Toast.LENGTH_SHORT).show();
            }

    }
}
