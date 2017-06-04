package com.example.android.litepal_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button create_database;
    Button insert;
    Button delete;
    Button update1;
    Button update2;
    Button query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_database = (Button) findViewById(R.id.create_database);
        insert = (Button) findViewById(R.id.insert);
        update1 = (Button) findViewById(R.id.update1);
        update2 = (Button) findViewById(R.id.update2);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //最简单的数据库操作
                Connector.getDatabase();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The First Code");
                book.setAuthor("Da");
                book.setPages(400);
                book.setPrice(16.95);
                book.setPress("who knows");
                book.save();
            }
        });
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("who knows");
                book.save();
                book.setPrice(10.99);
                book.save();
            }
        });
        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");

                //数据更新成默认值
                //book.setToDefault("pages");
                //book.updateAll();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "17");
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                /*
                Book firstBook = DataSupport.findFirst(Book.class);
                Book lastBook = DataSupport.findLast(Book.class);
                List<Book> books = DataSupport.select("name","author").find(Book.class);
                List<Book> books = DataSupport.select("pages > ?","400").find(Book.class);
                List<Book> books = DataSupport.select("price desc").find(Book.class);
                List<Book> books = DataSupport.limit(3).find(Book.class);
                List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);
                List<Book> books = DataSupport.select("name","author","pages")
                        .where("pages > ?","400")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);*/
                for (Book book : books) {
                    Log.d("MainActivity", "book name is " + book.getName());
                    Log.d("MainActivity", "book author is " + book.getAuthor());
                    Log.d("MainActivity", "book pages is " + book.getPages());
                    Log.d("MainActivity", "book price is " + book.getPrice());
                    Log.d("MainActivity", "book press is " + book.getPress());
                }
            }
        });
    }
}
