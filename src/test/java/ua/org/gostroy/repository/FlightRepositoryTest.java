package ua.org.gostroy.repository;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.org.gostroy.FakeAirlineApplication;
import ua.org.gostroy.domain.entity.Flight;
import ua.org.gostroy.domain.entity.Passenger;
import ua.org.gostroy.domain.entity.Reservation;
import ua.org.gostroy.domain.entity.notpersist.IataAirportsCode;

import java.util.Date;

/**
 * Created by Sergey on 4/22/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FakeAirlineApplication.class)
@WebAppConfiguration
@IntegrationTest
public class FlightRepositoryTest {

    @Autowired
    FlightRepository flightRepository;

    @Before
    public void setUp() {
        flightRepository.deleteAll();
    }

    @Test
    public void testSave() {

        Passenger passenger1 = new Passenger("1", "1");
        Passenger passenger2 = new Passenger("2", "2");

        Reservation reservation1 = new Reservation("1", "1", "1", Lists.newArrayList(passenger1, passenger2));
        Reservation reservation2 = new Reservation("2", "2", "2", Lists.newArrayList(passenger1, passenger2));

        Flight flight = new Flight(new Date(), 10, 1, 1, "1", IataAirportsCode.CPH, IataAirportsCode.AXM);
        flight.setReservations(Lists.newArrayList(reservation1, reservation2));

        Flight flightSaved = flightRepository.save(flight);

        Flight flightFromDb = flightRepository.findOne(flightSaved.getFlightID());

        Assert.assertEquals((long) flightFromDb.getNumberOfSeats(), 10L);
        Assert.assertEquals((long) flightFromDb.getReservations().size(), 2L);
        Assert.assertEquals(flightFromDb.getOrigin(), IataAirportsCode.AXM);
    }

}
