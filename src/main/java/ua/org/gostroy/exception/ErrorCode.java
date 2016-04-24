package ua.org.gostroy.exception;

/**
 * Created by Sergey on 4/23/2016.
 */
public enum ErrorCode {

    NoFlights(1, "No Flights"),
    NoneTickets(2, "None or not enough avilable tickets"),
    IllegalInput(3, "Illegal input"),
    UnknownError(4, "Unknown error"),;

    public Integer errorCode;
    public String description;

    ErrorCode(Integer errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
}
