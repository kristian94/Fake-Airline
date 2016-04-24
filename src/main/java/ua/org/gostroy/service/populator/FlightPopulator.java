package ua.org.gostroy.service.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.entity.Flight;
import ua.org.gostroy.domain.entity.notpersist.IataAirportsCode;
import ua.org.gostroy.service.FlightService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Sergey on 4/24/2016.
 */
@Service
public class FlightPopulator {

    private Random random = new Random();

    @Autowired
    FlightService flightService;

    List<Map.Entry<String, Integer>> flightNumberAndNumberOfSeats = new ArrayList<Map.Entry<String, Integer>>() {{
        add(new AbstractMap.SimpleImmutableEntry<>("Airbus А321", 185));
        add(new AbstractMap.SimpleImmutableEntry<>("100 Fokker 100", 109));
        add(new AbstractMap.SimpleImmutableEntry<>("142 British Aerospace 146", 109));
        add(new AbstractMap.SimpleImmutableEntry<>("73g Boeing ", 737));
        add(new AbstractMap.SimpleImmutableEntry<>("AN4 Antonov AN-24", 52));
        add(new AbstractMap.SimpleImmutableEntry<>("D28 Dornier 228", 19));
        add(new AbstractMap.SimpleImmutableEntry<>("YK4 Yakovlev YAK-40", 32));
    }};

    private List<Integer> pricePerSeat = new ArrayList<Integer>() {{
        add(65);
        add(165);
        add(25);
        add(135);
        add(300);
        add(32);
        add(79);
        add(69);
        add(111);
        add(16);
        add(125);
        add(200);
        add(75);
        add(74);
        add(123);
    }};

    private List<Integer> traveltime = new ArrayList<Integer>() {{
        add(120);
        add(180);
        add(60);
        add(240);
        add(300);
        add(20);
        add(40);
        add(600);
    }};

    private Integer flightNumberAndNumberOfSeatsSize = flightNumberAndNumberOfSeats.size();
    private Integer pricePerSeatSize = pricePerSeat.size();
    private Integer traveltimeSize = traveltime.size();
    private Integer destinationAndOriginSize = IataAirportsCode.values().length;


    public void populateDb() {
        flightService.deleteAll();

        LocalDate startDate = LocalDate.of(2016, 4, 1);
        LocalDate endDate = LocalDate.of(2016, 6, 30);

        for (int i = 0; i < 250; i++) {
            Flight flight = createFlightObject(startDate, endDate);
            flightService.save(flight);
        }

        createSpecialFlyToEachMonday(startDate, endDate);

    }

    private void createSpecialFlyToEachMonday(LocalDate startDate, LocalDate endDate) {

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                Flight flight = createFlightObject(startDate, endDate);
                flight.setOrigin(IataAirportsCode.CPH);
                flight.setDestination(IataAirportsCode.STN);
                Date dateOldFormat = Date.from(date.atStartOfDay(ZoneId.of("UTC")).toInstant());
                flight.setDate(dateOldFormat);

                flightService.save(flight);
            }
        }
    }

    private Flight createFlightObject(LocalDate startDate, LocalDate endDate) {
        Flight flight = new Flight();

        Integer flightNumberAndNumberOfSeatsRandom = random.nextInt(flightNumberAndNumberOfSeatsSize);
        flight.setFlightNumber(flightNumberAndNumberOfSeats.get(flightNumberAndNumberOfSeatsRandom).getKey());
        flight.setNumberOfSeats(flightNumberAndNumberOfSeats.get(flightNumberAndNumberOfSeatsRandom).getValue());

        Integer pricePerSeatRandom = random.nextInt(pricePerSeatSize);
        flight.setPricePerSeat(pricePerSeat.get(pricePerSeatRandom));

        Integer traveltimeRandom = random.nextInt(traveltimeSize);
        flight.setTraveltime(traveltime.get(traveltimeRandom));

        Integer destinationAndOriginRandom = random.nextInt(destinationAndOriginSize);
        flight.setOrigin(IataAirportsCode.values()[destinationAndOriginRandom]);
        destinationAndOriginRandom = random.nextInt(destinationAndOriginSize);
        flight.setDestination(IataAirportsCode.values()[destinationAndOriginRandom]);

        LocalDate localDate = genrandomDateBetween(startDate, endDate);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
        flight.setDate(date);

        return flight;
    }

    private LocalDate genrandomDateBetween(LocalDate startDate, LocalDate endDate) {
        int minDay = (int) startDate.toEpochDay();
        int maxDay = (int) endDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}
