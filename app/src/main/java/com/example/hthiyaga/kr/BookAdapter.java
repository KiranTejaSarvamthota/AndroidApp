package com.example.hthiyaga.kr;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hthiyaga on 4/16/2018.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public Context mContext;
    public List<Book> booksList = new ArrayList<>();
    public BookAdapter(@NonNull Context context, @LayoutRes ArrayList<Book> list) {
        super(context, 0 , list);
        mContext = context;
        booksList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Book currentBook = booksList.get(position);

        //TextView author = (TextView) listItem.findViewById(R.id.textView_author);
        //author.setText(currentMovie.getbAuthor());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentBook.getbName());

        //TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        //release.setText(currentMovie.getbPublication());

        return listItem;
    }
}

