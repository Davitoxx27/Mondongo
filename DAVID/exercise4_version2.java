//A1_02 Assignment 4 version 2
//CARLOTA CALERO SÁNCHEZ-CAMACHO
//ERNESTO CALDERÓN GONZALEZ
//DAVID CALZADO OLMO
//ADAM GOURAM EL BOUHALI
import java.util.Scanner;

public class exercise4_version2 {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) {
        int availableseats = 20; //First, we declare all the variables we will need
        char[] seats = new char[20];//We use a char, which is the way to put a letter without being a string, so the program can interactuate with it
        int option = 0;
        double ticketprice = 0;
        int tickets = 0;
        int ages=0;
        int above18=0;
        int below18=0;
        int minortickets=0;
        int seatnumber=0;
        for (int k = 0; k < seats.length; k++) {//This will make that at the beginning every seat is set as Available('A')

                seats[k]='A';
            }
        
        do {//Before we use the switch we display the menu for every case so that the user can choose which option they desire
            System.out.println("Menu: ");
            System.out.println("1.Establish the price for the ticket: ");
            System.out.println("2.Purchase tickets: ");
            System.out.println("3.Exit the program: ");
            System.out.println("Select an option: ");
            option = KEYBOARD.nextInt();

            switch (option) {//We put a switch to divide every algorithm depending on which case the user chose
                case 1://The first case is to set a price for the train ticket
                    do {
                        System.out.println("How much does the train ticket cost?: ");
                        ticketprice = KEYBOARD.nextDouble();
                        if (ticketprice <= 0) {
                            ticketprice = 0;
                            System.out.println("The train must cost more than 0, lets start again: ");

                        }
                        System.out.println("Your ticket cost: " + ticketprice);
                    } 
                    while (ticketprice <= 0);//It will be repeated until the ticket price is neither 0 nor negative
                    break;
                case 2://The second case will have several functions:
                    if (ticketprice <= 0) {//First it will not be initializated if the ticket price hasnt been set yet
                        System.out.println("You have not entered a proper ticketprice yet");
                        }
                     else {
                        boolean invalidnumbertickets=false;//If the ticket price was set, we ask the number of tickets and by the boolean variable we find if there are enough seats available for the tickets wanted
                        do {
                            System.out.println("How many tickets do you want? ");
                            tickets = KEYBOARD.nextInt();
                      
                            if (tickets <= 0) {
                                System.out.println("Error 404, tickets must be greater than 0 ");
                                tickets = 0;
                            }
                            else if (tickets > availableseats) {
                                System.out.println("There are just  "+ availableseats + " tickets left, back to menu");
                                invalidnumbertickets=true;
                                
                            }
                        } while (tickets <= 0 && invalidnumbertickets==true);//It will be repeated if the tickets are less than 0 or more than the available seats
                        
                    
                        
                    
                    do{
                        above18=0;
                        below18=0;
                          for(int i = 0; i < tickets; i++) { //For every person who has a ticket it ask his age
                             
                              System.out.println("How old are you? ");
                              ages = KEYBOARD.nextInt();
                              if (ages>=18){
                              
                             above18 = above18+1;//There is a counter which counts 1 more for being over the legal age
                          
                              }
                              else{
                          
                              below18 = below18+1;//It also counts the number of people under the legal age
                              }
                              }
                              if (above18==0){
                                  System.out.println("There are no adults,reenter your ages: ");
                               
                              }
                      
                      
                      
                      
                      }
                      while (above18==0);//while there are no adults repeat the whole loop
                    
                      minortickets=minortickets+below18;//A variable for later to make the calculations of the price
                    if (tickets < availableseats) {//If the tickets requested are less than the seats available, the program continues
                        int counter = 0;
                        int manual = 0;
                        System.out.println("Seats availability: ");
                        for (int p = 0; p < seats.length; p++) {//Show the seats of the array and their availability

                            
                            System.out.print((p+1) + ":" + seats[p] + " ");
                        }
                        System.out.println();

                        System.out.println("To choose your seat enter any number but 0, if not will be elected automatically ");
                        manual = KEYBOARD.nextInt();//The program ask id the user wants to choose his own seat
                        if (manual == 0) {//If he doesnt want to choose it, the program will choose the first seats without occupancy
                            for (int i = 0; i < seats.length && counter < tickets; i++) {

                                if (seats[i] == 'A') {
                                    seats[i] = 'O';
                                    availableseats--;
                                    counter++;
                                  System.out.println("Seat number " + (i+1) + " has been assigned");//Shows which seats has been assigned
                                }
                                
                            }
                            System.out.println("Seats availability after purchase: ");
                            for (int p = 0; p < seats.length; p++) {

                               
                                System.out.print((p+1) + ":" + seats[p] + " ");
                               
}
                            System.out.println();
                            System.out.println("Your " + tickets + " tickets were bought correctly, " + below18+ " are tickets for minors");
                        } else {//Now if the usear chose manual selection:
                            for (int i=0; i<tickets; i++){
                                boolean validseat=false;
                                while (validseat==false){//It will ask every passanger for his seat, while it is occupied, it will be asking again, if not, it goes on
                                System.out.println("Passenger " + (i+1)  + " desired seat:");
                                seatnumber= KEYBOARD.nextInt();
                                
                                if (seatnumber >= 1 && seatnumber <= 20) {//It assures the seat is beetween the one that can be chosen
                                    seatnumber=seatnumber-1;                        
                                    if (seats[seatnumber] == 'A') {
                                        seats[seatnumber] = 'O';
                                        availableseats--;
                                        validseat=true;
                                        for (int p = 0; p < seats.length; p++) {
                                            System.out.print((p+1) + ":" + seats[p] + " ");
                                                                                                }
                                            System.out.println();
                                
                                    }
                                    else{
                                        System.out.println("One of the seats is already occupied, try again");
                                        
                    
                                        }
                                    
                                    }
                                    
                                else {
                                        System.out.println("The number of the seat you chose is not correct, try again");

                                    }
                                    }
                           
                            }
                                    
                        }
                        //Finally we calculate the price with discounts
                        double totalwithoutdiscounts = ticketprice * tickets;
                            System.out.printf("Total price without discounts: %.2f%n", totalwithoutdiscounts , "$");

                            // Discount for minors
                            double minorsdiscount = below18 * ticketprice * 0.2;
                            System.out.printf("Discount for minors (20%%): %.2f%n", minorsdiscount , "$");

                            // Apply discounts for being more than 12
                            double twelvediscount = 0;
                            if (tickets > 12) {
                                twelvediscount = (totalwithoutdiscounts - minorsdiscount) * 0.1;
                                System.out.printf("Discount for being more than 12(10%%): %.2f%n", twelvediscount , "$");
                            }

                            // Calculate final price with discounts
                            double finalPrice = totalwithoutdiscounts - minorsdiscount - twelvediscount;
                            System.out.printf("Final price with all the discounts: %.2f%n", finalPrice , "$");

                    } else {
                        System.out.println("There are not enough tickets ");//This is what happens when you want more tickets than seats are available
                    }
                    }
                    break;
                case 3://In the third case you stop the program
                    System.out.println("You exited the program");
                    System.out.println("End of the program");
                    break;
                default:
                    System.out.println("You must select a correct option");//If you choose an option that it is not 1,2 or 3 this is displayed

            }

        }while(option!=3);//Display the menu while you not choose 1,2 or 3

}
}
