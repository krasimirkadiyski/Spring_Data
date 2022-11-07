package entities;

import javax.persistence.Entity;

@Entity
public class Car extends PassengerVehicle {
    private static final String type = "CAR";
    public Car(){}
    public Car(String type, int numOfPassengers){
        super(type,numOfPassengers);
    }
}
