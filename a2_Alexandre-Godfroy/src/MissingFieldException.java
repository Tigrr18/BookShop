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
 * Exception thrown when a required field is missing.
 */
public class MissingFieldException extends Exception {
    /**
     * Constructs a MissingFieldException with a message indicating the missing field.
     *
     * @param field the name of the missing field that caused the exception
     */
    public MissingFieldException(String field){
        super("Error: missing " + field);
    }
}
