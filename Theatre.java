/*
 * CW 2023 - new theatre 
 * author - rukaiya nishfa
 * ID - w1953601/20220991
 */

import java.util.*;
import java.io.*;
import java.io.IOException;

public class Theatre {
    static Scanner input = new Scanner(System.in);
    static int[] arrRow1 = new int[12];
    static int[] arrRow2 = new int[16];
    static int[] arrRow3 = new int[20];
    static ArrayList<ticket> ticketInfo = new ArrayList<>();

    //method for a user to continue purchasing tickets 
    public static boolean choice(){
            while (true) {
                System.out.print(">>>>  ");
                String answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    return true;
                } else if (answer.equalsIgnoreCase("n")) {
                    return false;
                } else {
                    System.out.println("\nInvalid input! Please enter 'Y' or 'N'.\n");
                }
            }
    }  

    //email validation
    public static String emailValid(){
        String email;
        while (true) {
            System.out.print("Enter your email (eg: john.smith@gmail.com): ");
            email = input.nextLine();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Invalid email, Please re-enter");
                continue;
            } else{
                break;}
        }
        return email;
    }
    //price validation
    public static double priceValid() {
        double price = 0;
        while (true) {
            try {
                System.out.print("\nEnter the Price of the ticket: £ ");
                price = input.nextDouble();
                if (price <= 0) {
                    System.out.println("Invalid amount entered! Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! please enter again.");
                input.nextLine(); // consume the invalid input
            } 
        }return price;
    }
    //row validation
    public static int rowValid(){
        int rowNum=0;
        while (true) {
            try {
                System.out.print(">>>> ");
                rowNum = input.nextInt();
                if (rowNum>3 || rowNum<=0){
                    System.out.println("\nInvalid row number, please enter between 1-3");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid input! please the row number again.");
                input.nextLine(); // consume the invalid input
            } 
        } return rowNum;   
    }
    //seat validation
    public static int seatValid(){
        int seatNum=0;
        while (true) {
            try {
                System.out.print(">>>> ");
                seatNum = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("\nInvalid input! please enter the seat number again.");
                input.nextLine(); // consume the invalid input
            } 
        } return seatNum;   
    }

    //method to check seat availability
    public static void check_seat(int[]list, int seat){
        while(true){ 
            for(int i = 0; i<list.length; i++){
                if (((i+1)==seat) && (list[i]==0)) {
                    list[i]=1; 
                    System.out.println("\nSeat " + seat + " is successfully purchased.");
                    return; // exit the method, without continuing the loop
                } 
            }
            System.out.println("\nSeat " +  seat + " is already purchased, please choose another seat");
            System.out.print("enter another seat number: ");//user can re-enter 
            seat=input.nextInt();
        }
    } 
    
    //buy_ticket method
    public static void buy_ticket() {
        while(true){
            try {
                input.nextLine(); // consume the newline character coz of string
                System.out.print("Enter your name: ");
                String name = input.nextLine();
                System.out.print("Enter your Surname: ");
                String surName = input.nextLine();
                String email = emailValid();
                
                int seatNum, rowNum;
                while (true) {
                    double price = priceValid();
                    System.out.print("\nEnter the row number (1-3) you would like to purchase.\n");
                    rowNum=rowValid();
                    System.out.print("\nEnter the seat number you would like to purchase." +
                    "\nrow 1 -> 1-12\nrow 2 -> 1-16\nrow 1 -> 1-20\n");
                    seatNum = seatValid();
                    input.nextLine(); // consume the newline character
                    switch (rowNum) {
                        case 1 -> {
                            if ((seatNum > 12 || seatNum <= 0)) {
                                System.out.println("\nIncorrect seat, please re-enter between 1-12");
                                continue;
                            }
                            check_seat(arrRow1, seatNum);
                        }
                        case 2 -> {
                            if ((seatNum > 16 || seatNum <= 0)) {
                                System.out.println("\nIncorrect seat, please re-enter between 1-16");
                                continue;
                            }
                            check_seat(arrRow2, seatNum);
                        }
                        default -> {
                            if ((seatNum > 20 || seatNum <= 0)) {
                                System.out.println("\nIncorrect seat, please re-enter between 1-20");
                                continue;
                            }
                            check_seat(arrRow3, seatNum);
                        }
                    }
                    Person person = new Person(name, surName, email);
                    // Find the ticket in the list and update the seat number
                    for (int i = 0; i < ticketInfo.size(); i++) {
                        ticket t = ticketInfo.get(i);
                        if (t.getRow() == rowNum && t.getSeat() == seatNum) {
                            t.setSeat(seatNum);
                            ticketInfo.set(i, t);
                            break;
                        }
                    }
                    ticketInfo.add(new ticket(rowNum,seatNum, price, person));

                    System.out.println("\nDo you want to purchase another ticket? Y/N ");
                    if (choice()){
                        continue;}
                    else{
                        break;} 
                }break; //main break
                
            } catch (Exception e) {
                System.out.println("\nAn error occurred! Please try again.\n");
                input.next(); //to stop the infinity loop
            } 
        }
    }
    
    //seats area printing method, per list
    public static void print_seating_area(int[] list) {
        for(int index = 0; index<list.length;index++){
            if(index== list.length/2){ //print the space between the seats
                System.out.print(" ");
            }
            if (list[index]==0) {
                System.out.print("O");
            } else {
                System.out.print("X" );
            }
        }
        System.out.println();
    }
    
    //per list, makes 1->0
    public static void cancel_seat_list(int[]list, int seat) {
        int chance=0; //user only has limited tries incase he forgets the seat
        while(chance<5){
            for(int i = 0; i<list.length; i++){
                if (((i+1)==seat) && (list[i]==1)) {
                    list[i]=0; 
                    System.out.println( "\nSeat " + seat+ " is cancelled.\n");
                    return; // exit the method, without continuing the loop
                }
            }
            System.out.println("\nThe seat " + seat+ " is yet to be purchased, please choose another seat.");  
            System.out.print("Enter another seat number: ");
            seat=input.nextInt();
            chance++;
        }
        System.out.println("\nEnter 'N' to return to the menu.");
    }

    //cancel the tickets
    public static void cancel_ticket() {
        while(true){
            try {
                int seatNum, rowNum;
                System.out.print("\nEnter the row number (1-3) in which the seat you would like to cancel is in.\n");
                rowNum=rowValid();
                System.out.print("\nEnter the seat number you would like to cancel." +
                "\nrow 1 -> 1-12\nrow 2 -> 1-16\nrow 1 -> 1-20\n");
                seatNum = seatValid();
                switch (rowNum) {
                    case 1 -> {
                        if ((seatNum > 12 || seatNum <= 0)) {
                            System.out.println("\nInvalid seat, please re-enter between 1-12");
                            continue;
                        }
                        cancel_seat_list(arrRow1, seatNum);
                    }
                    case 2 -> {
                        if ((seatNum > 16 || seatNum <= 0)) {
                            System.out.println("\nInvalid seat, please re-enter between 1-16");
                            continue;
                        }
                        cancel_seat_list(arrRow2, seatNum);
                    }
                    default -> {
                        if ((seatNum > 20 || seatNum <= 0)) {
                            System.out.println("\nInvalid seat, please re-enter between 1-20");
                            continue;
                        }
                        cancel_seat_list(arrRow3, seatNum);
                    }
                }
                /* https://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html*/
                for (int i = 0; i < ticketInfo.size(); i++) {
                    ticket t = ticketInfo.get(i);
                    if (t.getRow() == rowNum && t.getSeat() == seatNum) {
                        ticketInfo.remove(i);
                        break;  }
                } 
                System.out.println("\nDo you want to cancel another ticket? Y/N ");
                if (choice()){
                    continue;}
                else{
                    break;}  
            } catch (Exception e) {
                System.out.println("\nInvalid input! please enter again\n");
                input.next(); //to stop the infinity loop
            } 
        }
    }
    //method that prints the available seat numbers
    public static void show_available(int[]list){
        for(int i = 0; i<list.length; i++){
            if (list[i]==0) {
                System.out.print((i+1)+ ", " );    } 
        } 
        System.out.println();
    }

    public static void save() {
        try{
            FileWriter fileWrite = new FileWriter("seatDetails.txt");

            for (int i = 0; i < arrRow1.length; i++) {
                fileWrite.write(arrRow1[i] + " ");
            }
            fileWrite.write("\n");

            for (int i = 0; i < arrRow2.length; i++) {
                fileWrite.write(arrRow2[i] + " ");
            }
            fileWrite.write("\n");

            for (int i = 0; i < arrRow3.length; i++) {
                fileWrite.write(arrRow3[i] + " ");
            }
            fileWrite.write("\n");

            fileWrite.flush(); //ensures all the contents are written
            fileWrite.close();

            System.out.println("\nArray contents written to file successfully.\n");

        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    public static void load(){ 
        //https://learning.westminster.ac.uk/ultra/courses/_89628_1/outline/file/_4318564_1
        try {
            File file = new File("seatDetails.txt");
            Scanner file_reader = new Scanner(file);

                for (int i = 0; i < arrRow1.length; i++) {
                    arrRow1[i] = Integer.valueOf(file_reader.next());
                }
                for (int j = 0; j < arrRow2.length; j++) {
                    arrRow2[j] = Integer.valueOf(file_reader.next());
                }
                for (int k = 0; k < arrRow3.length; k++) {
                    arrRow3[k] = Integer.valueOf(file_reader.next());
                }
            
            file_reader.close();
            System.out.println("Array contents are loaded to the system successfully");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method that calculates total for tickets purchased, and displays ticket object
    public static void show_ticket_info() {
        double totalPrice =0;
        for (ticket i : ticketInfo) {
            System.out.println("-------------------------------------------------");
            i.print();
            totalPrice += i.getPrice();
        }
        System.out.println("\nThe total price of all tickets is £" + totalPrice);
    }
 
    //method that sort the tickets according to the ticket price for each, starting with the cheapest
    public static void sort_tickets() {
        //selection sorting - https://learning.westminster.ac.uk/ultra/courses/_89628_1/outline/file/_4327080_1
        int cheap; 
        ticket temp;
        for (int start = 0; start < ticketInfo.size() - 1; start++) {
            cheap = start;
            for (int i = start + 1; i < ticketInfo.size(); i++) {
                ticket index = ticketInfo.get(i);
                ticket minIndex = ticketInfo.get(cheap);
                if (index.getPrice() < minIndex.getPrice()) {
                    cheap = i;  }
            }
            temp = ticketInfo.get(start);
            ticketInfo.set(start, ticketInfo.get(cheap));
            ticketInfo.set(cheap, temp);
            /*https://stackoverflow.com/questions/40118518/how-two-swap-two-elements-in-a-array-list-without-using-collections-swap-method*/
        }
        double totalPrice = 0;
        for (ticket i : ticketInfo) {
            System.out.println("-------------------------------------------------");
            i.print();
            totalPrice += i.getPrice();
        }
        System.out.println("\nThe total price of all tickets is £" + totalPrice);
    }
    
    public static void main(String[] args) {
        boolean exit = false;
        while (true) {
            System.out.println("\nWelcome the New Theatre: ");
            System.out.println("-------------------------------------------------");
            System.out.println("""
                    Please select an option: \s
                    1) Buy a ticket
                    2) Print seating area
                    3) Cancel ticket\s
                    4) List available seats
                    5) Save to file\s
                    6) Load from file\s
                    7) Print ticket information and total price
                    8) Sort tickets by price
                        0) Quit""");
            System.out.println("-------------------------------------------------");
            try {
                System.out.print("Enter option: ");
                int ans = input.nextInt();
                System.out.println();
                switch (ans) {
                    case 1 -> {
                        buy_ticket();
                        System.out.println("Thank you for your purchase");
                    }
                    case 2 -> {
                        System.out.println("     ***********\n     *  STAGE  *\n     ***********\n");
                        System.out.print("    ");  //indentation
                        print_seating_area(arrRow1);
                        System.out.print("  ");
                        print_seating_area(arrRow2);
                        print_seating_area(arrRow3);
                    }
                    case 3 -> cancel_ticket();
                    case 4 -> {
                        System.out.print("Seats available in row 1: ");
                        show_available(arrRow1);
                        System.out.print("Seats available in row 2: ");
                        show_available(arrRow2);
                        System.out.print("Seats available in row 3: ");
                        show_available(arrRow3);
                    }
                    case 5 -> save();
                    case 6 -> load();
                    case 7 -> show_ticket_info();
                    case 8 -> sort_tickets();
                    case 0 -> exit = true;
                    default -> {
                        System.out.println("Invalid option!\n please re-enter");
                        continue;
                    }
                }
                if(exit){
                    break;   }        
            } 
            catch (Exception e) {
                System.out.println("\nInvalid input! please enter again\n");
                input.next(); //to stop the infinity loop
            }  
        } 
    }    
}
