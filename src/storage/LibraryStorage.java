package storage;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LibraryStorage {
    private static LibraryStorage instance;
    private List<Book> books;

    private LibraryStorage() {
        books = new ArrayList<>();
    }

    public static synchronized LibraryStorage getInstance() {
        if (instance == null) {
            instance = new LibraryStorage();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(title, Pattern.CASE_INSENSITIVE);
        for (Book book : books) {
            if (pattern.matcher(book.getTitle()).find()) {
                result.add(book);
            }
        }
        return result;
    }

    // Thuật toán sắp xếp nổi bọt theo năm xuất bản
    public void bubbleSortByYear() {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).getYear() > books.get(j + 1).getYear()) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    // Lưu dữ liệu vào file nhị phân
    public void saveToBinaryFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
        }
    }

    public void saveData(String filename) {
        try {
            saveToBinaryFile(filename);
            System.out.println("Data has been saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }


    // Tải dữ liệu từ file nhị phân
//    public void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            books = (List<Book>) ois.readObject();
//        }
//    }
}
