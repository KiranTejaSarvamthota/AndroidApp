package com.example.hthiyaga.kr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by hthiyaga on 4/16/2018.
 */

/**
 * Created by hthiyaga on 4/16/2018.
 */

public class WishList extends AppCompatActivity {

    private ListView listView1;
    private MyNewCartAdapter myNewCartAdapter;
    public static String namebook = "";
    public static String nameauthor = "";
    public static String namepublication = "";
    public static ArrayList<Integer> positionList = new ArrayList<Integer>();
    public static ArrayList<Book> bookList;
    public Button delete,add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);
        delete= (Button) findViewById(R.id.deleteWishBook);
        add = (Button) findViewById(R.id.addWishBook);
        listView1 = (ListView) findViewById(R.id.wish_listview);
        //final ArrayList<Cart> moviesList = new ArrayList<>();
        //public static ArrayList<Movie> movieList = new ArrayList<Movie>();
        //Log.d("Sar1",String.valueOf(movieList.size()));
        parseJSONFile();
        Log.d("Sar",String.valueOf(bookList.size()));
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String BookName1 = bundle.getString("BookName1", null);
            //moviesList.add(BookName);

            Log.d("here", "ggg");
            //moviesList.add(new Cart(BookName1));

            myNewCartAdapter = new MyNewCartAdapter(this, bookList,WishList.this);
            listView1.setAdapter(myNewCartAdapter);
            Log.d("here", "SSS");
        }

        else{

            //String Bookname1="";
            //movieList.add(new Movie(null,null,null));
            myNewCartAdapter = new MyNewCartAdapter(this, bookList, WishList.this);
            listView1.setAdapter(myNewCartAdapter);

        }
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = 0 ; i < positionList.size() ; i++){
                    bookList.remove(i);
//                    mainAdapter.notifyDataSetChanged();
                    myNewCartAdapter = new MyNewCartAdapter(WishList.this, bookList,WishList.this);
                    listView1 = (ListView) findViewById(R.id.wish_listview);
                    listView1.setAdapter(myNewCartAdapter);
                    updateFile();
                    Toast.makeText(getApplicationContext(),"Removed successfully from the cart",Toast.LENGTH_SHORT).show();
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WishList.this,UserActivity.class);
                startActivity(intent);

            }
        });

    }

    public static void parseJSONFile(){
        Log.d(" here", "Kiran");
        bookList = new ArrayList<Book>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(Environment.getExternalStorageDirectory()+"/Music/Data2.txt"));
            for (Object o : a) {
                JSONObject contact = (JSONObject) o;

                String namebook = (String) contact.get("bName");
                Log.d("Name:", namebook);

                String nameauthor = (String) contact.get("bAuthor");
                Log.d("Aut:", nameauthor);

                String namepublication = (String) contact.get("bPublication");
                Log.d("Publication:", namepublication);

                ArrayList<String> relsList = new ArrayList<String>();
                relsList = (ArrayList<String>)contact.get("relationshipList");
                if (bookList.size() == 0 ) {
                    bookList.add(new Book(nameauthor, namebook,namepublication));
                    Log.d("here","ss1");
                }else if(generateContactNames(bookList).contains(namebook)) {
                    Log.d("here","ss2");
                    continue;

                }
                else{
                    bookList.add(new Book(nameauthor, namebook,namepublication));
                    Log.d("here","ss3");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> generateContactNames(ArrayList<Book> books){
        ArrayList<String> nameList = new ArrayList<String>();

        for (Book data : books) {
            nameList.add(data.getbName());
        }
        return nameList;
    }

    public void onMethodCallback(int i) {
        positionList.add(i);
    }

    public static void updateFile(){

        try {
            File file1 = new File(Environment.getExternalStorageDirectory()+"/Music/Data2.txt");
            ObjectMapper mapper1=new ObjectMapper();
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(mapper1.writeValueAsString(bookList).getBytes());
            outputStream.close();
        }
        catch (Throwable t) {
            t.getStackTrace();
        }
    }


}


