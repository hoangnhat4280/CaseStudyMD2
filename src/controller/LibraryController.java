package controller;

import model.Document;
import storage.LibraryStorage;
import java.util.List;

public class LibraryController {
    private static LibraryController instance;
    private LibraryStorage storage;

    // Constructor private để ngăn tạo đối tượng từ bên ngoài
    private LibraryController() {
        storage = LibraryStorage.getInstance();
    }

    // Synchronized để đảm bảo chỉ có một instance trong môi trường đa luồng
    public static synchronized LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

    public void addDocument(Document document) {
        storage.addDocument(document);
    }

    public List<Document> searchByTitle(String title) {
        return storage.searchByTitle(title);
    }
}

