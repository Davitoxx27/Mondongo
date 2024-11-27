import java.util.Scanner;

public class assignment6 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    static final int ROWS = 5;
    static final int COLUMNS = 4;
    static final char AVAILABLE = 'A';
    static final char OCCUPIED = 'O';
    private static int availableSeats = ROWS * COLUMNS;
    private static char[][] seats = seatstatus();

    public static void main(String[] args) {
        menu();
        KEYBOARD.close();
    }

    public static double askprice() {
        double price;
        do {
            System.out.println("Enter the price for the train ticket.");
            System.out.println("Please note that the ticket price must be greater than 0.");
            price = KEYBOARD.nextDouble();
            if (price <= 0) {
                System.out.println("ERROR. The price of the ticket must be greater than 0.");
            }
        } while (price <= 0);
        return price;

    }

    // a module that displays every seat as available
    public static char[][] initializeseats() {
        char[][] seats = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seats[i][j] = AVAILABLE;
            }
        }
        return seats;
    }

    // Display the seat status
    public static char[][] seatstatus() {
        System.out.println("The current seat status is as follows:");
        char[][] seats = initializeseats();
        // The first row with the column headers of the matrix is displayed.
        System.out.print("\t");
        for (int i = 1; i <= COLUMNS; i++) {
            System.out.print(i + "\t");
        }
        // Each row of seats is shown with its corresponding value (available or
        // occupied).
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            System.out.print("Row " + (i + 1) + ":  ");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("%c \t", seats[i][j]);
            }
            System.out.println();
        }
        return seats;
    }

    // Now we have to make a method that ask the user for the row and column number
    // and to verify if it is within then LB,UB(LB (Lower Bound) & UB (Upper Bound))
    public static int verifyrange(int LB, int UB, String message) {

        int number;
        do {
            System.out.println(message);
            number = KEYBOARD.nextInt();
            if (number < LB || number > UB) {
                System.out.println("ERROR 404. The number must be between " + LB + " and " + UB);
            }

        } while (number < LB || number > UB);
        return (number);

    }

public static void menu(){
    int option;
    double price = askprice();
    do{
        // The number of available seats is shown
        System.out.println("\n Currently there are " + availableSeats + " available seats.");
        // Show the main menu
        System.out.println("\n Choose one of the following options:");
        System.out.println("1. Purchase tickets");
        System.out.println("2. Change tickets");
        System.out.println("3. Show seat status");
        System.out.println("4. Exit the program");
        System.out.println("Enter the number of the option you wish to choose:");
        // Read the option selected by the user
        option = KEYBOARD.nextInt();
        // Depending on the option selected by the user, it will process the different algorithms:
        switch (option) {
            case 1:
             // Purchase tickets
             System.out.println("Purchasing tickets chosen");
             // Ask for the number of tickets to buy
             int totalTickets = verifyrange(1, 20, "Enter the number of tickets you want to buy: ");
             System.out.println("The total tickets bought are: " + totalTickets);

             // Ask for the number of tickets for minors
             int minorTickets = verifyrange(0, totalTickets, "Enter the number of tickets for minors: ");
             System.out.println("The number of tickets for minors are: " + minorTickets);

             // Ask for the row of the seat
             int row = verifyrange(1, ROWS, "Enter the row number of the seat you want to buy: ");
             System.out.println("The row number of the seat is: " + row);

             // Ask for the column of the seat
             int column = verifyrange(1, COLUMNS, "Enter the column number of the seat you want to buy: ");
             System.out.println("The column number of the seat is: " + column);

             // Mark the seat as occupied
             seats[row - 1][column - 1] = 'O'; // We put 'O' to indicate is Occupied
             availableSeats--;
             break;
            // case 2:
            //     // Change tickets
            //     changeTickets();
            //     break;
            // case 3:
            //     // Show seat status
            //     seatstatus();
            //     break;
            // case 4:
            //     // Exit the program
            //     System.out.println("You exited the program.");
            //     break;
            default:
                // If the option is not valid, an error message is displayed
                System.out.println("ERROR 404. The option must be between 1 and 4.");
        }
    }   
    while(option<0 && option>4);
}
}