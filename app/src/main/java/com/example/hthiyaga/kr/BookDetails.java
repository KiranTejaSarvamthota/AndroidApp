package com.example.hthiyaga.kr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kirantejasarvamthota on 3/26/18.
 */


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by kirantejasarvamthota on 3/12/18.
 */

public class BookDetails extends AppCompatActivity {

    TextView bookname;
    TextView bookauthor;
    TextView bookpublication;
    FloatingActionButton fab;
    public Book newBook;
    Button wishlist;


    public static ArrayList<Book> bookList = new ArrayList<Book>();
    public static ArrayList<Book> wishbookList = new ArrayList<Book>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        bookname= (TextView)findViewById(R.id.bookname);
        bookauthor= (TextView)findViewById(R.id.bookauthor);
        bookpublication= (TextView)findViewById(R.id.bookpublication);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        wishlist = (Button) findViewById(R.id.wish);

        Bundle bundle = getIntent().getExtras();

        final String BookAuthor = bundle.getString("BookAuthor",null);

        final String BookName = bundle.getString("BookName",null);

        final String BookPublication = bundle.getString("BookPublication",null);


        bookname.setText(BookName);
        bookauthor.setText(BookAuthor);
        bookpublication.setText(BookPublication);
        fab.setOnClickListener(new View.OnClickListener() {
            String path = "";
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(BookDetails.this, MyCart.class);
                Bundle bundle = new Bundle();


                bundle.putString("BookName1",BookName);


                intent.putExtras(bundle);
                startActivity(intent);
                //startActivity(new Intent(BookDetails.this,MyCart.class));
                newBook = new Book(BookAuthor,BookName,BookPublication);
                wishbookList.add(newBook);

                try {
                    File file = new File(Environment.getExternalStorageDirectory()+"/Music/Data1.txt");
                    path = file.getAbsolutePath();
                    ObjectMapper mapper=new ObjectMapper();
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(mapper.writeValueAsString(wishbookList).getBytes());
                    outputStream.close();
                }
                catch (Throwable t) {
                    t.getStackTrace();
                }



                ////////////////////





            }
        });


        wishlist.setOnClickListener(new View.OnClickListener() {

            String pathwish = "";
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetails.this,WishList.class);
                Bundle bundle = new Bundle();

                bundle.putString("BookName1", BookName);
                intent.putExtras(bundle);
                startActivity(intent);
                newBook = new Book(BookAuthor,BookName,BookPublication);
                bookList.add(newBook);

                try {
                    File file1 = new File(Environment.getExternalStorageDirectory()+"/Music/Data2.txt");
                    pathwish = file1.getAbsolutePath();
                    ObjectMapper mapper1=new ObjectMapper();
                    FileOutputStream outputStream = new FileOutputStream(file1);
                    outputStream.write(mapper1.writeValueAsString(bookList).getBytes());
                    outputStream.close();
                }
                catch (Throwable t) {
                    t.getStackTrace();
                }
            }
        });

        //Get the bundle


//Extract the data…




    }
}

