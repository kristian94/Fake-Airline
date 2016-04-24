package ua.org.gostroy.web.domain.dto.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sergey on 4/23/2016.
 */
public class FlightDto {

    private String flightID;
    private String date;
    @JsonProperty("numberOfSeats")
    private Integer numberOfFreeSeats;
    @JsonProperty("totalPrice")
    private Integer pricePerSeat;
    private Integer traveltime;
    private String flightNumber;
    private String destination;
    private String origin;


    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumberOfFreeSeats() {
        return numberOfFreeSeats;
    }

    public void setNumberOfFreeSeats(Integer numberOfFreeSeats) {
        this.numberOfFreeSeats = numberOfFreeSeats;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
