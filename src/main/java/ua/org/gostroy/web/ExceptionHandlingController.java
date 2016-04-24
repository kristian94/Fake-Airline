package ua.org.gostroy.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gostroy.exception.ErrorCode;
import ua.org.gostroy.exception.NoFlights;
import ua.org.gostroy.exception.NoneAvilableTickets;
import ua.org.gostroy.web.domain.FlightExceptionMessage;

/**
 * Created by Sergey on 4/23/2016.
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    @ExceptionHandler({NoFlights.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FlightExceptionMessage handleAppException1(NoFlights ex) {
        FlightExceptionMessage flightExceptionMessage = new FlightExceptionMessage(HttpStatus.NOT_FOUND.value(), ErrorCode.NoFlights.errorCode, ex.getMessage());

        return flightExceptionMessage;
    }

    @ExceptionHandler({NoneAvilableTickets.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FlightExceptionMessage handleAppException4(NoneAvilableTickets ex) {
        FlightExceptionMessage flightExceptionMessage = new FlightExceptionMessage(HttpStatus.NOT_FOUND.value(), ErrorCode.NoneTickets.errorCode, ex.getMessage());

        return flightExceptionMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FlightExceptionMessage handleAppException2(MethodArgumentNotValidException ex) {
        FlightExceptionMessage flightExceptionMessage = new FlightExceptionMessage(HttpStatus.BAD_REQUEST.value(), ErrorCode.IllegalInput.errorCode, ex.getMessage());

        return flightExceptionMessage;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public FlightExceptionMessage handleAppException3(Throwable ex) {
        FlightExceptionMessage flightExceptionMessage = new FlightExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.UnknownError.errorCode, ex.getMessage());

        return flightExceptionMessage;
    }

}
