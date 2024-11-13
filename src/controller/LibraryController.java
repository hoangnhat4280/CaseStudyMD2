package controller;

import model.Book;
import model.Member;
import model.LoanRecord;
import storage.LibraryStorage;
import storage.CsvReaderExample;
import storage.CsvWriterExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LibraryController {
    private static LibraryController instance;
    private LibraryStorage storage;
    private List<Member> members; // Danh sách thành viên
    private List<LoanRecord> loanRecords; // Danh sách bản ghi mượn sách


    // Constructor private để ngăn tạo đối tượng từ bên ngoài
    private LibraryController() {
        storage = LibraryStorage.getInstance();
        members = new ArrayList<>();
        loanRecords = new ArrayList<>();
    }

    // Singleton pattern
    public static synchronized LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

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

    // Thêm sách
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

    // Sửa sách
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
    public void addMemberFromInput(Scanner scanner) {
        System.out.print("Nhập ID thành viên: ");
        String memberId = scanner.nextLine();
        System.out.print("Nhập tên thành viên: ");
        String name = scanner.nextLine();
        Member newMember = new Member(memberId, name);
        members.add(newMember);
        System.out.println("Thành viên đã được thêm thành công!");
    }
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Không có thành viên nào.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    // Mượn sách cho thành viên
    public void borrowBookForMember(Scanner scanner) {
        System.out.print("Nhập ID thành viên: ");
        String memberId = scanner.nextLine();
        System.out.print("Nhập ISBN sách: ");
        String isbn = scanner.nextLine();
        Member member = findMemberById(memberId);
        if (member != null) {
            for (Book book : storage.getBooks()) {
                if (book.getIsbn().equals(isbn) && !book.getLoanStatus().isBorrowed()) {
                    member.borrowBook(book);
                    loanRecords.add(new LoanRecord(member, book));
                    book.getLoanStatus().borrow();
                    System.out.println("Sách đã được mượn thành công cho thành viên: " + member.getName());
                    return;
                }
            }
            System.out.println("Sách không có sẵn hoặc đã được mượn.");
        } else {
            System.out.println("Không tìm thấy thành viên với ID này.");
        }
    }

    // Trả sách từ thành viên
    public void returnBookFromMember(Scanner scanner) {
        System.out.print("Nhập ID thành viên: ");
        String memberId = scanner.nextLine();
        System.out.print("Nhập ISBN sách: ");
        String isbn = scanner.nextLine();
        Member member = findMemberById(memberId);
        if (member != null) {
            for (Book book : member.getBorrowedBooks()) {
                if (book.getIsbn().equals(isbn)) {
                    member.returnBook(book);
                    book.getLoanStatus().returnItem();
                    System.out.println("Sách đã được trả thành công.");
                    return;
                }
            }
            System.out.println("Thành viên này không mượn sách này.");
        } else {
            System.out.println("Không tìm thấy thành viên với ID này.");
        }
    }

    // Tìm thành viên theo ID
    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
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

    // Mượn sách
    public void borrowBook(String isbn) {
        for (Book book : storage.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getLoanStatus().borrow()) {
                    System.out.println("Sách đã được mượn thành công.");
                    return;
                } else {
                    System.out.println("Sách đã được mượn trước đó.");
                    return;
                }
            }
        }
        System.out.println("Không tìm thấy sách với ISBN này.");
    }

    // Trả sách
    public void returnBook(String isbn) {
        for (Book book : storage.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getLoanStatus().returnItem()) {
                    System.out.println("Sách đã được trả thành công.");
                    return;
                } else {
                    System.out.println("Sách chưa được mượn.");
                    return;
                }
            }
        }
        System.out.println("Không tìm thấy sách với ISBN này.");
    }


    // Phương thức để load sách từ file CSV
    public void loadBooksFromCsv(String fileName) {
        CsvReaderExample csvReader = CsvReaderExample.getInstance();
        List<Book> books = csvReader.readBooksFile(fileName);
        // Thêm sách vào storage sau khi đã đọc từ CSV
        for (Book book : books) {
            storage.addBook(book);
        }
        System.out.println("Sách đã được tải từ file " + fileName);
    }

    // Phương thức để lưu sách vào file CSV
    public void saveBooksData(String fileName) {
        CsvWriterExample csvWriter = new CsvWriterExample();
        List<Book> books = storage.getBooks();
        csvWriter.writeBooksFile(fileName, books);
    }


}







