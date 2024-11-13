package storage;

import model.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriterExample {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    // Phương thức ghi sách vào file CSV
    public void writeBooksFile(String fileName, List<Book> books) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            // Ghi tiêu đề cho file CSV
            fileWriter.append("Title,Author,Year,ISBN");
            fileWriter.append(NEW_LINE_SEPARATOR);
            // Ghi từng sách vào file
            for (Book book : books) {
                fileWriter.append(book.getTitle());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(book.getAuthor());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(book.getYear()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(book.getIsbn());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            System.out.println("Books have been written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error in writing books to CSV file");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }
        }
    }
}
