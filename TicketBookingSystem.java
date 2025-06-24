import java.util.Scanner;

public class TicketBookingSystem {

    private static boolean[] seatStatus = new boolean[10];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\nChoose an action:");
            System.out.println("1. Show Seats");
            System.out.println("2. Book a Seat");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Quit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    displaySeats();
                    break;
                case 2:
                    bookSeat();
                    break;
                case 3:
                    removeBooking();
                    break;
                case 4:
                    System.out.println("Exiting system. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private static void displaySeats() {
        System.out.println("\nAvailable Seats:");
        for (int i = 0; i < seatStatus.length; i++) {
            if (seatStatus[i]) {
                System.out.print("X ");
            } else {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }

    private static void bookSeat() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("\nEnter a seat number to book (1-10): ");
        int selectedSeat = inputScanner.nextInt();

        if (selectedSeat < 1 || selectedSeat > 10) {
            System.out.println("Invalid seat number!");
        } else if (seatStatus[selectedSeat - 1]) {
            System.out.println("This seat is already booked.");
        } else {
            seatStatus[selectedSeat - 1] = true;
            System.out.println("Seat successfully booked!");
        }
    }

    private static void removeBooking() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("\nEnter a seat number to cancel (1-10): ");
        int selectedSeat = inputScanner.nextInt();

        if (selectedSeat < 1 || selectedSeat > 10) {
            System.out.println("Invalid seat number!");
        } else if (!seatStatus[selectedSeat - 1]) {
            System.out.println("This seat is not currently booked.");
        } else {
            seatStatus[selectedSeat - 1] = false;
            System.out.println("Booking has been cancelled.");
        }
    }
}
