package softuni.exam.service;


import softuni.exam.models.entity.Mechanic;

import java.io.IOException;

// TODO: Implement all methods
public interface MechanicService {

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;
    Boolean isDuplicatedByEmail(String email);

    Boolean isDuplicatedByFirstName(String firstName);
    Mechanic getByFirstName(String firstName);
}
