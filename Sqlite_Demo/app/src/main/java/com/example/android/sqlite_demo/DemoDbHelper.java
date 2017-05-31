package com.example.android.sqlite_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wkp on 2017/5/29.
 */

public class DemoDbHelper extends SQLiteOpenHelper{

    private Context mContext;

    //四条建表语句
    public static final String CREATE_CLASS = "create table majorClass ("
            + "id integer primary key autoincrement, "
            + "name text unique)";

    public static final String CREATE_COURSE = "create table course ("
            + "id integer primary key autoincrement, "
            + "name text unique)";

    public static final String CREATE_STUDENT = "create table student ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "number text unique, "
            + "majorClassId integer, "
            + "foreign key(majorClassId) references majorClass(id) on delete cascade)";

    public static final String CREATE_SCORE = "create table score ("
            + "id integer primary key autoincrement, "
            + "studentId integer, "
            + "courseId integer, "
            + "score real, "
            + "foreign key(studentId) references student(id) on delete cascade, "
            + "foreign key(courseId) references course(id) on delete cascade)";

    public DemoDbHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
        mContext=context;
    }

    //实现创建数据库的逻辑
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLASS);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_SCORE);
        Toast.makeText(mContext,"Create db succeeded",Toast.LENGTH_SHORT).show();
    }

    //实现升级数据库的逻辑
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists course");
        db.execSQL("drop table if exists majorClass");
        db.execSQL("drop table if exists student");
        db.execSQL("drop table if exists score");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("pragma foreign_keys = ON;");
    }
}
