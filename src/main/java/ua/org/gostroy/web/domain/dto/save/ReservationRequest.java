package ua.org.gostroy.web.domain.dto.save;

import org.hibernate.validator.constraints.Email;
import ua.org.gostroy.domain.entity.Passenger;

import java.util.List;

/**
 * Created by Sergey on 4/23/2016.
 */
public class ReservationRequest {

    private String flightID;
    private Integer numberOfSeats;
    private String reserveeName;
    private String reserveePhone;
    @Email
    private String reserveeEmail;
    private List<Passenger> passengers;


    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getReserveeName() {
        return reserveeName;
    }

    public void setReserveeName(String reserveeName) {
        this.reserveeName = reserveeName;
    }

    public String getReserveePhone() {
        return reserveePhone;
    }

    public void setReserveePhone(String reserveePhone) {
        this.reserveePhone = reserveePhone;
    }

    public String getReserveeEmail() {
        return reserveeEmail;
    }

    public void setReserveeEmail(String reserveeEmail) {
        this.reserveeEmail = reserveeEmail;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
