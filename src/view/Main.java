package view;

import controller.LibraryController;
import model.Book;
import model.Member;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryController libraryController = LibraryController.getInstance();
        
        Book book1 = new Book("Naruto", "Kakashi", 2008, "2408");
        Book book2 = new Book("Haikyuu", "Shoyo", 2006, "2412");
        libraryController.addBook(book1);
        libraryController.addBook(book2);



        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Hiển thị sách");
            System.out.println("2. Thêm sách");
            System.out.println("3. Sửa sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách");
            System.out.println("6. Sắp xếp sách theo năm");
            System.out.println("7. Lưu dữ liệu");
            System.out.println("8. Thoát");
            System.out.println("9. Thêm thành viên");
            System.out.println("10. Mượn sách cho thành viên");
            System.out.println("11. Trả sách từ thành viên");
            System.out.println("12. Hiển thị thành viên");
            System.out.print("Vui lòng chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    libraryController.displayBooks();
                    break;
                case 2:
                    libraryController.addBookFromInput(scanner);
                    break;
                case 3:
                    libraryController.updateBookFromInput(scanner);
                    break;
                case 4:
                    libraryController.deleteBookFromInput(scanner);
                    break;
                case 5:
                    libraryController.searchBooksFromInput(scanner);
                    break;
                case 6:
                    libraryController.sortBooksByYear();
                    break;
                case 7:
                    libraryController.saveData("libraryData.bin");
                    break;
                case 8:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                case 9:
                    libraryController.addMemberFromInput(scanner);
                    break;
                case 10:
                    libraryController.borrowBookForMember(scanner);
                    break;
                case 11:
                    libraryController.returnBookFromMember(scanner);
                    break;
                case 12:
                    libraryController.displayMembers();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}