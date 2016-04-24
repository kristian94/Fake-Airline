package ua.org.gostroy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.domain.entity.Flight;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Sergey on 4/22/2016.
 */
@Repository
public interface FlightRepository extends MongoRepository<Flight, BigInteger> {

    Page<Flight> findAllByOriginAndDateBetween(String from, Date dateStart, Date dateEnd, Pageable pageable);

    Page<Flight> findAllByOriginAndDestinationAndDateBetween(String from, String to, Date dateStart, Date dateEnd, Pageable pageable);
}
