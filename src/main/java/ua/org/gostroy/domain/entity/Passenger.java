package ua.org.gostroy.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sergey on 4/21/2016.
 */
@Document
public class Passenger {

    private String firstName;
    private String lastName;

    public Passenger() {
    }

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
