package ua.org.gostroy.web.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.org.gostroy.domain.entity.Flight;
import ua.org.gostroy.domain.entity.Reservation;
import ua.org.gostroy.exception.NoFlights;
import ua.org.gostroy.exception.NoneAvilableTickets;
import ua.org.gostroy.service.FlightService;
import ua.org.gostroy.web.domain.FlightExceptionMessage;
import ua.org.gostroy.web.domain.dto.save.ReservationRequest;
import ua.org.gostroy.web.domain.dto.view.ReservationResponse;
import ua.org.gostroy.web.domain.dto.view.Response1;
import ua.org.gostroy.web.mapper.FlightMapper;
import ua.org.gostroy.web.mapper.ReservationMapper;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Sergey on 4/23/2016.
 */
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class FlightResource {

    @Autowired
    FlightService flightService;
    @Autowired
    FlightMapper flightMapper;
    @Autowired
    ReservationMapper reservationMapper;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    @Deprecated
    @ApiOperation(value = "Just for internal needs, for tests")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unknown error", response = FlightExceptionMessage.class)
    })
    private Response1 findAll() {
        List<Flight> flights = flightService.findAll();
        Response1 response1 = flightMapper.toDto(flights);

        return response1;
    }

    @RequestMapping(value = "/flights/raw", method = RequestMethod.GET)
    @Deprecated
    @ApiOperation(value = "Just for internal needs, for tests")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unknown error", response = FlightExceptionMessage.class)
    })
    private List<Flight> findAllRaw() {
        List<Flight> flights = flightService.findAll();

        return flights;
    }

    @RequestMapping(value = "/flights/{from}/{date}/{tickets}", method = RequestMethod.GET)
    @ApiOperation(value = "Request all available flights that matches the provided search criteria's")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unknown error", response = FlightExceptionMessage.class)
    })
    private Response1 findAllFromByDateWithCountLimit(
            @ApiParam(value = "start airport(as an IATA Code)", required = true)
            @PathVariable String from,
            @ApiParam(value = "travek data, in format yyyyMMdd", required = true)
            @PathVariable String date,
            @ApiParam(value = "requested amount of tickets", required = true)
            @PathVariable Integer tickets
    ) throws ParseException {
        Page<Flight> flights = flightService.findAllByOriginAndDate(from, date, tickets);
        Response1 response1 = flightMapper.toDto(flights);

        return response1;
    }

    @RequestMapping(value = "/flights/{from}/{to}/{date}/{tickets}", method = RequestMethod.GET)
    @ApiOperation(value = "Request all available flights that matches the provided search criteria")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Unknown error", response = FlightExceptionMessage.class)
    })
    private Response1 findAllFromToByDateWithCountLimit(
            @ApiParam(value = "start airport(as an IATA Code)", required = true)
            @PathVariable String from,
            @ApiParam(value = "destination airport(as an IATA Code)", required = true)
            @PathVariable String to,
            @ApiParam(value = "travek data, in format yyyyMMdd", required = true)
            @PathVariable String date,
            @ApiParam(value = "requested amount of tickets", required = true)
            @PathVariable Integer tickets
    ) throws ParseException {
        Page<Flight> flights = flightService.findAllByOriginnAndDestinationAndDate(from, to, date, tickets);
        Response1 response1 = flightMapper.toDto(flights);

        return response1;
    }

    @RequestMapping(value = "/reservation/{flightId}", method = RequestMethod.POST)
    @ApiOperation(value = "Make a reservation for flight and persons provided with request")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Illegal input", response = FlightExceptionMessage.class),
            @ApiResponse(code = 404, message = "No Flights; None or not enough avilable tickets", response = FlightExceptionMessage.class),
            @ApiResponse(code = 500, message = "Unknown error", response = FlightExceptionMessage.class),
    })
    private ReservationResponse createReservation(
            @ApiParam(value = "id returned by on the two GET requests", required = true)
            @PathVariable String flightId,
            @RequestBody @Valid ReservationRequest reservationRequest
    ) throws NoFlights, NoneAvilableTickets {
        Reservation reservation = reservationMapper.fromDto(reservationRequest);
        Flight flight = flightService.saveReservation(reservation, flightId);

        ReservationResponse reservationResponse = reservationMapper.toDto(flight, reservationRequest);
        return reservationResponse;
    }
}
