
package vuhuy.kashima.exception;

/**
 * A ValidationException is thrown if any user input is invalid
 * 
 * @author Sebastian Kaspari
 */
public class ValidationException extends Exception
{
    private static final long serialVersionUID = 6951535205062761539L;

    /**
     * Create a new ValidationException with the given message
     * 
     * @param message The error message
     */
    public ValidationException(String message)
    {
        super(message);
    }
}
