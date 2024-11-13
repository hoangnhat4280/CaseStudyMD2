package storage;

import model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderExample {
    private static final String COMMA_DELIMITER = ","; // Dùng dấu phẩy để phân tách
    private static CsvReaderExample instance;

    private CsvReaderExample() {
    }

    public static CsvReaderExample getInstance() {
        if (instance == null) {
            instance = new CsvReaderExample();
        }
        return instance;
    }

    // Đọc sách từ file CSV
    public List<Book> readBooksFile(String fileName) {
        BufferedReader br = null;
        List<Book> books = new ArrayList<>();
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            // Đọc từng dòng trong file
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split(COMMA_DELIMITER);
                String title = splitData[0];
                String author = splitData[1];
                int year = Integer.parseInt(splitData[2]);
                String isbn = splitData[3];
                Book book = new Book(title, author, year, isbn);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return books;
    }
}
