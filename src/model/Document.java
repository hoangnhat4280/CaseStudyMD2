package model;

import java.io.Serializable;

public abstract class Document implements Serializable {
    protected String title;
    protected String author;
    protected int year;

    public Document(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}