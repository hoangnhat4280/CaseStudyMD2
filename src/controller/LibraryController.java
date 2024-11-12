package controller;

import model.Document;
import storage.LibraryStorage;
import java.util.List;

public class LibraryController {
    private static LibraryController instance;
    private LibraryStorage storage;

    private LibraryController() {
        storage = LibraryStorage.getInstance();
    }

    public static LibraryController getInstance() {
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
