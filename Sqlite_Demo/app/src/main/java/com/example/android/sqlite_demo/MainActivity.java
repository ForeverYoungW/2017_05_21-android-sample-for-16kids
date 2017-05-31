package com.example.android.sqlite_demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DemoDbHelper dbHelper;

    private Button create;

    private Button insertCourse;
    private Button insertClass;
    private Button insertStudent;
    private Button insertScore;

    private Button update;

    private Button deleteCourse;
    private Button deleteClass;
    private Button deleteStudent;
    private Button deleteScore;

    private Button queryCourse;
    private Button queryClass;
    private Button queryStudent;
    private Button queryScore;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DemoDbHelper(this, "Demo.db", null, 1);
        create = (Button) findViewById(R.id.create);
        insertCourse = (Button) findViewById(R.id.insertCourse);
        insertClass = (Button) findViewById(R.id.insertClass);
        insertStudent = (Button) findViewById(R.id.insertStudent);
        insertScore = (Button) findViewById(R.id.insertScore);

        update = (Button) findViewById(R.id.update);

        deleteCourse = (Button) findViewById(R.id.deleteCourse);
        deleteClass = (Button) findViewById(R.id.deleteClass);
        deleteStudent = (Button) findViewById(R.id.deleteStudent);
        deleteScore = (Button) findViewById(R.id.deleteScore);

        queryCourse = (Button) findViewById(R.id.queryCourse);
        queryClass = (Button) findViewById(R.id.queryClass);
        queryStudent = (Button) findViewById(R.id.queryStudent);
        queryScore = (Button) findViewById(R.id.queryScore);

        create.setOnClickListener(this);
        insertCourse.setOnClickListener(this);
        insertClass.setOnClickListener(this);
        insertStudent.setOnClickListener(this);
        insertScore.setOnClickListener(this);

        update.setOnClickListener(this);

        deleteCourse.setOnClickListener(this);
        deleteClass.setOnClickListener(this);
        deleteStudent.setOnClickListener(this);
        deleteScore.setOnClickListener(this);

        queryCourse.setOnClickListener(this);
        queryClass.setOnClickListener(this);
        queryStudent.setOnClickListener(this);
        queryScore.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.create:
                createDB();
                break;
            case R.id.insertCourse:
                insertCourse();
                break;
            case R.id.insertClass:
                insertClass();
                break;
            case R.id.insertStudent:
                insertStudent();
                break;
            case R.id.insertScore:
                insertScore();
                break;
            case R.id.update:
                updateCourse();
                break;
            case R.id.deleteCourse:
                deleteCourse();
                break;
            case R.id.deleteClass:
                deleteClass();
                break;
            case R.id.deleteStudent:
                deleteStudent();
                break;
            case R.id.deleteScore:
                deleteScore();
                break;
            case R.id.queryCourse:
                queryCourse();
                break;
            case R.id.queryClass:
                queryClass();
                break;
            case R.id.queryStudent:
                queryStudent();
                break;
            case R.id.queryScore:
                queryScore();
                break;
        }
    }

    void createDB() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    void insertCourse() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //组装要插入的数据，这里采用写死的静态数据的方式
        //具体编写代码的时候应该是获取用户的输入作为要插入的数据
        values.put("name", "高等数学");
        db.insert("course", null, values);

        //db.execSQL("insert into course (name) values (?)",new String[]{"线性代数"});
    }

    void insertClass() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "计算机三班");
        db.insert("majorClass", null, values);
    }

    void insertStudent() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "我的天");
        values.put("number", "1402102");
        values.put("majorClassId", 3);
        db.insert("student", null, values);
    }

    void insertScore() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentId", 1);
        values.put("courseId", 2);
        values.put("score", 89);
        db.insert("score", null, values);
    }


    void updateCourse() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", "高等数学我勒个擦");
        //db.update("course", values, "name=?", new String[]{"高等数学"});
        db.execSQL("update course set name = ? where name = ?",new String[]{"高等数学我勒个擦","高等数学"});
    }

    void updateClass() {

    }

    void updateStudent() {

    }

    void updateScore() {

    }

    void deleteCourse() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("course", null, null);
    }

    void deleteClass() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("majorClass", "name=?", new String[]{"计算机三班"});
        //db.execSQL("delete from majorClass where name = ?",new String[]{"计算机二班"});
    }

    void deleteStudent() {

    }

    void deleteScore() {

    }

    void queryCourse() {
        Toast.makeText(this, "queryCourse", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("course", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                d("MainActivity", "courseId: " + id);
                d("MainActivity", "courseName: " + name);
            } while (cursor.moveToNext());
        }
    }

    void queryClass() {
        Toast.makeText(this, "queryClass", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("majorClass", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                d("MainActivity", "classId: " + id);
                d("MainActivity", "className: " + name);
            } while (cursor.moveToNext());
        }
    }

    void queryStudent() {
        Toast.makeText(this, "queryStudent", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("student", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                int classId = cursor.getInt(cursor.getColumnIndex("majorClassId"));
                Log.d("MainActivity", "studentId: " + id);
                Log.d("MainActivity", "studentName: " + name);
                Log.d("MainActivity", "studentNumber: " + number);
                Log.d("MainActivity", "classId: " + classId);
            } while (cursor.moveToNext());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void queryScore() {
        Toast.makeText(this, "queryScore", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String querySql = "select cou.name cName,stu.name sName,score " +
                "from course cou,student stu,score sco " +
                "where sco.studentId=stu.id and sco.courseId=cou.id";
        Cursor cursor = db.rawQuery(querySql, null, null);
        if (cursor.moveToFirst()) {
            do {
                String cName = cursor.getString(cursor.getColumnIndex("cName"));
                String sName = cursor.getString(cursor.getColumnIndex("sName"));
                double score = cursor.getDouble(cursor.getColumnIndex("score"));
                Log.d("MainActivity", "courseName: " + cName);
                Log.d("MainActivity", "studentName: " + sName);
                Log.d("MainActivity", "score: " + score);
            } while (cursor.moveToNext());
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
