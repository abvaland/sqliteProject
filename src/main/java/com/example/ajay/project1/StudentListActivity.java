package com.example.ajay.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    RecyclerView rvStudList;
    LinearLayoutManager linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle("Student List");
        linearLayout=new LinearLayoutManager(this);
        initUIControls();
        bindStudentList();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bindStudentList();
    }

    private void bindStudentList() {
        DataHelper dataHelper=new DataHelper(this);
        ArrayList<Student> listStud=dataHelper.getStudentList();
//        Log.i("Student", String.valueOf(listStud.get(0)));
        rvStudList.setLayoutManager(linearLayout);
        StudentAdapter studAdapter=new StudentAdapter(this,listStud);

        rvStudList.setAdapter(studAdapter);
    }

    private void initUIControls() {
        rvStudList= (RecyclerView) findViewById(R.id.rvStudList);
    }
}
