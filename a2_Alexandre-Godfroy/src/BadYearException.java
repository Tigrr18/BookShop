// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy
// ---------------------------------------------s


/**
 * This exception is thrown to indicate an invalid year input.
 */
public class BadYearException extends Exception{
    /**
     * Constructs a new BadYearException with the specified message.
     *
     * @param s the message that provides more information about the invalid ISBN-13.
     */
    public BadYearException(String s) {
        super(s);
    }
}
