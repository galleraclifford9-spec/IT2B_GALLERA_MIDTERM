package IT2B_GALLERA_MIDTERM;

import IT2B_GALLERA_MIDTERM.dao.DatabaseConnection;
import IT2B_GALLERA_MIDTERM.ui.ReservationUI;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.initialize();
        new ReservationUI().start();
    }
}
