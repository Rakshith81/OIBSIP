import java.util.*;

public class DigitalLibraryManagement {
    private HashMap<String, Integer> userCredentials = new HashMap<>();
    private HashMap<Integer, String> bookDB = new HashMap<>();
    private static String currentUserId;
    private static int issuedBookNumber = 0;
    private Scanner sc = new Scanner(System.in);

    public DigitalLibraryManagement() {
        // Initialize books only once
        bookDB.put(1, "EE : Electric Machine-1");
        bookDB.put(2, "EE : Electric Machine-2");
        bookDB.put(3, "EE : Electric Drive");
        bookDB.put(4, "EE : Power System-1");
        bookDB.put(5, "EE : Power System-2");
        bookDB.put(6, "EE : Basic Electrical");
        bookDB.put(7, "EE : Power Electronics");
        bookDB.put(8, "ECE : Basic Electronics");
        bookDB.put(9, "ECE : Microprocessor & Microcontroller");
        bookDB.put(10, "ECE : Analog Electronics");
        bookDB.put(11, "ECE : Digital Electronics");
        bookDB.put(12, "CSE : C Programming");
        bookDB.put(13, "CSE : Java Programming");
        bookDB.put(14, "CSE : Artificial Intelligence");
        bookDB.put(15, "CSE : Object Oriented Programming (OOPs)");
        bookDB.put(16, "IT : Data Structures & Algorithms");
        bookDB.put(17, "IT : Databases - Networks");
        bookDB.put(18, "IT : Statistics");
        bookDB.put(19, "BBA : Principle of Management");
        bookDB.put(20, "BBA : Economics for Engineers");
        bookDB.put(21, "MATHS : Engineering Mathematics-1");
        bookDB.put(22, "MATHS : Engineering Mathematics-2");
        bookDB.put(23, "MATHS : Engineering Mathematics-3");
        bookDB.put(24, "PHYS : Physics");
        bookDB.put(25, "CHEM : Chemistry");
        bookDB.put(26, "ENG : English");
        bookDB.put(27, "BENG : Bengali");
        bookDB.put(28, "HIND : Hindi");
        bookDB.put(29, "EE : Control System");
        bookDB.put(30, "EE : Electrical Circuit Theory");
        bookDB.put(31, "EE : Electric & Hybrid Vehicle");
        bookDB.put(32, "EE : Renewable Energy Sources");

        // Initialize users only once
        userCredentials.put("Rakshith", 13579);
        userCredentials.put("Rakshithdip", 20026);
        userCredentials.put("Rakx", 12345);
        userCredentials.put("SPal", 24680);
        userCredentials.put("Dip", 11111);
    }

    public void homepage() {
        System.out.println("--------------------------------------------------------");
        System.out.println("----- WELCOME TO DIGITAL LIBRARY MANAGEMENT SYSTEM -----");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exit");
        System.out.print("Select Option: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                adminLogin();
                break;
            case 2:
                userLogin();
                break;
            case 3:
                System.out.print("Do You want to Exit? (Y/N): ");
                String exitChoice = sc.next();
                if (exitChoice.equalsIgnoreCase("Y")) {
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                } else {
                    homepage();
                }
                break;
            default:
                System.out.println("Invalid Option. Try again.");
                homepage();
        }
    }

    public void adminLogin() {
        System.out.println("------ ADMIN LOGIN ------");
        System.out.print("Enter User ID: ");
        currentUserId = sc.next();
        System.out.print("Enter Password: ");
        int password = sc.nextInt();

        // Only allow admin users
        if ((currentUserId.equals("Rakshith") || currentUserId.equals("Rakshithdip"))
                && userCredentials.containsKey(currentUserId)
                && userCredentials.get(currentUserId) == password) {
            System.out.println("Login Successful!");
            adminMainPage();
        } else {
            System.out.println("Invalid credentials. Try again.");
            adminLogin();
        }
    }

    public void userLogin() {
        System.out.println("------ USER LOGIN ------");
        System.out.print("Enter User ID: ");
        currentUserId = sc.next();
        System.out.print("Enter Password: ");
        int password = sc.nextInt();

        if (userCredentials.containsKey(currentUserId) && userCredentials.get(currentUserId) == password) {
            System.out.println("Login Successful!");
            userMainPage();
        } else {
            System.out.println("Invalid credentials. Try again.");
            userLogin();
        }
    }

