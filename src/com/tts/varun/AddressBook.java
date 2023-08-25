package com.tts.varun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.exit;

public class AddressBook {

    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String emailAddress;

    public AddressBook() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public AddressBook(String firstName,String lastName,long phoneNumber,String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "************\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Email: " + emailAddress + "\n" +
                "************\n";
    }


    private static void mainPrompt() {
        System.out.println("1) Add an entry\n" +
                "2) Remove an entry\n" +
                "3) Search for a specific entry\n" +
                "4) Print Address Book\n" +
                "5) Delete Book\n" +
                "6) Quit\n" +
                "Please choose what you'd like to do with the database:");
    }

    private static String searchFirstName(String firstName, ArrayList<AddressBook> addressBook){
        ArrayList<AddressBook> searchResult = new ArrayList<>();

        for (AddressBook addressBook1 : addressBook) {
            if (addressBook1.getFirstName().startsWith(firstName)) {
                searchResult.add(addressBook1);
            }
        }
        return searchResult.toString();
    }

    private static String searchLastname(String lastName,ArrayList<AddressBook> addressBook){
        ArrayList<AddressBook> searchResult = new ArrayList<>();

        for (AddressBook addressBook1 : addressBook) {
            if (addressBook1.getLastName().startsWith(lastName)) {
                searchResult.add(addressBook1);
            }
        }
        return searchResult.toString();
    }

    private static String searchPhoneNumber(long phoneNumber,ArrayList<AddressBook> addressBook){
        ArrayList<AddressBook> searchResult = new ArrayList<>();

        for (AddressBook addressBook1 : addressBook) {
            if (addressBook1.getPhoneNumber() == phoneNumber) {
                searchResult.add(addressBook1);
            }
        }
        return searchResult.toString();
    }

    private static String searchEmailAddress(String emailAddress,ArrayList<AddressBook> addressBook){
        ArrayList<AddressBook> searchResult = new ArrayList<>();

        for (AddressBook addressBook1 : addressBook) {
            if (addressBook1.getEmailAddress().startsWith(emailAddress)) {
                searchResult.add(addressBook1);
            }
        }
        return searchResult.toString();
    }

    private static ArrayList<AddressBook> removeEntry(String emailAddress,ArrayList<AddressBook> addressBook){

        boolean isFound = false;

        Iterator<AddressBook> iterator = addressBook.iterator();

        while (iterator.hasNext()){
            AddressBook tmpAddressBook = iterator.next();
            if (tmpAddressBook.getEmailAddress().equals(emailAddress)){
                System.out.println("Deleted the following entry:");
                System.out.println(tmpAddressBook.toString());
                iterator.remove();
                isFound = true;
            }
        }
        if (!isFound){
            System.out.println("\nEntry not found!");
        }

        return addressBook;
    }

    public static void main(String[] args) {

        ArrayList<AddressBook> addressBook = new ArrayList<>();

        String firstName;
        String lastName;
        long phoneNumber;
        String emailAddress;

        AddressBook.mainPrompt();

        int tryCount = 0;
        int maxTryCount = 3;

        Scanner scanner = new Scanner(System.in);

        while (tryCount < maxTryCount){ // Allow only maximum 3 invalid retries
            try {
                int mainSelection = Integer.parseInt(scanner.nextLine());

                while ( mainSelection <= 6 ){
                    switch (mainSelection){

                        case 1:
                        {
                            System.out.println("First Name:");
                            firstName = scanner.nextLine();

                            System.out.println("Last Name:");
                            lastName = scanner.nextLine();

                            System.out.println("Phone Number:");
                            phoneNumber = Long.parseLong(scanner.nextLine());

                            System.out.println("Email Address:");
                            emailAddress = scanner.nextLine();

                            addressBook.add(new AddressBook(firstName,lastName,phoneNumber,emailAddress));
                            AddressBook.mainPrompt();

                            break;
                        }
                        case 2:
                        {
                            System.out.println("\nEnter an entry's email to remove:");
                            emailAddress = scanner.nextLine();

                            System.out.println("DEBUG: "+emailAddress);

                            AddressBook.removeEntry(emailAddress,addressBook);
                            AddressBook.mainPrompt();

                            break;
                        }
                        case 3:
                        {
                            System.out.println("1) First Name\n" +
                                    "2) Last Name\n" +
                                    "3) Phone Number\n" +
                                    "4) Email Address\n" +
                                    "Chose a search type:");
                            int searchSelection = Integer.parseInt(scanner.nextLine());


                            switch (searchSelection){
                                case 1:
                                {
                                    System.out.println("\nEnter your search:");
                                    String firstNameForSearch = scanner.nextLine();

                                    System.out.println(AddressBook.searchFirstName(firstNameForSearch,addressBook));
                                    AddressBook.mainPrompt();
                                    break;
                                }
                                case 2:
                                {
                                    System.out.println("\nEnter your search:");
                                    String lastNameForSearch = scanner.nextLine();

                                    System.out.println(AddressBook.searchLastname(lastNameForSearch,addressBook));
                                    AddressBook.mainPrompt();
                                    break;

                                }
                                case 3:
                                {
                                    System.out.println("\nEnter your search:");
                                    long phoneNumberForSearch = Long.parseLong(scanner.nextLine());

                                    System.out.println(AddressBook.searchPhoneNumber(phoneNumberForSearch,addressBook));
                                    AddressBook.mainPrompt();
                                    break;

                                }
                                case 4:
                                {
                                    System.out.println("\nEnter your search:");
                                    String emailAddressForSearch = scanner.nextLine();

                                    System.out.println(AddressBook.searchEmailAddress(emailAddressForSearch,addressBook));
                                    AddressBook.mainPrompt();
                                    break;

                                }
                                default:
                                {
                                    System.out.println("\nInvalid search option.");
                                    AddressBook.mainPrompt();
                                    break;
                                }
                            }

                            break;
                        }

                        case 4:
                        {
                            System.out.println(addressBook.toString());
                            AddressBook.mainPrompt();
                            break;
                        }

                        case 5:
                        {
                            addressBook.clear();
                            System.out.println("Address Book cleared.");
                            AddressBook.mainPrompt();
                            break;
                        }

                        case 6:
                        {
                            System.out.println("Thank you for using my Address Book. Good Bye!");
                            System.exit(0);
                        }

                        default:
                        {
                            System.out.println("You have entered an invalid value.");
                            tryCount = tryCount+1;
                            AddressBook.mainPrompt();
                        }

                    }
                    mainSelection = Integer.parseInt(scanner.nextLine());

                }

                //Catch any main selection greater than 6
                System.out.println("You have entered an invalid value.");
                tryCount = tryCount+1;
                AddressBook.mainPrompt();

            } catch (NumberFormatException e) {
                System.out.println("You have entered an invalid value.");
                AddressBook.mainPrompt();
                tryCount =tryCount+1;
            }

        }

        System.out.println("Too many invalid inputs entered. Terminating..");

    }


}
