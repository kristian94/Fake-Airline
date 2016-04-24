package ua.org.gostroy.exception;

/**
 * Created by Sergey on 4/23/2016.
 */
public class NoFlights extends Exception {

    public NoFlights(String message) {
        super(message);
    }

    public NoFlights(String message, Throwable cause) {
        super(message, cause);
    }
}
