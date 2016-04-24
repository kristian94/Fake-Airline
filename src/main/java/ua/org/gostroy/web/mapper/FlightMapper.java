package ua.org.gostroy.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.entity.Flight;
import ua.org.gostroy.service.FlightService;
import ua.org.gostroy.web.domain.dto.view.FlightDto;
import ua.org.gostroy.web.domain.dto.view.Response1;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sergey on 4/23/2016.
 */
@Service
public class FlightMapper {

    private String dateFormat = "yyyyMMdd";

    @Autowired
    FlightService flightService;

    public FlightDto toDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightID(flight.getFlightID().toString());
        flightDto.setPricePerSeat(flight.getPricePerSeat());
        flightDto.setTraveltime(flight.getTraveltime());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setDestination(flight.getDestination().iataCode);
        flightDto.setOrigin(flight.getOrigin().iataCode);

        SimpleDateFormat parserSDF = new SimpleDateFormat(dateFormat);
        flightDto.setDate(parserSDF.format(flight.getDate()));

        Integer reservatedSeats = flightService.getReservatedSeats(flight);
        flightDto.setNumberOfFreeSeats(flight.getNumberOfSeats() - reservatedSeats);

        return flightDto;
    }

    public Response1 toDto(Page<Flight> flights) {
        Response1 response1 = new Response1();
        for (Flight flight : flights) {
            FlightDto flightDto = this.toDto(flight);
            response1.addFlightDto(flightDto);
        }

        return response1;
    }

    public Response1 toDto(List<Flight> flights) {
        Response1 response1 = new Response1();
        for (Flight flight : flights) {
            FlightDto flightDto = this.toDto(flight);
            response1.addFlightDto(flightDto);
        }

        return response1;
    }

}
