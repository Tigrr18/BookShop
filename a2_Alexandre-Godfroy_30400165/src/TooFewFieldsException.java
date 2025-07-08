// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy, ID 40300165
// ---------------------------------------------s
/**
 * Name and ID Alexandre Godfroy 40300165
 * COMP249
 * Assignment #2
 * Due Date 14th of November 2024
 */

/**
 * Exception thrown when there are too few fields in the input data.
 */
public class TooFewFieldsException extends Exception{
    /**
     * Constructs a TooFewFieldsException with a default message indicating too few fields.
     */
    public TooFewFieldsException(){
        super("Error: Too few fields");
    }
    /**
     * Constructs a TooFewFieldsException with a specified message.
     *
     * @param s the message explaining the exception
     */
    public TooFewFieldsException(String s){
        super(s);
    }
}
