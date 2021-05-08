package cinema;

import java.util.Scanner;

public class Cinema {
    //creating scanner object
    final static Scanner scanner = new Scanner(System.in);

    //making the grid 2D array
    static String[][] totalRows = {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
            {"1", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"2", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"3", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"4", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"5", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"6", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"7", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"8", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"9", "S", "S", "S", "S", "S", "S", "S", "S", "S"}
    };

    //declaring additional variables
    static int purchased_tickets = 0;
    static int current_income = 0;

    //declaring main method
    public static void main(String[] args) {
        //accepting values of the rows and seats to get the grid
        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seatsInEachRow = scanner.nextInt();

        //calling method
        menu(rows,seatsInEachRow);
    }

    // declaring menu method
    public static void menu(int rows,int seatsInEachRow){
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                show_seats(totalRows,rows,seatsInEachRow);
                menu(rows,seatsInEachRow);
                break;
            case 2:
                buy_ticket(totalRows,rows,seatsInEachRow);
                menu(rows,seatsInEachRow);
                break;
            case 3:
                show_statistics(rows,seatsInEachRow);
                menu(rows, seatsInEachRow);
            case 0:
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    // declaring buy_ticket method
    public static void buy_ticket(String[][] totalRows,int rows,int seatsInEachRow){
        //declaring variables
        int selectedRow;
        int selectedSeat;

        //looping until we get the write values
        while(true){
            try{
                //accepting values of the rows and seats to book the seat
                System.out.println("Enter a row number:");
                selectedRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                selectedSeat = scanner.nextInt();

                //checking if the seat is booked or not
                if(totalRows[selectedRow][selectedSeat].equals("B")){
                    System.out.println("That ticket has already been purchased!");
                } else{
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong input!");
            }
        }

        //declaring additional variables
        int ticketPrice;

        //calculating total number of seats
        int seats = rows * seatsInEachRow;

        //calculating total number of seats in the frontRows
        int frontRow = rows/2;

        //checking if the conditions to determined the prices
        if (seats <= 60){
            ticketPrice = 10;
        } else{
            if(selectedRow <= frontRow){
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }

        //printing the ticket price
        System.out.println("Ticket price: $"+ ticketPrice);

        //changing the unreserved spot to reserved
        totalRows[selectedRow][selectedSeat] = "B";

        //confirming that ticket is purchased and added to the total purchased_tickets
        purchased_tickets++;

        //confirming that ticket is purchased and added to the current_income
        current_income += ticketPrice;

    }

    // declaring show_seats method
    public static void show_seats(String[][] totalRows,int rows,int seatsInEachRow){
        System.out.println();
        //printing the gird
        System.out.println("Cinema:");
        for(int i = 0; i < rows+1; i++){
            for(int j = 0; j < seatsInEachRow+1;j++){
                System.out.print(totalRows[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // declaring show_statistics method
    public static void show_statistics(int rows,int seatsInEachRow){
        //declaring additional variables
        int total_income;
        int frontRow;
        int frontRowSeats;
        int backRowSeats;

        //calculating number of seats
        int seats = rows * seatsInEachRow;

        //calculating percentage tickets sold
        double percentage_sold = ((double)purchased_tickets/(double)seats) * 100.00;

        //checking if the conditions to determined the prices
        if (seats <= 60){
            total_income = 10 * seats;
        } else{
            frontRow = rows/2;
            frontRowSeats = frontRow * seatsInEachRow;
            backRowSeats = (rows-frontRow) * seatsInEachRow;
            total_income = (frontRowSeats * 10) + (backRowSeats * 8);
        }

        // Printing the results
        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f",purchased_tickets,percentage_sold);
        System.out.println("%");
        System.out.printf("Current income: $%d%nTotal income: $%d%n",current_income,total_income);
    }



}