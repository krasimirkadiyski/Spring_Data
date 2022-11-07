package entities;

import javax.persistence.Entity;

@Entity(name = "bikes")
public class Bike extends Vehicle {
private static final String type = "BIKE";
    public Bike(){
        super(type);
    }

}
