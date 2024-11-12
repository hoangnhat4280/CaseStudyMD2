package model;

public class Book extends Document {
    private String isbn;

    public Book(String title, String author, int year, String isbn) {
        super(title, author, year);
        this.isbn = isbn;
    }

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
