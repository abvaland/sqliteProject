package com.example.ajay.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ajay on 18-01-2017.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    Activity actContext;
    ArrayList<Student> listStud;
    public StudentAdapter(Activity actContext, ArrayList<Student> listStud) {

    this.actContext=actContext;
    this.listStud=listStud;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item_layout,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Student s=listStud.get(position);
        holder.txtStudRollno.setText(s.getRollno()+"");
        holder.txtStudName.setText(s.getName());
        holder.txtStudStd.setText(s.getStd());

        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s=listStud.get(position);
                Intent editIntent=new Intent(actContext,EditStudentActivity.class);

                Bundle studBundle=new Bundle();
                studBundle.putInt("rollno",s.getRollno());
                studBundle.putString("name",s.getName());
                studBundle.putString("std",s.getStd());
                editIntent.putExtras(studBundle);
                actContext.startActivity(editIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStud.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtStudRollno,txtStudName,txtStudStd;
        ImageButton imgBtnEdit;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtStudRollno= (TextView) itemView.findViewById(R.id.txtRollno);
            txtStudName= (TextView) itemView.findViewById(R.id.txtStudName);
            txtStudStd= (TextView) itemView.findViewById(R.id.txtStudStd);
            imgBtnEdit= (ImageButton) itemView.findViewById(R.id.imgBtnEdit);
        }
    }
}
