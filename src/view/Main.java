package view;

import controller.LibraryController;
import model.Book;
import model.Document;
import storage.LibraryStorage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LibraryController libraryController = LibraryController.getInstance();
        LibraryStorage libraryStorage = LibraryStorage.getInstance();

        libraryController.addDocument(new Book("Naruto", "Kakashi", 2008, "978-0134685991"));
        libraryController.addDocument(new Book("Haikyuu", "Shoyo", 2006, "978-0321349606"));

        libraryStorage.bubbleSortByYear();

        System.out.println("Danh sách tài liệu sau khi sắp xếp theo năm:");
        for (Document doc : libraryStorage.searchByTitle("")) {
            System.out.println(doc.getTitle() + " - " + doc.getYear());
        }

        // Lưu vào tệp nhị phân
        try {
            libraryStorage.saveToBinaryFile("libraryData.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
