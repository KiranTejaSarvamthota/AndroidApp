package com.example.hthiyaga.kr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kirantejasarvamthota on 4/17/18.
 */

public class SearchByPublicationName extends AppCompatActivity {

    Book[] items;

    ArrayList<Book> listItems;

    PublicationAdapter publicationAdapter;

    ListView listView;

    EditText editText;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_publication_name);

        listView=(ListView)findViewById(R.id.bypublicationlistview);

        editText=(EditText)findViewById(R.id.publication_search);

        initList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent= new Intent(SearchByPublicationName.this, BookDetails.class);

                Book currentBook = listItems.get(i);

                //Create the bundle
                Bundle bundle = new Bundle();

                bundle.putString("BookAuthor", currentBook.getbAuthor());
                bundle.putString("BookName", currentBook.getbName());
                bundle.putString("BookPublication", currentBook.getbPublication());


                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {



            }



            @Override

            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {

                if(s.toString().equals("")){

                    // reset listview

                    initList();

                }

                else{

                    // perform search

                    searchItem(s.toString());

                }

            }



            @Override

            public void afterTextChanged(Editable s) {



            }

        });

    }



    public void searchItem(String textToSearch){

        for(Book item:items){


            Log.i("sarvam1",item.getbPublication());


            if(!item.getbPublication().contains(textToSearch)){

                listItems.remove(item);

            }

        }

        publicationAdapter.notifyDataSetChanged();

    }

    public void initList(){

        items=new Book[]{new Book("Alsop Fred","Birds of North America","NewYorK DK Publication"),new Book("Yorke Malcolm","Beasty Tales","Washington"),new Book("Hageman","Contractors guide to the building code","Craftman book co"),new Book("Johnson james weldon","The Creation illustrated by james","Newyork Holiday house"),new Book("Adams Scott","Dogberts top secret management","Harper Business Newyork"),new Book("BKLA","Familiar Poems","Garden city"),new Book("Gibson Rowan","Rethinking future","London Naperville")};

        listItems=new ArrayList<>(Arrays.asList(items));

        publicationAdapter=new PublicationAdapter(this, listItems);

        listView.setAdapter(publicationAdapter);

    }
}
