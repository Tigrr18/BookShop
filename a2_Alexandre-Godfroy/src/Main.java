// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy    
// ---------------------------------------------s


import javax.xml.transform.Source;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program processes book records, handles file I/O, and
 * organizes books based on genre. It provides functionality for viewing
 * and selecting books through a menu interface.
 */
public class Main {
    /**
     * Driver method calls the different parts of the
     * assignment, which are made in different methods
     */
    public static void main(String[] args) {
        do_part1();
        do_part2();
        do_part3();
    }

    /**
     * Processes input book files by reading given book records and categorizing them
     * into genre-specific files. Syntax errors in the book records are caught and kept
     * in a syntax error file.
     */
    static void do_part1() {
        Scanner sc = null;
        PrintWriter CCB = null, HCB = null, MTV = null, MRB = null, NEB = null, OTR = null, SSM = null, TPA = null, syn = null;
        PrintWriter[] files = {CCB,HCB,MTV,MRB,NEB,OTR,SSM,TPA,syn};
        String[] fileNames = null;
        final int syntax = 8;
        final String[] fieldNames = {"title", "authors", "price", "isbn", "genre", "year"};
        final String[] bookGenres = {"CCB","HCB","MTV","MRB","NEB","OTR","SSM","TPA"};
        final String[] genreFileNames = {"Cartoons_Comics_Books.csv","Hobbies_Collectibles_Books.csv",
                "Movies_TV.csv","Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv","Old_Time_Radio.csv",
                "Sports_Sports_Memorabilia.csv","Trains_Planes_Automobiles.csv","syntax_error_file.txt"};

        try {
            sc = new Scanner(new FileInputStream("part1_input_file_names.txt"));
            int numOfFiles = sc.nextInt();
            sc.nextLine();
            fileNames = new String[numOfFiles];
            for (int i = 0; i < numOfFiles; i++) {
                fileNames[i] = sc.nextLine();
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Error: could not find/open file \"part1_input_file_names.txt\". Exiting program.");
            System.exit(0);
        }

        for (int i = 0; i < files.length; i++) {
            try{
                files[i] = new PrintWriter(new FileOutputStream(genreFileNames[i]));
            }catch (FileNotFoundException e){
                System.out.println("Error: could not create/open \"" + genreFileNames[i] + "\" file.");
            }
        }

        for (int i = 0; i < fileNames.length; i++) {
            try {
                sc = new Scanner(new FileInputStream(fileNames[i]));
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] entry = LineToArray(line);
                    try{
                        if (entry.length>6){
                            throw new TooManyFieldsException();
                        } else if (entry.length<6){
                            throw new TooFewFieldsException();
                        }else {
                            for (int j = 0; j < entry.length; j++) {
                                if (entry[j].equals("")) {
                                    throw new MissingFieldException(fieldNames[j]);
                                }
                            }

                            boolean genreExists = false;
                            int genrePosition = -1;
                            for (int j = 0; j < bookGenres.length; j++) {
                                if (entry[4].equals(bookGenres[j])){
                                    genreExists = true;
                                    genrePosition = j;
                                }
                            }

                            if (genreExists){
                                files[genrePosition].println(line);
                            } else{
                                throw new UnknownGenreException();
                            }

                        }
                    }catch (TooManyFieldsException | TooFewFieldsException | MissingFieldException | UnknownGenreException e){
                        System.out.println("syntax error in file: " + fileNames[i] +
                                "\n====================\n" + e + "\n Record: " + line + "\n");
                        files[syntax].println(line);
                    }

                }
            }catch (FileNotFoundException e){
                System.out.println("Error: could not open file \"" + fileNames[i] + "\". Exiting program.");
                System.exit(0);
            }
        }
        for (int j = 0; j < files.length; j++) {
            files[j].close();
        }
    }

    /**
     * Processes organized genre book files, creates book objects and serializes
     * those objects to binary files. Semantic errors are caught and written to a
     * semantic error file.
     */
    static void do_part2(){
        final String[] csvFileNames = {"Cartoons_Comics_Books.csv","Hobbies_Collectibles_Books.csv",
                "Movies_TV.csv","Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv","Old_Time_Radio.csv",
                "Sports_Sports_Memorabilia.csv","Trains_Planes_Automobiles.csv"};
        final String[] serFileNames = {"Cartoons_Comics_Books.csv.ser","Hobbies_Collectibles_Books.csv.ser",
                "Movies_TV.csv.ser","Music_Radio_Books.csv.ser","Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};
        final int title_index = 0, author_index = 1, price_index = 2, isbn_index = 3, genre_index = 4, year_index = 5;
        Scanner CCB = null, HCB = null, MTV = null, MRB = null, NEB = null, OTR = null, SSM = null, TPA = null;
        Scanner[] readFiles = {CCB, HCB, MTV, MRB, NEB, OTR, SSM, TPA};
        PrintWriter semantic = null;
        ObjectOutputStream CCB_ser = null, HCB_ser = null, MTV_ser = null, MRB_ser = null, NEB_ser = null, OTR_ser = null, SSM_ser = null, TPA_ser = null;
        ObjectOutputStream[] writeFiles = {CCB_ser, HCB_ser, MTV_ser, MRB_ser, NEB_ser, OTR_ser, SSM_ser, TPA_ser};

        try {
            for (int i = 0; i < readFiles.length; i++) {
                readFiles[i] = new Scanner(new FileInputStream(csvFileNames[i]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, could not find file");
        }

        try {
            for (int i = 0; i < writeFiles.length; i++) {
                writeFiles[i] = new ObjectOutputStream(new FileOutputStream(serFileNames[i]));
            }
            semantic = new PrintWriter(new FileOutputStream("semantic_error_file.txt"));
        } catch (IOException e) {
            System.out.println("Error, could not create/find file");
        }

        for (int i = 0; i < readFiles.length; i++) {
            while (readFiles[i].hasNextLine()) {
                String line = readFiles[i].nextLine();
                String[] entry = LineToArray(line);
                if (entry.length < 6) {
                    System.out.println(line);
                }else {

                    String title = entry[title_index];
                    String author = entry[author_index];
                    double price = Double.parseDouble(entry[price_index]);
                    String isbn = entry[isbn_index];
                    String genre = entry[genre_index];
                    int year = Integer.parseInt(entry[year_index]);

                    try {
                        CheckPrice(price);
                        CheckYear(year);
                        if (isbn.length() == 10) {
                            CheckIsbn10(isbn);
                        } else if (isbn.length() == 13) {
                            CheckIsbn13(isbn);
                        } else {
                            throw new BadIsbn10Exception("Error: The ISBN is not the correct length (10 or 13)");
                        }

                        Book book = new Book(title, author, price, isbn, genre, year);
                        try {
                            writeFiles[i].writeObject(book);
                        } catch (IOException f) {
                            System.out.println("Error: could not print binary in file " + writeFiles[i]);
                        }

                    } catch (BadPriceException | BadYearException | BadIsbn10Exception | BadIsbn13Exception e) {
                        System.out.println("semantic error in file: " + csvFileNames[i] +
                                "\n====================\n" + e + "\nRecord: " + line + "\n");
                        semantic.println(title + "," + author + "," + price + "," + isbn + "," + genre + "," + year);
                    }
                }
            }
        }
        for (int i = 0; i < writeFiles.length; i++) {
            try {
                writeFiles[i].close();
            } catch (IOException e) {
                System.out.println("Error: problem closing file " + writeFiles[i]);
            }
        }
    }

    /**
     * Validates that the price is a positive value.
     *
     * @param price The price of the book to be checked.
     * @throws BadPriceException if the price is less than or equal to zero.
     */
    static void CheckPrice(double price) throws BadPriceException {
        if (price <= 0){
            throw new BadPriceException("Error: Price cannot be equal to or lower than zero.");
        }
    }

    /**
     * Validates that the publication year is between 1995 and 2010.
     *
     * @param year The year of the book to be checked.
     * @throws BadYearException if the year is outside 1995-2010.
     */
    static void CheckYear(int year) throws BadYearException {
        if (year > 2010 || year < 1995){
            throw new BadYearException("Error: Year must be between 1995 and 2010.");
        }
    }

    /**
     * Validates a 10-digit ISBN.
     *
     * @param isbnString The ISBN of the book to be checked.
     * @throws BadIsbn10Exception if the ISBN does not follow given criteria.
     */
    static void CheckIsbn10 (String isbnString) throws BadIsbn10Exception{
        int[] isbn = new int[isbnString.length()];
        char character;
        for (int i = 0; i < isbnString.length(); i++) {
            character = isbnString.charAt(i);
            if (character == '0' | character ==  '1' | character ==  '2' | character ==  '3' | character ==  '4' | character ==  '5' | character ==  '6' | character ==  '7' | character ==  '8' | character ==  '9'){
                isbn[i] = Integer.parseInt(character + "");
            }else{
                throw new BadIsbn10Exception("Error: The ISBN contains abnormal characters.");
            }
        }
        int sum = 0;

        for (int i = 0; i < isbn.length; i++) {
            sum += isbn[i]*(10-i);
        }

        if (sum%11 != 0){
            throw new BadIsbn10Exception("Error: ISBN 10 is invalid.");
        }
    }

    /**
     * Validates a 13-digit ISBN.
     *
     * @param isbnString The ISBN of the book to be checked.
     * @throws BadIsbn13Exception if the ISBN does not follow given criteria.
     */
    static void CheckIsbn13 (String isbnString) throws BadIsbn13Exception{
        int[] isbn = new int[isbnString.length()];
        for (int i = 0; i < isbnString.length(); i++) {
            isbn[i] = Integer.parseInt(isbnString.charAt(i) + "");
        }

        int sum = isbn[0] + isbn[1]*3 + isbn[2] + isbn[3]*3 + isbn[4] + isbn[5]*3 + isbn[6] + isbn[7]*3 + isbn[8] + isbn[9]*3 + isbn[10] + isbn[11]*3 + isbn[12];

        if (sum%10 != 0){
            throw new BadIsbn13Exception("Error: ISBN 13 is invalid.");
        }
    }

    /**
     * Deserializes books from binary files and allows the user to view books
     * based on genre through a menu.
     */
    static void do_part3(){
        final String[] serFileNames = {"Cartoons_Comics_Books.csv.ser","Hobbies_Collectibles_Books.csv.ser",
                "Movies_TV.csv.ser","Music_Radio_Books.csv.ser","Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};
        ObjectInputStream CCB_ser = null, HCB_ser = null, MTV_ser = null, MRB_ser = null, NEB_ser = null, OTR_ser = null, SSM_ser = null, TPA_ser = null;
        ObjectInputStream[] files = {CCB_ser, HCB_ser, MTV_ser, MRB_ser, NEB_ser, OTR_ser, SSM_ser, TPA_ser};

        try{
            files = OpenFiles(files, serFileNames);
        }catch (IOException e){
            System.out.println("Error: Could not open binary files");
        }
        Book temp = null;
        int[] counter = new int[files.length];
        for (int i = 0; i < files.length; i++) {
            boolean endOfFile = false;
            do{
                try{
                    temp = (Book) files[i].readObject();
                    counter[i]++;
                } catch (EOFException e) {
                    endOfFile = true;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error: There was a problem reading the binary file");
                }
            }while (!endOfFile);
        }
        try{
            files = CloseFiles(files);
        } catch (IOException e) {
            System.out.println("Error: Could not close binary files");
        }

        Book[][] books = new Book[files.length][];
        for (int i = 0; i < books.length; i++) {
            books[i] = new Book[counter[i]];
        }

        try {
            files = OpenFiles(files,serFileNames);
            for (int i = 0; i < files.length; i++) {
                for (int j = 0; j < books[i].length; j++) {
                    books[i][j] = (Book) files[i].readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: Something went wrong with the binary files reading");
        }

        Scanner kb = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("Welcome to Alexandre Godfroy's book sorter!");
        int currentFile = 0;
        String input = DisplayMainMenu(serFileNames[currentFile],counter[currentFile],kb);
        while (true){

            switch (input){
                case "v": {
                    System.out.println("viewing: " + serFileNames[currentFile] + " (" + counter[currentFile] + " records)");
                    if (counter[currentFile] == 0){
                        System.out.println("No books found in " + serFileNames[currentFile] + ". Returning to Main Menu.");
                        input = DisplayMainMenu(serFileNames[currentFile],counter[currentFile],kb);
                        break;
                    }
                    int n = -1;
                    while (n != 0) {
                        System.out.print("Please enter viewing command (positive or negative integer): ");
                        n = kb.nextInt();
                        int indexPointer = 0;
                        int display;

                        if (n == 0) {
                            System.out.println("Returning to main menu...");
                            input = DisplayMainMenu(serFileNames[currentFile],counter[currentFile],kb);
                            break;
                        } else if (n < 0) {
                            if (indexPointer + n < 0) {
                                System.out.println("BOF has been reached");
                                indexPointer = 0;
                                display = DisplayLeft(books[currentFile], indexPointer, n);
                            } else {
                                indexPointer = indexPointer + n;
                                display = DisplayLeft(books[currentFile], indexPointer, n);
                            }
                        } else {
                            display = DisplayRight(books[currentFile], indexPointer, n);
                            if (indexPointer+n >= books[currentFile].length) {
                                indexPointer = books[currentFile].length - 1;
                            }else{
                                indexPointer = indexPointer + n;
                            }
                        }
                    }
                }
                case "s": {
                    int subInput = DisplaySubMenu(serFileNames,counter,kb);
                    switch (subInput) {
                        case 1: {
                            currentFile = 0;
                            break;
                        }
                        case 2: {
                            currentFile = 1;
                            break;
                        }
                        case 3: {
                            currentFile = 2;
                            break;
                        }
                        case 4: {
                            currentFile = 3;
                            break;
                        }
                        case 5: {
                            currentFile = 4;
                            break;
                        }
                        case 6: {
                            currentFile = 5;
                            break;
                        }
                        case 7: {
                            currentFile = 6;
                            break;
                        }
                        case 8: {
                            currentFile = 7;
                            break;
                        }
                        case 9: {
                            System.out.println("Thank you for using our book sorter. See you next time!");
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Invalid subInput. please try again.");
                            subInput = kb.nextInt();
                            break;
                        }
                    }
                    input = DisplayMainMenu(serFileNames[currentFile],counter[currentFile],kb);
                    break;
                }
                case "x":{
                    System.out.println("Thank you for using our book sorter. See you next time!");
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.print("Invalid input, please enter a valid input: ");
                    input = kb.next();
                    break;
                }
            }
        }
    }

    /**
     * Opens an array of ObjectInputStreams to read from given binary files.
     *
     * @param files Array of ObjectInputStreams to open.
     * @param names Array of file names to be opened.
     * @return An array of open ObjectInputStreams.
     * @throws IOException if a file cannot be opened.
     */
    static ObjectInputStream[] OpenFiles(ObjectInputStream[] files, String[] names) throws IOException {
        for (int i = 0; i < files.length; i++) {
            files[i]=new ObjectInputStream(new FileInputStream(names[i]));
        }
        return files;
    }

    /**
     * Closes a given array of ObjectInputStreams.
     *
     * @param files Array of ObjectInputStreams to close.
     * @return An array of closed ObjectInputStreams.
     * @throws IOException if a file cannot be closed.
     */
    static ObjectInputStream[] CloseFiles(ObjectInputStream[] files) throws IOException {
        for (int i = 0; i < files.length; i++) {
            files[i].close();
        }
        return files;
    }

    /**
     * Displays the main menu for selecting and viewing files and returning the user's choice.
     *
     * @param selectedFileName The name of the currently selected file.
     * @param records The number of records in the selected file.
     * @param kb Scanner for user input.
     * @return The user's menu choice.
     */
    static String DisplayMainMenu(String selectedFileName, int records, Scanner kb){
        System.out.println("-------------------------------------------");
        System.out.println("                 Main Menu                 ");
        System.out.println("-------------------------------------------");
        System.out.println(" v View the selected file: " + selectedFileName + " (" + records + " records)");
        System.out.println(" s Select a file to view");
        System.out.println(" x Exit");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.print("Enter Your Choice: ");
        return kb.nextLine();
    }

    /**
     * Displays the submenu for selecting the genre file to view and returning the user's choice.
     *
     * @param fileNames Array of file names to select from.
     * @param records Array of record counts corresponding to each file.
     * @param kb Scanner for user input.
     * @return The user's menu choice as an integer.
     */
    static int DisplaySubMenu(String[] fileNames, int records[], Scanner kb){
        System.out.println("-------------------------------------------");
        System.out.println("               File Sub-Menu               ");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println(" " + (i+1) + " " + fileNames[i] + "\t \t(" + records[i] + " records)");
        }
        System.out.println(" 9 Exit");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.print("Enter Your Choice: ");
        int choice = kb.nextInt();
        kb.nextLine();
        return choice;
    }

    /**
     * Displays books to the left of the current index based on user input n using recusion.
     *
     * @param books Array of Book objects.
     * @param currentIndex The current index position in the array.
     * @param n The number of positions to move left.
     * @return an integer n that allows us to know when the recursion ends.
     */
    static int DisplayLeft(Book[] books, int currentIndex, int n){
        if (currentIndex == -1){
            return 0;
        } else if(n == -1){
            System.out.println(books[currentIndex]);
            System.out.println();
            return currentIndex;
        } else{
            System.out.println(books[currentIndex]);
            System.out.println();
            return DisplayLeft(books, currentIndex-1, n+1);
        }
    }

    /**
     * Displays books to the right of the current index based on user input n using recusion.
     *
     * @param books Array of Book objects.
     * @param currentIndex The current index position in the array.
     * @param n The number of positions to move right.
     * @return an integer n that allows us to know when the recursion ends.
     */
    static int DisplayRight(Book[] books, int currentIndex, int n){
        if (currentIndex > books.length-1){
            System.out.println("BOF has been reached");
            System.out.println();
            return currentIndex-1;
        } else if (n == 1) {
            System.out.println(books[currentIndex]);
            System.out.println();
            return currentIndex;
        }else {
            System.out.println(books[currentIndex]);
            System.out.println();
            return DisplayRight(books, currentIndex+1, n-1);
        }
    }

    /**
     * Converts a line of CSV-formatted text into an array of Strings, where each element represents a field in the record.
     * If the book title contains quotes, the commas contained within the quotes are not taken into account.
     *
     * @param line the line of text to parse
     * @return an array of Strings containing the different fields in the record;
     */
    static String[]LineToArray(String line){
        String originalLine = line;
        int commaCount = 0;
        for (int j = 0; j < line.length(); j++) { //counting total amount of comma
            if (line.charAt(j)==',') {
                commaCount++;
            }
        }
        String[] entry;
        if (originalLine.charAt(0) == '"') { //if the title has the " character, count again
            line = originalLine;
            int quoteEnd = line.substring(1).indexOf('"');
            String title = line.substring(0, quoteEnd+1);
            String[] lineContainingQuotes = line.split("\"");
            commaCount = 0; //reset to zero
            for (int j = 0; j < lineContainingQuotes[2].length(); j++) { //recount
                if (lineContainingQuotes[2].charAt(j) == ',') {
                    commaCount++;
                }
            }
            entry = lineContainingQuotes[2].split(",");
            entry[0] = title;
        }else{
            entry = line.split(",");
        }
        if (originalLine.charAt(originalLine.length()-1) == ',') {
            String[] temp = entry;
            entry = new String[entry.length+1];
            for (int i = 0; i < temp.length; i++) {
                entry[i] = temp[i];
            }
            entry[entry.length-1] = "";
        }
        return entry;
    }
}
