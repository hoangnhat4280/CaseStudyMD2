package model;

public class ResearchPaper extends Document implements Readable {
    private String field; // nghiên cứu
    private String journal; // tên tạp chí

    public ResearchPaper(String title, String author, int year, String field, String journal) {
        super(title, author, year);
        this.field = field;
        this.journal = journal;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    @Override
    public String getSummary() {
        return title + " by " + author + " in the field of " + field;
    }

    @Override
    public String getDetails() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year +
                ", Field: " + field + ", Published in: " + journal;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", field='" + field + '\'' +
                ", journal='" + journal + '\'' +
                '}';
    }
}
