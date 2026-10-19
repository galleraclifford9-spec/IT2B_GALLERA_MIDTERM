package IT2B_GALLERA_MIDTERM.ui;

import IT2B_GALLERA_MIDTERM.dao.ScenarioDao;
import IT2B_GALLERA_MIDTERM.model.Reservation;

import java.util.List;
import java.util.Scanner;

public class ReservationUI {
    private static final String LINE = "=".repeat(50);
    private final Scanner scanner = new Scanner(System.in);
    private final ScenarioDao dao = new ScenarioDao();

    public void start() {
        int choice;
        do {
            showMenu();
            choice = readChoice();
            switch (choice) {
                case 1 -> addReservation();
                case 2 -> viewReservations();
                case 3 -> System.out.println("\nExiting system... Thank you!");
                default -> System.out.println("\nInvalid choice. Please enter 1, 2, or 3.\n");
            }
        } while (choice != 3);
    }

    private void showMenu() {
        System.out.println(LINE);
        System.out.println("          HOTEL ROOM RESERVATION SYSTEM");
        System.out.println(LINE);
        System.out.println("[1] Add New Reservation");
        System.out.println("[2] View All Reservations");
        System.out.println("[3] Exit System");
        System.out.println(LINE);
        System.out.print("Enter choice: ");
    }

    private int readChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void addReservation() {
        System.out.println("\n--- ADD NEW RESERVATION ---");

        String name;
        do {
            System.out.print("Enter Guest Name: ");
            name = scanner.nextLine().trim();
        } while (name.isEmpty());

        String room;
        while (true) {
            System.out.print("Enter Room Type (SINGLE / DOUBLE / SUITE): ");
            room = scanner.nextLine().trim().toUpperCase();
            if (room.equals("SINGLE") || room.equals("DOUBLE") || room.equals("SUITE")) break;
            System.out.println("Invalid room type. Try again.");
        }

        double rate;
        while (true) {
            System.out.print("Enter Nightly Rate: ");
            try {
                rate = Double.parseDouble(scanner.nextLine().trim());
                if (rate > 0) break;
                System.out.println("Rate must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        String payment;
        while (true) {
            System.out.print("Enter Payment Status (PAID / PENDING): ");
            payment = scanner.nextLine().trim().toUpperCase();
            if (payment.equals("PAID") || payment.equals("PENDING")) break;
            System.out.println("Invalid status. Try again.");
        }

        if (dao.addReservation(new Reservation(name, room, rate, payment))) {
            System.out.println("\nSUCCESS: Reservation recorded in database!\n");
        } else {
            System.out.println("\nFAILED: Could not save reservation.\n");
        }
    }

    private void viewReservations() {
        List<Reservation> list = dao.getAllReservations();
        String border = "+----+--------------------+------------+--------------+---------------+";

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                          ALL ROOM RESERVATIONS");
        System.out.println("=".repeat(70));
        System.out.println(border);
        System.out.printf("| %-2s | %-18s | %-10s | %-12s | %-13s |%n",
                "ID", "GUEST NAME", "ROOM TYPE", "NIGHTLY RATE", "PAYMENT");
        System.out.println(border);
        for (Reservation r : list) {
            System.out.printf("| %-2d | %-18s | %-10s | %-12.2f | %-13s |%n",
                    r.getId(), r.getGuestName(), r.getRoomType(), r.getNightlyRate(), r.getPayment());
        }
        System.out.println(border);
        System.out.println("Total Records Displayed: " + list.size() + "\n");
    }
}