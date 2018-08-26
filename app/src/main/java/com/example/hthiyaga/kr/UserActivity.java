package com.example.hthiyaga.kr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        private ListView listView;
        private BookAdapter mAdapter;
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.Navigation_V);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.movies_list);
        final ArrayList<Book> booksList = new ArrayList<>();
        booksList.add(new Book("Alsop Fred","Birds of North America","NewYorK DK Publication"));
        booksList.add(new Book("Yorke Malcolm","Beasty Tales","NewYork Sony Wonder,c1997"));
        booksList.add(new Book("Hageman","Contractors guide to the building code","Craftman book co"));
        booksList.add(new Book("Johnson james weldon","The Creation illustrated by james","Newyork Holiday house"));
        booksList.add(new Book("Adams Scott","Dogberts top secret management","Harper Business Newyork"));
        booksList.add(new Book("Royston Angela","fire fighter","Green Haven press"));
        booksList.add(new Book("BKLA","Familiar Poems","Garden city"));
        booksList.add(new Book("Jeffry A","Global Capitalism","New York"));
        booksList.add(new Book("Winter Jeanette","Hey diddle diddle","Redwagon books"));
        booksList.add(new Book("Gibson Rowan","Rethinking future","London Naperville"));
        booksList.add(new Book("Lesy Micheal","Wisconsin death trip","University of new mexico press"));


        mAdapter = new BookAdapter(this,booksList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent= new Intent(UserActivity.this, BookDetails.class);

                Book currentBook = booksList.get(i);

                //Create the bundle
                Bundle bundle = new Bundle();

                bundle.putString("BookAuthor", currentBook.getbAuthor());
                bundle.putString("BookName", currentBook.getbName());
                bundle.putString("BookPublication", currentBook.getbPublication());


                intent.putExtras(bundle);



                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.SearchByBook:
                Intent intentA = new Intent(this,SearchByBookName.class);
                startActivity(intentA);

                Toast.makeText(this,"Search by Book is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SearchByAuthor:

                Intent intentB = new Intent(this,SearchByAuthorName.class);
                startActivity(intentB);
                Toast.makeText(this,"Search by Author is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.SearchByGenre:
                Intent intentC = new Intent(this,SearchByPublicationName.class);
                startActivity(intentC);
                Toast.makeText(this,"Search by Genre is selected",Toast.LENGTH_SHORT).show();
                break;
//            default:
//                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch(item.getItemId()){

            case R.id.home:

                Toast.makeText(this,"This is home",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,UserActivity.class));

                break;
            case R.id.my_cart:

                startActivity(new Intent(this,MyCart.class));

        }

       // if(id==R.id.home){
       //     Toast.makeText(this,"This is home",Toast.LENGTH_LONG).show();
       // }


        if(id==R.id.setting){
            Toast.makeText(this,"This is setting",Toast.LENGTH_LONG).show();
        }



        if(id==R.id.log){
            Toast.makeText(this,"Logged out successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        if(id==R.id.wishList){
            Toast.makeText(this,"This is Wishlist",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,WishList.class);
            startActivity(intent);
        }



        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }
}
