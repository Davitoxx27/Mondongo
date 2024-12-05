//DAVID CALZADO OLMO
//ADAM GOURAM EL BOUHALI
//CARLOTA CALERO 
//ERNESTO CALDERON 
import java.util.Scanner;

public class Assignment7 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    static final int ROWS = 5;
    static final int COLUMNS = 4;
    static final char AVAILABLE = 'A';
    static final char OCCUPIED = 'O';
    private static int availableSeats = ROWS * COLUMNS;
    private static char[][] seats = seatstatus();
    static int tickets;
    static final double DISCOUNT_MINORS = 20; // or multiply by 0,8
	static final double DISCOUNT_MAXTICKETS = 10; // or multiply by 0,9
	static final int MAX_TICKETS_NUMBER = 12;
    static int minorTickets;
    private static int totalTickets;
    private static boolean purchaseCompleted , seatsAssigned;
    private static double price;
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
     //Method that selects the seats
    //  public static selectSeats(){



    //  }
    //Method to select seats manually
    public static void selectSeatsManually(){
        for (int i = 0; i < totalTickets; i++) {
            System.out.println("Ticket number " + (i + 1));
            int row, column;
            boolean seatselected = false;
            do {
                // Ask for the row of the seat
                row = verifyrange(1, ROWS, "Enter the row number of the seat you want to buy: ");
                System.out.println("The row number of the seat is: " + row);
                // Ask for the column of the seat
                System.out.println("Ticket number " + (i + 1));
                column = verifyrange(1, COLUMNS, "Enter the column number of the seat you want to buy: ");
                System.out.println("The column number of the seat is: " + column);
                if (seats[row - 1][column - 1] == OCCUPIED) {
                    System.out.println("ERROR. The seat is already occupied.");
                } else {
                    // Mark the seat as occupied
                    seats[row - 1][column - 1] = 'O'; // We put 'O' to indicate is Occupied
                    availableSeats--;
                    seatselected = true;
                }
            } while (seatselected == false);
        }


    }
    //Method to select seats automatically
