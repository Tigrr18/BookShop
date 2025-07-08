// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy
// ---------------------------------------------

/**
 * This exception is thrown to indicate an error with a 10-digit ISBN.
 */
public class BadIsbn10Exception extends Exception{
    /**
     * Constructs a new BadIsbn10Exception with the specified message.
     *
     * @param s the message that provides more information about the invalid ISBN-10.
     */
    public BadIsbn10Exception(String s){
        super(s);
    }
}
