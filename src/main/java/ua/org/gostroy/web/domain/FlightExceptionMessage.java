package ua.org.gostroy.web.domain;

/**
 * Created by Sergey on 4/23/2016.
 */
public class FlightExceptionMessage {

    private Integer httpError;
    private Integer errorCode;
    private String message;

    public FlightExceptionMessage(Integer httpError, Integer errorCode, String message) {
        this.httpError = httpError;
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getHttpError() {
        return httpError;
    }

    public void setHttpError(Integer httpError) {
        this.httpError = httpError;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
