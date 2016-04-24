package ua.org.gostroy.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.org.gostroy.domain.entity.notpersist.IataAirportsCode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey on 4/21/2016.
 */
@Document
public class Flight {

    @Id
    private BigInteger flightID;
    private Date date;
    private Integer numberOfSeats;
    private Integer pricePerSeat;
    private Integer traveltime;
    private String flightNumber;
    private IataAirportsCode destination;
    private IataAirportsCode origin;
    private List<Reservation> reservations = new ArrayList<>();

    public Flight() {
    }

    public Flight(Date date, Integer numberOfSeats, Integer pricePerSeat, Integer traveltime, String flightNumber, IataAirportsCode destination, IataAirportsCode origin) {
        this.date = date;
        this.numberOfSeats = numberOfSeats;
        this.pricePerSeat = pricePerSeat;
        this.traveltime = traveltime;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.origin = origin;
    }

    public BigInteger getFlightID() {
        return flightID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(Integer pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public Integer getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(Integer traveltime) {
        this.traveltime = traveltime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public IataAirportsCode getDestination() {
        return destination;
    }

    public void setDestination(IataAirportsCode destination) {
        this.destination = destination;
    }

    public IataAirportsCode getOrigin() {
        return origin;
    }

    public void setOrigin(IataAirportsCode origin) {
        this.origin = origin;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
