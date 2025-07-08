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
 * Exception thrown when an invalid or unknown genre is encountered.
 */
public class UnknownGenreException extends Exception{
    /**
     * Constructs an UnknownGenreException with a default message indicating an invalid genre.
     */
    public UnknownGenreException(){
        super("Error: invalid genre");
    }
    /**
     * Constructs an UnknownGenreException with a specified message.
     *
     * @param s the message explaining the exception
     */
    public  UnknownGenreException(String s){
        super(s);
    }
}
