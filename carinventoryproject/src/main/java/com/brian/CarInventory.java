package com.brian;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarInventory {

    // ArrayList to hold the Cars list to use in this object
    private List<Car> carsList = new ArrayList<>();
    // ArrayList to hold the search results
    private List<Car> filteredList = new ArrayList<>();

    Scanner sc = new Scanner(System.in);


    // start()
    public void start() {
        System.out.println("WELCOME TO CAR INVENTORY.");
        readFromFile();
        mainMenu();
    }


    // mainMenu()
    public void mainMenu() {


        boolean storeOpen = true;

        while (storeOpen) {

            System.out.println("Please select from the menu below.");

            System.out.println("1. Add a car to the inventory\n" +
                    "2. Delete a car from the inventory\n" +
                    "3. List all the cars in the inventory\n" +
                    "4. Search for cars by: Make, Model, Year, Color, or less than a certain number of miles. \n" +
                    "5. Exit Program. ");
            int userInput = Integer.parseInt(sc.nextLine());

            switch (userInput) {

                case 1:
                    addCar();
                    break;
                case 2:
                    deleteCar();
                    break;
                case 3:
                    displayInventory();
                    mainMenu();
                    break;
                case 4:
                    searchForCar();
                    break;
                case 5:
                    System.out.println("Have a nice day.");
                    break;
                default:
                    System.out.println("Please enter a valid menu option");

            }
            // if exit applicaiton is selected then change the condition to false.
            storeOpen = false;

        }

    }


    // addCar()
    public void addCar() {

        System.out.println("Please enter in your car that you would like to add to the inventory.");
        System.out.println("Enter in the make of the car: ");
        String userMake = sc.nextLine();
        System.out.println("Enter in the model of the car: ");
        String userModel = sc.nextLine();
        System.out.println("Enter in the year of the car: ");
        String userYearStr = sc.nextLine();
        int userYear = Integer.parseInt(userYearStr);
        System.out.println("Enter in the color of the car: ");
        String userColor = sc.nextLine();
        System.out.println("Enter in the miles of the car: ");
        String userMilesStr = sc.nextLine();
        int userMiles = Integer.parseInt(userMilesStr);
        System.out.println("Great, you have added a " + userYear + " " + userMake + " " + userModel + " that is " + userColor);

        // create a new car object
        Car newCar = new Car(userMake, userModel, userYear, userColor, userMiles);
        // add the new car object to the ArrayList
        carsList.add(newCar);

        writeToFile();

        mainMenu();

    }


    // deleteCar()
    public void deleteCar() {
        displayInventory();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Enter which car (By number) you would like to delete: ");
        int userDelete = Integer.parseInt(sc.nextLine());
        // update array
        carsList.remove(userDelete);

        // write updated array to file
        writeToFile();

        mainMenu();

    }


    // searchForCar()
    public void searchForCar() {

        boolean keepGoing = false;

        do {

            System.out.println("Would you like to search by the Make, Model, Year, Color, or less than a certain amount of miles?\n" +
                    "1) Make: \n" +
                    "2) Model: \n" +
                    "3) Color: \n" +
                    "4) Year: \n" +
                    "5) Less Than Certain Miles: ");
            int userChoice = Integer.parseInt(sc.nextLine());

            switch (userChoice) {

                case 1:
                    searchByMake();
                    break;
                case 2:
                    searchByModel();
                    break;
                case 3:
                    searchByColor();
                    break;
                case 4:
                    searchByYear();
                    break;
                case 5:
                    searchByMiles();
                    break;
                default:
                    keepGoing = true;
                    System.out.println("Please enter a valid search option");
            }

        } while (keepGoing);

    }

    // searchByMake()
    public void searchByMake() {
        System.out.println("Please enter in what make of car you would like to search: ");
        String searchMake = sc.nextLine();
        int count = 0;

        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getMake().equals(searchMake)) {
                filteredList.add(carsList.get(i));
                count++;
            }
        }

        if ( count > 0 ) {
            // display results
            displaySearchResults();
        } else {
            // no results fount
            System.out.println("No vehicles were found.");
        }

        mainMenu();

    }

    // searhByModel()
    public void searchByModel() {
        System.out.println("Please enter in what model of car you would like to search: ");
        String searchModel = sc.nextLine();
        int count = 0;

        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getMake().equals(searchModel)) {
                filteredList.add(carsList.get(i));
                count++;
            }
        }
        if ( count > 0 ) {
            // display results
            displaySearchResults();
        } else {
            // no results fount
            System.out.println("No vehicles were found.");
        }

        mainMenu();
    }

    // searchByColor()
    public void searchByColor() {
        System.out.println("Enter in what color of car you would like to search for: ");
        String searchColor = sc.nextLine();
        int count = 0;

        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getMake().equals(searchColor)) {
                filteredList.add(carsList.get(i));
                count++;
            }
        }
        if ( count > 0 ) {
            // display results
            displaySearchResults();
        } else {
            // no results fount
            System.out.println("No vehicles were found.");
        }

        mainMenu();
    }

    // searchByYear()
    public void searchByYear() {
        System.out.println("Enter in what year of car you would like to search for: ");
        String searchYearStr = sc.nextLine();
        int count = 0;

        int searchYear = Integer.parseInt(searchYearStr);
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getYear() == searchYear) {
                filteredList.add(carsList.get(i));
                count++;
            }
        }
        if ( count > 0 ) {
            // display results
            displaySearchResults();
        } else {
            // no results fount
            System.out.println("No vehicles were found.");
        }

        mainMenu();
    }

    // searchByMiles()
    public void searchByMiles() {
        System.out.println("Enter in the maximum amount of miles you would like the car to have: ");
        String searchYearStr = sc.nextLine();
        int count = 0;

        int searchYear = Integer.parseInt(searchYearStr);
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getMiles() < searchYear) {
                filteredList.add(carsList.get(i));
                count++;
            }
        }
        if ( count > 0 ) {
            // display results
            displaySearchResults();
        } else {
            // no results fount
            System.out.println("No vehicles were found.");
        }

        mainMenu();
    }


    // viewInventory()
    // displays all elements in the carList ArrayList
    public void displayInventory() {

        for (int i = 0; i < carsList.size(); i++) {
            System.out.println("Vehicle # " + i);
            System.out.println( carsList.get(i).getColor() + " " + carsList.get(i).getYear() + " " + carsList.get(i).getMake() + " " +
                    carsList.get(i).getModel() + " with " + carsList.get(i).getMiles() + " miles" );
            System.out.println("________________");
        }

//        mainMenu();

    }


    // displaySearchResults()
    // displays all elements in the filterdList ArrayList
    public void displaySearchResults() {

        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println("Vehicle # " + i);
            System.out.println( carsList.get(i).getColor() + " " + carsList.get(i).getYear() + " " + carsList.get(i).getMake() + " " +
                    carsList.get(i).getModel() + " with " + carsList.get(i).getMiles() + " miles" );
            System.out.println("________________");
        }

        mainMenu();

    }


    // this one will completely rewrite the csv file
    public void writeToFile() {

        // uses carsList to overwrite the csv file
        try {
            Writer writer = new FileWriter("new_carinventory_file.csv");
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(carsList);
            writer.close();

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println(e);
        }

        readFromFile();

    }


    public void readFromFile() {

        // read the newly created file
        try {
            // set state to what ever is returned from the file
            carsList = new CsvToBeanBuilder<Car>(new FileReader("new_carinventory_file.csv")).withType(Car.class).build().parse();
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("NO STORAGE FILE WAS FOUND, PLEASE ENTER A VEHICLE TO ENABLE SEARCHING");
            System.out.println();
        }

    }


}
