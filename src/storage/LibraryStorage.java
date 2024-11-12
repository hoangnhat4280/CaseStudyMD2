package storage;

import model.Document;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LibraryStorage {
    private static LibraryStorage instance;
    private List<Document> documents;

    private LibraryStorage() {
        documents = new ArrayList<>();
    }

    public static synchronized LibraryStorage getInstance() {
        if (instance == null) {
            instance = new LibraryStorage();
        }
        return instance;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public List<Document> searchByTitle(String title) {
        List<Document> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(title, Pattern.CASE_INSENSITIVE);
        for (Document doc : documents) {
            if (pattern.matcher(doc.getTitle()).find()) {
                result.add(doc);
            }
        }
        return result;
    }

    // Thuật toán sắp xếp nổi bọt theo năm xuất bản
    public void bubbleSortByYear() {
        for (int i = 0; i < documents.size() - 1; i++) {
            for (int j = 0; j < documents.size() - i - 1; j++) {
                if (documents.get(j).getYear() > documents.get(j + 1).getYear()) {
                    Document temp = documents.get(j);
                    documents.set(j, documents.get(j + 1));
                    documents.set(j + 1, temp);
                }
            }
        }
    }

    public void saveToBinaryFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(documents);
        }
    }

    // Phương thức mới để lưu dữ liệu với xử lý ngoại lệ
    public void saveData(String filename) {
        try {
            saveToBinaryFile(filename);
            System.out.println("Data has save to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            documents = (List<Document>) ois.readObject();
        }
    }
}
