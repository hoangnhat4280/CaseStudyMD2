package model;

import java.time.LocalDate;

public class Newspaper extends Document {
    private LocalDate publicationDate;

    public Newspaper(String title, String author, int year, LocalDate publicationDate) {
        super(title, author, year);
        this.publicationDate = publicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
