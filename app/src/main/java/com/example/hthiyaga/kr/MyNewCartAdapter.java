package com.example.hthiyaga.kr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hthiyaga on 4/17/2018.
 */

public class MyNewCartAdapter extends ArrayAdapter<Book> {

    public Context mContext;
    public List<Book> booksList = new ArrayList<>();
    public CheckBox delete;
    public View rowView;
    WishList wishList;

    public MyNewCartAdapter(@NonNull Context context, ArrayList<Book> list, WishList myWishActivity) {
        super(context,R.layout.cart_list,list);
        mContext = context;
        booksList = list;
        this.wishList= myWishActivity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.cart_list,parent,false);

        Book currentBook = booksList.get(position);

//        TextView author = (TextView) listItem.findViewById(R.id.textView_author);
//        author.setText(currentMovie.getbAuthor());

        TextView name = (TextView) listItem.findViewById(R.id.textView_cartlist);
        name.setText(currentBook.getbName());

        CheckBox delete= (CheckBox)listItem.findViewById(R.id.contactCheck);


        delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Log.d("Teja", String.valueOf(position));
                    ((WishList)mContext).onMethodCallback(position);
                }
            }
        });




        //TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        //release.setText(currentMovie.getbPublication());

        return listItem;
    }
}

