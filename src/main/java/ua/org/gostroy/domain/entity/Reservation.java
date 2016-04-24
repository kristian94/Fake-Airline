package ua.org.gostroy.domain.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Sergey on 4/21/2016.
 */

@Document
public class Reservation {

    private String reserveeName;
    private String reserveePhone;
    @Email
    private String reserveeEmail;
    private List<Passenger> passengers;

    public Reservation() {
    }

    public Reservation(String reserveeName, String reserveePhone, String reserveeEmail, List<Passenger> passengers) {
        this.reserveeName = reserveeName;
        this.reserveePhone = reserveePhone;
        this.reserveeEmail = reserveeEmail;
        this.passengers = passengers;
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