    public void adminMainPage() {
        System.out.println("------ ADMIN MAINPAGE ------");
        System.out.println("Welcome Admin: " + currentUserId);
        System.out.println("1. Add New Book");
        System.out.println("2. Update Existing Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Go to User Mainpage");
        System.out.println("5. Logout");
        System.out.print("Select Option: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                addBook();
                adminMainPage();
                break;
            case 2:
                updateBook();
                adminMainPage();
                break;
            case 3:
                deleteBook();
                adminMainPage();
                break;
            case 4:
                userMainPage();
                break;
            case 5:
                homepage();
                break;
            default:
                System.out.println("Invalid Option.");
                adminMainPage();
        }
    }

    public void userMainPage() {
        System.out.println("------ USER MAINPAGE ------");
        System.out.println("Welcome User: " + currentUserId);
        System.out.println("1. View Book");
        System.out.println("2. Issue Book");
        System.out.println("3. Return Book");
        System.out.println("4. Logout");
        System.out.print("Select Option: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                viewBooks();
                break;
            case 2:
                issueBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                homepage();
                break;
            default:
                System.out.println("Invalid Option.");
                userMainPage();
        }
    }

    public void addBook() {
        System.out.print("Enter New Book Number: ");
        int bookNumber = sc.nextInt();
        sc.nextLine();
        if (bookDB.containsKey(bookNumber)) {
            System.out.println("Book number already exists.");
        } else if (bookNumber <= 0) {
            System.out.println("Invalid book number.");
        } else {
            System.out.print("Enter Book Details: ");
            String details = sc.nextLine();
            bookDB.put(bookNumber, details);
            System.out.println("Book added successfully.");
        }
    }

    public void updateBook() {
        System.out.print("Enter Book Number to Update: ");
        int bookNumber = sc.nextInt();
        sc.nextLine();
        if (bookDB.containsKey(bookNumber)) {
            System.out.println("Current Details: " + bookDB.get(bookNumber));
            System.out.print("Enter New Details: ");
            String newDetails = sc.nextLine();
            bookDB.put(bookNumber, newDetails);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void deleteBook() {
        System.out.print("Enter Book Number to Delete: ");
        int bookNumber = sc.nextInt();
        if (bookDB.containsKey(bookNumber)) {
            System.out.println("Deleting: " + bookDB.get(bookNumber));
            System.out.print("Confirm? (Y/N): ");
            String confirm = sc.next();
            if (confirm.equalsIgnoreCase("Y")) {
                bookDB.remove(bookNumber);
                System.out.println("Book deleted.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void viewBooks() {
        System.out.println("1. View Specific Book");
        System.out.println("2. View All Books");
        System.out.println("3. Back to User Mainpage");
        System.out.print("Choose Option: ");
        int viewOption = sc.nextInt();
        switch (viewOption) {
            case 1:
                System.out.print("Enter Book Number: ");
                int bookNo = sc.nextInt();
                if (bookDB.containsKey(bookNo)) {
                    System.out.println(bookNo + " --> " + bookDB.get(bookNo));
                } else {
                    System.out.println("Book not found.");
                }
                viewBooks();
                break;
            case 2:
                for (Map.Entry<Integer, String> entry : bookDB.entrySet()) {
                    System.out.println(entry.getKey() + " --> " + entry.getValue());
                }
                viewBooks();
                break;
            case 3:
                userMainPage();
                break;
            default:
                System.out.println("Invalid option.");
                viewBooks();
        }
    }

    public void issueBook() {
        System.out.print("Enter Book Number to Issue: ");
        int bookToIssue = sc.nextInt();
        if (issuedBookNumber == 0 && bookDB.containsKey(bookToIssue)) {
            issuedBookNumber = bookToIssue;
            System.out.println("Book issued: " + bookDB.get(bookToIssue));
        } else if (issuedBookNumber != 0) {
            System.out.println("Return previous book first.");
        } else {
            System.out.println("Invalid book.");
        }
        userMainPage();
    }

    public void returnBook() {
        if (issuedBookNumber != 0 && bookDB.containsKey(issuedBookNumber)) {
            System.out.println("Returning Book: " + bookDB.get(issuedBookNumber));
            System.out.print("Confirm Return? (Y/N): ");
            String returnChoice = sc.next();
            if (returnChoice.equalsIgnoreCase("Y")) {
                issuedBookNumber = 0;
                System.out.println("Book returned.");
            } else {
                System.out.println("Return cancelled.");
            }
        } else {
            System.out.println("No book to return.");
        }
        userMainPage();
    }

    public static void main(String[] args) {
        DigitalLibraryManagement library = new DigitalLibraryManagement();
        library.homepage();
    }
}