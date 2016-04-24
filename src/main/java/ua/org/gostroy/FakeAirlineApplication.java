package ua.org.gostroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.org.gostroy.service.populator.FlightPopulator;

@SpringBootApplication
public class FakeAirlineApplication {

    private static FlightPopulator flightPopulator;

    public static void main(String[] args) {
        SpringApplication.run(FakeAirlineApplication.class, args);

        flightPopulator.populateDb();
    }

    @Autowired
    public void setFlightPopulator(FlightPopulator flightPopulator) {
        FakeAirlineApplication.flightPopulator = flightPopulator;
    }
}
