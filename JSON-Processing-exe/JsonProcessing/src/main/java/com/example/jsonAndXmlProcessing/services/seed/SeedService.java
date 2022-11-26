package com.example.jsonAndXmlProcessing.services.seed;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUser() throws IOException, JAXBException;
    void seedCategory() throws IOException, JAXBException;
    void seedProducts() throws IOException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedUser();
        seedCategory();
        seedProducts();
    }
}
