package model;

public class BookLoan implements Borrowable {
    private boolean borrowed;

    public BookLoan() {
        this.borrowed = false;
    }

    @Override
    public boolean borrow() {
        if (!borrowed) {
            borrowed = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnItem() {
        if (borrowed) {
            borrowed = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}
