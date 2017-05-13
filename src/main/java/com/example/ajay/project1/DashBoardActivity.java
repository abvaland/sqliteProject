package com.example.ajay.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtHeading;
    Button btnAdd,btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        initUIControls();
        registerForListeners();
        SharedPreferences myPref=getSharedPreferences("myPref",MODE_PRIVATE);
        txtHeading.setText("Hello "+myPref.getString("name","empty"));
    }

    private void registerForListeners() {
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    private void initUIControls() {
        txtHeading= (TextView) findViewById(R.id.txtHeading);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        btnShow= (Button) findViewById(R.id.btnShow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAdd:
                Intent addStudentIntent=new Intent(this,AddStudentActivity.class);
                startActivity(addStudentIntent);
                break;

            case R.id.btnShow:
                Intent studentListIntent=new Intent(this,StudentListActivity.class);
                startActivity(studentListIntent);
                break;
        }
    }
}