public static void selectSeatsAutomatically(){
    int selectedSeats = 0;
                            boolean completedSeatSelection = false;
                            System.out.println("Seats will be selected automatically:");
                            // Traverse through rows of the seats matrix in search of available seats
                            // Traversal ends when the seat selection is complete, i.e. when as many seats have been selected as there are tickets to be purchased by the user.
                            for(int i = 0; i < ROWS && !completedSeatSelection; i++){
                                for(int j = 0; j < COLUMNS && !completedSeatSelection; j++){
                                    if(seats[i][j] == AVAILABLE){
                                        seats[i][j] = OCCUPIED;
                                        System.out.println("The seat in row " + (i+1) + " and column " + (j+1) + " has been selected.");
                                        // The selected and available seat counts are updated.
                                        selectedSeats++;
                                        availableSeats--;
                                        
                                        if(selectedSeats == tickets){
                                            completedSeatSelection = true;
                                        }
                                    }
                                }
                            }



}
// A method that selects adjacent seats
public static void searchContiguousSeats(){


    boolean adjacentSeats = false;
    // To find contiguous seats, the array must be traversed. Once found, no further traversal is necessary. 
    // Since this is a traversal that may end before the end of the matrix traversal (indeterminate number of iterations), 
    // the matrix is traversed by nesting two while loops.
    int i = 0;
    // The while condition causes the rows of the matrix to be traversed as long as contiguous entries have not been found.
    while (i < ROWS && !adjacentSeats) {
        int adjacent = 0; // adjacent seats counter
        int j = 0;
        while (j < COLUMNS && !adjacentSeats) {
            if (seats[i][j] == AVAILABLE) {
                adjacent++;
            } else {
                adjacent = 0; // Restart the count when an occupied seat is found
            }
            // If, after traversing the matrix, N contiguous seats have been found....
            if (adjacent == tickets) {
                // Adjacent seats found are marked  as occupied.
                for (int k = j - adjacent + 1; k <= j; k++) {
                    seats[i][k] = OCCUPIED;
                    // Update available seats
                    availableSeats--;
                    System.out.println("The seat in row " + (i+1) + " and column " + (k+1) + " has been selected.");
                }
                adjacentSeats = true;
                purchaseCompleted = true;
                seatsAssigned = true;
            }
            j++;
        }
        i++;
    }
    if(!adjacentSeats){
        System.out.println("There are not enough adjacent seats available. Choose another type of seat selection");
        purchaseCompleted = false;
    }
    }
    //A method that shows the invoice
    public static void showInvoice(){
                         // Generation of the invoice for the purchase of tickets if the seats were assigned
                         if(seatsAssigned==true){ 
                            double totalPrice = price * tickets;
                            int ticketsAdults = tickets - minorTickets;
                            double totalPriceMinors, totalPriceAdults;
                            if (minorTickets> 0) {
                                totalPriceMinors = price * (1 - DISCOUNT_MINORS/100)  * minorTickets;//divided into 100 to convert it into a percentange, and substract 1 to apply the discount(it is equal to multiply by 0,8)
                                totalPriceAdults = price * ticketsAdults;
                                totalPrice = totalPriceAdults + totalPriceMinors;
                            }
                            else {
                                totalPrice = price * tickets;
                            }
                            if (tickets > MAX_TICKETS_NUMBER) {
                                totalPrice = totalPrice * (1 - DISCOUNT_MAXTICKETS/100);//(equivalent to multiply by 0,9)
                            }
                            System.out.println("Invoice:");
                            System.out.println("  Number of tickets: " + tickets);
                            System.out.println("  Price per ticket: " + price);
                            System.out.println("  Discount applied for purchase in bulk: " + (tickets > MAX_TICKETS_NUMBER ? DISCOUNT_MAXTICKETS + "%": "None"));
                            System.out.println("  Discount applied for tickets for minors: " + (minorTickets > 0 ? DISCOUNT_MINORS + "%" : "None"));
                            System.out.printf("  Total price: %.2f", totalPrice);
                            System.out.println();
                        }
                else{
                    System.out.println("Sorry, there are not enough seats available. Purchase could not be finished.");
                }


    }
        
    public static void menu() {
        int option;
        price = askprice();
        do {
            // The available seats are shown
            seatsAvailability();
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
            // Depending on the option selected by the user, it will process the different
            // algorithms:
            switch (option) {
                //Purchase tickets
                case 1:
                    //See if there are enough seats available
                    if (availableSeats>0){
                    System.out.println("Purchasing tickets chosen");
                    // Ask for the number of tickets to buy

                    totalTickets = verifyrange(1, availableSeats, "There are: " + availableSeats + " seats available ,enter the number of tickets you want to buy: "  );
                    tickets = totalTickets;
                    System.out.println("The total tickets bought are: " + totalTickets);
                    boolean adultTickets = false;
                    // Ask for the number of tickets for minors
                    do {
                        minorTickets = verifyrange(0, totalTickets, "Enter the number of tickets for minors,there must be at least one adult: ");
                        if (minorTickets == totalTickets) {
                            System.out.println("ERROR. There must be at least one adult");

                        } else {
                            System.out.println("The number of tickets for minors are: " + minorTickets);
                            adultTickets = true;
                        }
                    } while (adultTickets == false);
                    int purchaseOption;
                                            // Purchase options menu is displayed as long as the purchase has not been successfully completed.
                                            purchaseCompleted = true;
                                            // check that the seats have been assigned and thus avoid indicating the number of tickets and to exit the purchase option menu without having selected the seats
                                            seatsAssigned = false;
                    do{
                        System.out.println("Choose a ticket purchase option:");
                        System.out.println("1. Manual seat selection.");
                        System.out.println("2. Automatic seat selection.");
                        System.out.println("3. Automatic selection of adjacent seats.");
                        System.out.println("4. Exit purchase menu");
                        System.out.println("Enter the number of the option you wish to choose:");
                        // Read purchase option
                        purchaseOption = KEYBOARD.nextInt();
                        switch (purchaseOption) {
                            //Manual seat selection
                            case 1:
                            selectSeatsManually();
                                break;

                            //Automatic seat selection
                            
                            case 2:
                            selectSeatsAutomatically();
                            purchaseCompleted = true;
                            seatsAssigned = true;
                        break;
                        case 3:
                        searchContiguousSeats();
                        break;
                        case 4: 
                        System.out.println("You exited the purchase menu.");
                        break;
                        default:
                        System.out.println("ERROR 404. The option must be between 1 and 4.");
                    }
                }
                while(purchaseCompleted == false);
         showInvoice();        
        }
        break;
                    
                case 2:
                // Change tickets
                // Tickets can be changed when there is at least one ticket purchased and one seat available for exchange.
                int occupiedSeats = ROWS * COLUMNS - availableSeats;
                if (availableSeats > 0 && occupiedSeats > 0) {
                    // Show seat availability
                    seatsAvailability();
                    // Ask the number of tickets to change
                    int changedTickets = askTicketsToChange();
                    // Continue with the logic to change tickets...
                    System.out.println(changedTickets + " seats will be changed.");
                    // The current seat status is displayed first.
                    // The first row with the column headers of the matrix is displayed.
                   changeTickets(changedTickets);
                } else {
                    System.out.println("It is not possible to change seats because there are no seats available or there are no occupied seats.");
                }
                break;
           
                case 3:
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
                    break;
                case 4:
                    // Exit the program
                    System.out.println("You exited the program.");
                    break;
                default:
                    // If the option is not valid, an error message is displayed
                    System.out.println("ERROR 404. The option must be between 1 and 4.");
            }
        } while (option != 4);

    }
    //Seats availability
    public static void seatsAvailability() {
        System.out.println("The current seat availability is as follows:");
        //Colums headers
        System.out.print("\t");
        for (int i = 1; i <= COLUMNS; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        //Rows & Columns
        for (int i = 0; i < ROWS; i++) {
            System.out.print("Row " + (i + 1) + ":  ");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("%c \t", seats[i][j]);
            }
            System.out.println();
    }
        
    }
    //Now a method which ask the number of tickets to change:
    public static int askTicketsToChange() {
        int changedTickets;
        int occupiedSeats = ROWS * COLUMNS - availableSeats;
        do {
            System.out.println(
                    "Enter number of tickets to change. Please note that to change tickets you must select a number of tickets that have been purchased in advance and for which there are enough seats available.");

                    changedTickets = KEYBOARD.nextInt();
            if (changedTickets > occupiedSeats || changedTickets > availableSeats) {
                System.out
                        .println("ERROR. It is not possible to change the selected number of tickets.");
            }
        } while (changedTickets > occupiedSeats || changedTickets > availableSeats);
        System.out.println(changedTickets + " seats wil be changed.");
        return changedTickets;
    }

    //Last but not least, a method that change seat for N tickets:
    public static void changeTickets(int changedTickets) {
        for (int i = 0; i < changedTickets; i++) {
            System.out.println("Ticket number " + (i + 1));
            int row, column;
            boolean seatselected = false;
            boolean changeCompleted = false;
            seatsAvailability();
            // Change the ticket
            do {

                // Ask for the row of the seat
                row = verifyrange(1, ROWS, "Enter the row number of the seat you want to change: ");
   

                // Ask for the column of the seat
                
                column = verifyrange(1, COLUMNS, "Enter the column number of the seat you want to change: ");
               
                
                if (seats[row - 1][column - 1] == OCCUPIED) {
                    System.out.println("Correct position, you selected, to change, the seat:" + " row: "+ row + " column: " + column);
                    // Mark the seat as available
                    seats[row - 1][column - 1] = 'A'; // We put 'A' to indicate is Available
                    availableSeats++;
                    seatselected = true;
                   
                    //Select a new empty seat
                    do {
                    row = verifyrange(1, ROWS, "Enter the row number of the new seat: ");
                    column = verifyrange(1, COLUMNS, "Enter the column number of the new seat: ");
                    if (seats[row - 1][column - 1] == AVAILABLE) {
                        System.out.println("Correct position, your new seat is: " + " row: "+ row + " column: " + column);
                        // Mark the seat as occupied
                        seats[row - 1][column - 1] = 'O'; // We put 'O' to indicate is Occupied
                        availableSeats--;
                        changeCompleted = true;
                    } else {
                        System.out.println("ERROR. The seat is already occupied,try with another seat.");
                    }
                } while (changeCompleted == false);
                }
                 else {
                    // The seat was not occupied, so it cannot be changed.
                    System.out.println("ERROR. The seat is already available.");
                }
            }

            while (seatselected == false);
        }
    }
}