package com.example.ajay.project1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ajay on 17-01-2017.
 */
public class DataHelper {

    Activity actContext;
    public DataHelper(Activity actContext) {
        this.actContext=actContext;
    }

    public boolean isEmailExist(String s) {

        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from user where email=?", new String[]{s});
        boolean exist=(cursor.getCount()>0);
        cursor.close();
        return exist;
    }

    public long insertUsertDetails(User user) {

        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MyTables.User.Name,user.getName());
        values.put(MyTables.User.Email,user.getEmail());
        values.put(MyTables.User.Password,user.getPassword());
        return db.insert(MyTables.User.TableName,null,values);
    }

    public boolean requestForLogin(String email, String pass) {

        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql="select "+MyTables.User.Password+" from "+MyTables.User.TableName+" where "+MyTables.User.Email+"=?";
        String[] args={email};
        Cursor cursor = db.rawQuery(sql, args);

        while (cursor.moveToNext())
        {
            String password=cursor.getString(cursor.getColumnIndex(MyTables.User.Password));
            if(password.equals(pass))
                return true;
        }

        return  false;
    }

    public String getNameOfUser(String email) {
        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql="select "+MyTables.User.Name+" from "+MyTables.User.TableName+" where "+MyTables.User.Email+"=?";
        String[] args={email};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(MyTables.User.Name));

    }

    public long insertStudent(Student s) {
        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(MyTables.Student.RollNo,s.getRollno());
        values.put(MyTables.Student.Name,s.getName());
        values.put(MyTables.Student.Standard,s.getStd());
        return db.insert(MyTables.Student.TableName,null,values);

    }

    public ArrayList<Student> getStudentList() {
        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] columns={MyTables.Student.RollNo,MyTables.Student.Name,MyTables.Student.Standard};
        Cursor cursor = db.query(MyTables.Student.TableName, columns, null, null, null, null, null);

        ArrayList<Student> al=new ArrayList<Student>();
        while (cursor.moveToNext())
        {
            Student s=new Student();
            s.setRollno(cursor.getInt(cursor.getColumnIndex(MyTables.Student.RollNo)));
            s.setName(cursor.getString(cursor.getColumnIndex(MyTables.Student.Name)));
            s.setStd(cursor.getString(cursor.getColumnIndex(MyTables.Student.Standard)));
            al.add(s);
        }

        return al;
    }

    public int updateStudent(Student student) {

        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MyTables.Student.RollNo,student.getRollno());
        values.put(MyTables.Student.Name,student.getName());
        values.put(MyTables.Student.Standard,student.getStd());
        String whereCause=MyTables.Student.RollNo+"=?";
        String[] whereArgs={String.valueOf(student.getRollno())};

        try {
          //  db.execSQL("udapte student set rollno=1,studentName='aaaa' where rollno=1");
           // db.execSQL("update student set rollno='100',studentName='aaaa' where rollno='1'");
           return db.update(MyTables.Student.TableName,values,whereCause,whereArgs);//rollno update not posible bcz we change rollno in update field

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteStudent(Student student) {
        DBHelper dbHelper=new DBHelper(actContext,DBHelper.DbName,null,DBHelper.DBVersion);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereCause=MyTables.Student.RollNo+"=?";
        String[] whereArgs={String.valueOf(student.getRollno())};
        return db.delete(MyTables.Student.TableName,whereCause,whereArgs);
    }
}
