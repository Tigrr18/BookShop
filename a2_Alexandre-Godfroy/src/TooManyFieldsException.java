// ---------------------------------------------
// Assignment 2
// Questions: part 1, part2, part3
// Written by: Alexandre Godfroy
// ---------------------------------------------s


/**
 * Exception thrown when there are too many fields in the input data.
 */
public class TooManyFieldsException extends Exception{
    /**
     * Constructs a TooManyFieldsException with a default message indicating too many fields.
     */
    public TooManyFieldsException (){
        super("Error: Too many fields");
    }
    /**
     * Constructs a TooManyFieldsException with a specified message.
     *
     * @param s the message explaining the exception
     */
    public TooManyFieldsException(String s){
        super(s);
    }
}
