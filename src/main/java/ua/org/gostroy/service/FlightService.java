package ua.org.gostroy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.entity.Flight;
import ua.org.gostroy.domain.entity.Reservation;
import ua.org.gostroy.exception.NoFlights;
import ua.org.gostroy.exception.NoneAvilableTickets;
import ua.org.gostroy.repository.FlightRepository;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey on 4/23/2016.
 */
@Service
public class FlightService {

    private String dateFormat = "yyyyMMdd";

    @Autowired
    FlightRepository flightRepository;


    public List<Flight> findAll() {
        List<Flight> result = flightRepository.findAll();
        return result;
    }

    public Page<Flight> findAll(Pageable pageable) {
        Page<Flight> result = flightRepository.findAll(pageable);
        return result;
    }

    public Flight findOne(BigInteger id) throws NoFlights {
        Flight result = flightRepository.findOne(id);
        if (result == null) {
            throw new NoFlights("Not find Flight with id: " + id);
        }
        return result;
    }

    public Flight save(Flight entity) {
        Flight result = flightRepository.save(entity);
        return result;
    }

    public void delete(BigInteger id) {
        flightRepository.delete(id);
    }

    public void deleteAll() {
        flightRepository.deleteAll();
    }

    public Page<Flight> findAllByOriginAndDate(String from, String date, Integer limit) throws ParseException {
        SimpleDateFormat parserSDF = new SimpleDateFormat(dateFormat);
        Date dateParsed = parserSDF.parse(date);
        Date dateNext = new Date(dateParsed.getTime() + (1000 * 60 * 60 * 24));

        PageRequest page = new PageRequest(0, limit);
        Page<Flight> result = flightRepository.findAllByOriginAndDateBetween(from, dateParsed, dateNext, page);
        return result;
    }

    public Page<Flight> findAllByOriginnAndDestinationAndDate(String from, String to, String date, Integer limit) throws ParseException {
        SimpleDateFormat parserSDF = new SimpleDateFormat(dateFormat);
        Date dateParsed = parserSDF.parse(date);
        Date dateNext = new Date(dateParsed.getTime() + (1000 * 60 * 60 * 24));

        PageRequest page = new PageRequest(0, limit);
        Page<Flight> result = flightRepository.findAllByOriginAndDestinationAndDateBetween(from, to, dateParsed, dateNext, page);
        return result;
    }

    public Flight saveReservation(Reservation reservation, String flightId) throws NoFlights, NoneAvilableTickets {
        BigInteger id = new BigInteger(flightId);
        Flight flight = this.findOne(id);

        Integer reservatedSeats = getReservatedSeats(flight);
        if (reservatedSeats + reservation.getPassengers().size() > flight.getNumberOfSeats()) {
            String answer = String.format("There are no enough tickets to make this reservation: %s available, need %s", flight.getNumberOfSeats() - reservatedSeats, reservation.getPassengers().size());
            throw new NoneAvilableTickets(answer);
        }

        flight.getReservations().add(reservation);
        Flight flightNew = flightRepository.save(flight);
        return flightNew;
    }

    public Integer getReservatedSeats(Flight flight) {
        Integer reservatedSeats = 0;
        if (flight.getReservations() != null) {
            reservatedSeats = flight.getReservations().stream().map(s -> s.getPassengers().size()).reduce((s1, s2) -> s1 + s2).orElse(0);
        }
        return reservatedSeats;
    }
}
