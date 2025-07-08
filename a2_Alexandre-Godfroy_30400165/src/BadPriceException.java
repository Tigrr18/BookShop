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
 * This exception is thrown to indicate an invalid price input.
 */
public class BadPriceException extends Exception{
    /**
     * Constructs a new BadPriceException with the specified message.
     *
     * @param s the message that provides more information about the invalid ISBN-13.
     */
    public BadPriceException (String s){
        super(s);
    }
}
