package controller;

import model.Book;
import storage.LibraryStorage;
import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private static LibraryController instance;
    private LibraryStorage storage;

    // Constructor private để ngăn tạo đối tượng từ bên ngoài
    private LibraryController() {
        storage = LibraryStorage.getInstance();
    }

    // Singleton pattern
    public static synchronized LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

    // Thêm sách
    public void addBook(Book book) {
        storage.addBook(book);
    }

    // Hiển thị danh sách sách
    public void displayBooks() {
        List<Book> books = storage.getBooks();
        if (books.isEmpty()) {
            System.out.println("Không có sách nào.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Thêm sách từ đầu vào người dùng
    public void addBookFromInput(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        String author = scanner.nextLine();
        System.out.print("Nhập năm xuất bản: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Clear buffer
        System.out.print("Nhập ISBN: ");
        String isbn = scanner.nextLine();
        Book newBook = new Book(title, author, year, isbn);
        addBook(newBook);
    }

    // Sửa sách từ đầu vào người dùng
    public void updateBookFromInput(Scanner scanner) {
        System.out.print("Nhập ISBN của sách cần sửa: ");
        String isbnToUpdate = scanner.nextLine();
        System.out.print("Nhập tiêu đề mới: ");
        String newTitle = scanner.nextLine();
        System.out.print("Nhập tác giả mới: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Nhập năm xuất bản mới: ");
        int newYear = scanner.nextInt();
        scanner.nextLine();  // Clear buffer
        if (updateBook(isbnToUpdate, newTitle, newAuthor, newYear)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không tìm thấy sách với ISBN này.");
        }
    }

    // Cập nhật thông tin sách
    public boolean updateBook(String isbn, String newTitle, String newAuthor, int newYear) {
        for (Book book : storage.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setYear(newYear);
                return true;
            }
        }
        return false;
    }

    // Xóa sách từ đầu vào người dùng
    public void deleteBookFromInput(Scanner scanner) {
        System.out.print("Nhập ISBN của sách cần xóa: ");
        String isbnToDelete = scanner.nextLine();
        if (deleteBook(isbnToDelete)) {
            System.out.println("Xóa sách thành công!");
        } else {
            System.out.println("Không tìm thấy sách với ISBN này.");
        }
    }

    // Xóa sách
    public boolean deleteBook(String isbn) {
        for (Book book : storage.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                storage.removeBook(book);
                return true;
            }
        }
        return false;
    }

    // Tìm kiếm sách từ đầu vào người dùng
    public void searchBooksFromInput(Scanner scanner) {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine();
        List<Book> searchResults = searchByTitle(keyword);
        System.out.println("\nKết quả tìm kiếm cho từ khóa: \"" + keyword + "\":");
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy sách nào.");
        } else {
            for (Book book : searchResults) {
                System.out.println(book);
            }
        }
    }

    // Tìm kiếm sách theo tiêu đề
    public List<Book> searchByTitle(String title) {
        return storage.searchByTitle(title);
    }

    // Sắp xếp sách theo năm xuất bản
    public void sortBooksByYear() {
        storage.bubbleSortByYear();
        System.out.println("Danh sách sách đã được sắp xếp theo năm.");
    }

    // Lưu dữ liệu vào file
    public void saveData(String filename) {
        storage.saveData(filename);
    }
}
