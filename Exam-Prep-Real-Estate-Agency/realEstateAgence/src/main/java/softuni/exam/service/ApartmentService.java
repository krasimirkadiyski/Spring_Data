package softuni.exam.service;

import softuni.exam.repository.entity.Apartment;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface ApartmentService {
    Apartment getById(Long id);
    boolean areImported();

    String readApartmentsFromFile() throws IOException;

    String importApartments() throws IOException, JAXBException;
}
