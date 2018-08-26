package com.example.hthiyaga.kr;

/**
 * Created by hthiyaga on 4/16/2018.
 */

public class Book {

    // Store the id of the  movie poster
    private String bAuthor;
    // Store the name of the movie
    private String bName;
    // Store the release date of the movie
    private String bPublication;

    // Constructor that is used to create an instance of the Movie object
    public Book(String bAuthor, String bName, String bPublication) {
        this.bAuthor = bAuthor;
        this.bName = bName;
        this.bPublication = bPublication;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String mName) {
        this.bName = bName;
    }

    public String getbPublication() {
        return bPublication;
    }

    public void setbPublication(String bPublication) {
        this.bPublication = bPublication;
    }
}

