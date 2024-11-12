package view;

import controller.LibraryController;
import model.Book;
import model.Document;
import storage.LibraryStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhập từ khóa tìm kiếm : ");
        String keyword = scanner.nextLine();

        // getInstance() để lấy các đối tượng Singleton
        LibraryController libraryController = LibraryController.getInstance();
        LibraryStorage libraryStorage = LibraryStorage.getInstance();

        libraryController.addDocument(new Book("Naruto", "Kakashi", 2008, "248-0432236789"));
        libraryController.addDocument(new Book("Haikyuu", "Shoyo", 2006, "248-0236594728"));
        libraryController.addDocument(new Book("One Piece", "Oda", 1999, "248-0336338392"));

        libraryStorage.bubbleSortByYear();

        System.out.println("Danh sách tài liệu sau khi sắp xếp theo năm:");
        for (Document doc : libraryStorage.searchByTitle("")) {
            System.out.println(doc.getTitle() + " - " + doc.getYear());
        }

        List<Document> searchResults = libraryStorage.searchByTitle(keyword);
        System.out.println("\nKết quả tìm kiếm cho từ khóa: \"" + keyword + "\":");
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy tài liệu nào.");
        } else {
            for (Document doc : searchResults) {
                System.out.println(doc.getTitle() + " - " + doc.getYear());
            }
        }

        libraryStorage.saveData("libraryData.bin");

        scanner.close();
    }
}
