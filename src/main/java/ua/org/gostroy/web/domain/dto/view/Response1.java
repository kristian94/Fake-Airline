package ua.org.gostroy.web.domain.dto.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 4/23/2016.
 */
public class Response1 {

    private String airline = "airlineName";
    private List<FlightDto> flights = new ArrayList<>();


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public List<FlightDto> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDto> flights) {
        this.flights = flights;
    }

    public void addFlightDto(FlightDto flightDto) {
        flights.add(flightDto);
    }
}
