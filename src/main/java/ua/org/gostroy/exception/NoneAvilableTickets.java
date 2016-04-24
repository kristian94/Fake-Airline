package ua.org.gostroy.exception;

/**
 * Created by Sergey on 4/23/2016.
 */
public class NoneAvilableTickets extends Exception {

    public NoneAvilableTickets(String message) {
        super(message);
    }

    public NoneAvilableTickets(String message, Throwable cause) {
        super(message, cause);
    }

}
