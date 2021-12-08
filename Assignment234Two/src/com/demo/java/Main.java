package com.demo.java;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void displayInfo() {//Display info method as required by assignment instructions
        System.out.println("--------------------------------------");
        System.out.println("Assignment 2, 159.234 Semester 1 2021");
        System.out.println("Submitted by Brown, Phillip 21011867");
        System.out.println("--------------------------------------");
    }
    //Array lists to populate objects as they are read in
    protected static List<Book> bookList = new ArrayList<>();
    protected static List<Movies> moviesList = new ArrayList<>();
    protected static List<Journals> journalsList = new ArrayList<>();

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));//Added to handle two worded inputs

    public static void main(String[] args) throws IOException {
        //file read and holder list.
        List<String> holdPen = new ArrayList<>();

        try (BufferedReader dataIn = new BufferedReader(new FileReader("library.txt"))) {

            try {
                String line;
                while ((line = dataIn.readLine()) != null){
                    holdPen.add(line);
                }//while
            }//t2

            finally {
                dataIn.close();
            }
        }//t1
        catch (IOException e){
            System.out.println("Cannot read file please check naming, format or type and try again");
            e.printStackTrace();
        }//catch

        displayInfo();
        displayRaw(holdPen);
        mainMenu();
    }

    private static void mainMenu() throws IOException {//Main menu, first part of the menu asking for user input
        System.out.println("Enter 'q' to quit, enter 'i' to search by ID, or enter any other key to search by phrase in title");
        String Line = br.readLine();
        otherMainMenu(Line);
    }

    private static void otherMainMenu(String Line) throws IOException {//Handles switch statement, can be called from other methods directly to a switch condition.
        switch (Line) {
            case "q":
                System.exit(0);
            case "i":
                System.out.println("Enter ID to start search, or enter 'b' to go back to choose search method");
                Line = br.readLine();
                if(!Line.equals("b")) searchData(Line);
                else mainMenu();
                break;
        }
        selectItemTitle();
    }

    private static void displayRaw(List<String> holdPen){//reads through the holding list, splits the parts into the respective object types and displays them.

        for (String lines : holdPen) {

            String[] parts = lines.split(",");

            switch (parts[0]) {
                case "Movie":
                    Movies movies = new Movies();
                    movies.type = parts[0];
                    movies.id = Integer.parseInt(parts[1]);
                    movies.title = parts[2];
                    movies.year = Integer.parseInt(parts[3]);
                    movies.setDirector(parts[4]);
                    moviesList.add(movies);
                    System.out.println(movies);//DEBUG
                    break;
                case "Book":
                    Book book = new Book();
                    book.type = parts[0];
                    book.id = Integer.parseInt(parts[1]);
                    book.title = parts[2];
                    book.year = Integer.parseInt(parts[3]);
                    book.setAuthor(parts[4]);
                    book.setNumPages(Integer.parseInt(parts[5]));
                    bookList.add(book);
                    System.out.println(book);//DEBUG
                    break;
                case "Journal":
                    Journals journals = new Journals();
                    journals.type = parts[0];
                    journals.id = Integer.parseInt(parts[1]);
                    journals.title = parts[2];
                    journals.year = Integer.parseInt(parts[3]);
                    journals.setVolume(Integer.parseInt(parts[4]));
                    journals.setNumber(Integer.parseInt(parts[5]));
                    journalsList.add(journals);
                    System.out.println(journals);//DEBUG
                    break;
                default:
                    //  System.out.println(parts[0]);
                    break;
            }
        }//for
    }

    private static void searchData(String input) throws IOException {//
        /* For searching through the respective lists for the item number being searched.
        Goes through each list one at time until it either finds what it is looking for or doesn't.
        */
        int item = 0;
        int count = 0;
        int totalObjects = journalsList.size() + bookList.size() + moviesList.size();

        try {//If a user enters a non int value, error caught and the user is pushed back to 2nd menu
            item = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: You must enter a ID number for this search");
            otherMainMenu("i");

        }

        for (Movies value : moviesList) {
            if (item == value.id) {
                System.out.println(value);
                preSelection(value);
            }
            else count++;
        }//for

        for (Journals value : journalsList){
            if(item == value.id){
                System.out.println(value);
                preSelection(value);
            }
            else count++;
        }

        for (Book value : bookList){
            if (item == value.id){
                System.out.println(value);
                preSelection(value);
            }
            else count++;
        }//for

        if(count == totalObjects){//If the count is equal to the size of the three types then it has read every value and failed to find the search value
            System.out.println("Cannot locate item\n");
            otherMainMenu("i");
        }
        
    }

    private static void preSelection(Libitem value) throws IOException {
        /* Handles the selecting/selected item processing. Takes an Libitem object as input and processes through the borrowing and rating*/
        System.out.println("Enter 'i' to search for another item by ID, or enter any other key to select this item");
        String input = br.readLine();

        if(input.equals("i")){
            otherMainMenu(input);
        }
        System.out.println("Selected item is\n");
        System.out.println(value);

        selectItem(value);
    }

    private static void selectItem(Libitem value) throws IOException {
        /*Handles the borrowing and rating, spilt from the preselection so that the word search can call it without having to reselect the Object*/
        String input;

        if(value.onLoan){
            System.out.println("Enter 'r' to return the item, 'a' to rate the item or any other key to restart");
        }

        else{
            System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
        }

        input = br.readLine();

        switch (input){
            case "b":
                value.onLoan = true;
                System.out.println("The item's due date is: " + value.getDueDate());
                System.out.println("Selected item is\n");
                System.out.println(value);
                selectItem(value);
            case "a":
                System.out.println("Please enter your rating (1-10)");
                input = br.readLine();//add try/catch
                try{//Tests to check the number is a valid float.
                    Float.parseFloat(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number between 1-10");
                    selectItem(value);
                }
                Libitem.ratings.add(Float.parseFloat(input));
                System.out.println("The items new average rating is " + value.getAvgRating() + "\n");
                value.numReviews++;
                System.out.println("Selected item is\n");
                System.out.println(value);
                selectItem(value);
            case "r":
                System.out.println("The item has been returned");
                value.onLoan = false;
                System.out.println("Selected item is\n");
                System.out.println(value);
                selectItem(value);

        }
       mainMenu();//if none of the switch conditions are meet, return to main menu
    }

    private static void selectItemTitle() throws IOException {
        /*Processes the wor search, uses String.Contains() to check if the title matches/partial matches any title in the three object lists, it then
        places them in a array of objects, prints it and asks for user input. Once the user selects the index of the item they want it pushes it to selectedItem*/
        ArrayList<Libitem> libitems = new ArrayList<>();

        System.out.println("Enter a phrase in the title to start search, or enter 'b' to go back and choose search method");
        String input = br.readLine();
        int item = 0;
        int count = 1;

        if(input.equals("b")) { mainMenu(); }

        for (Movies value : moviesList){
            if(value.title.contains(input)) { libitems.add(value); }
        }//For loops kept in multiline format for readability

        for (Journals value : journalsList){
            if(value.title.contains(input)) { libitems.add(value); }
        }//Same for loop condidtions as the ID search except we are only looking for a partial match and passing it into the array

        for (Book value : bookList){
            if(value.title.contains(input)) { libitems.add(value); }
        }

        if(libitems.size() > 0){//If the array isn't empty then it found something that matched at least partially.
            for (Libitem value : libitems){
                System.out.println("* "+ count + " ----------------------------");
                count++;
                System.out.println(value);
            }

            System.out.println("Enter the item number to select item, or enter any other key to continue searching");
            input = br.readLine();
            try{
                item = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error, index number not selected");
                selectItemTitle();
            }

            System.out.println("Selected item is");
            System.out.println(libitems.get(item - 1));
            selectItem(libitems.get(item - 1));
        }

        else{//If the array is empty then nothing matched and we start again.
            System.out.println("Cannot locate item, please note search is case sensitive\n");
            selectItemTitle();
        }//if the list is empty then there was nothing matching the criteria.

    }
}//class
