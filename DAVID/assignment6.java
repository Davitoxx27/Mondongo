import java.util.Scanner;

public class assignment6 {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    static final int ROWS = 5;
	static final int COLUMNS = 4;
	static final char AVAILABLE = 'A';
	static final char OCCUPIED = 'O';
    public static void main(String[] args) {
    char [][] seats = seatstatus();
double price=askprice();
System.out.println("The price of the ticket is: "+ price);
//Verifyrange:

//Ask for the number of tickets to buy
int totaltickets= verifyrange(1, 20, "Enter the number of tickets you want to buy: ");
System.out.println("The total tickets bought are: " + totaltickets);

//Ask for the number of tickets for minors
int totalminors= verifyrange(0, totaltickets, "Enter the number of tickets for minors: ");
System.out.println("The total tickets for minors are: " + totalminors);

//Ask for the row where the ticket is wanted
int row = verifyrange(1, ROWS, "Enter the row number where you want to buy the ticket: ");
System.out.println("The row number asked is: " + row);

//Ask for the column where the ticket is wanted
int column = verifyrange(1, COLUMNS, "Enter the column number where you want to buy the ticket: ");
System.out.println("The column number asked is: " + column);

//Verify if the seat is available
KEYBOARD.close();
}
public static double askprice(){
    Scanner KEYBOARD = new Scanner(System.in);
    double price;
    do{
        System.out.println("Enter the price for the train ticket.");
        System.out.println("Please note that the ticket price must be greater than 0.");
        price = KEYBOARD.nextDouble();
        if(price <= 0){
            System.out.println("ERROR. The price of the ticket must be greater than 0.");
        }
    }while(price <= 0);
    return price;

}
//a module that displays every seat as available
public static char[][] initializeseats(){
    char [][] seats = new char[ROWS][COLUMNS];
    for(int i = 0;i < ROWS; i++){
        for(int j = 0; j < COLUMNS; j++){
            seats[i][j] = AVAILABLE;
        }
    }
    return seats;
}
//Display the seat status
public static char [][] seatstatus() {
    System.out.println("The current seat status is as follows:");
    char [][] seats = initializeseats();
        // The first row with the column headers of the matrix is displayed.
        System.out.print("\t");
        for (int i = 1; i <= COLUMNS; i ++){
            System.out.print(i + "\t");
        }
        // Each row of seats is shown with its corresponding value (available or occupied).
        System.out.println();
        for(int i = 0; i < ROWS; i++){
            System.out.print("Row " + (i + 1) + ":  ");
            for(int j = 0; j < COLUMNS; j++){
                System.out.printf("%c \t", seats[i][j]);
            }
            System.out.println();
        }
        return seats;
}
//Now we have to make a method that ask the user for the row and column number and to verify if it is within then LB,UB(LB (Lower Bound) & UB (Upper Bound))
public static int verifyrange(int LB,int UB,String message){

int number;
do{
    System.out.println(message);
    number = KEYBOARD.nextInt();
    if(number < LB || number > UB){
        System.out.println("ERROR 404. The number must be between " + LB + " and " + UB);
    }
    

}
while(number < LB || number > UB);
return (number);


    
}

}