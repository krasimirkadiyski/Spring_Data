package softuni.exam.service;

import softuni.exam.repository.entity.Offer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Set;

// TODO: Implement all methods
public interface OfferService {

    boolean areImported();

    String readOffersFileContent() throws IOException;
	
	String importOffers() throws IOException, JAXBException;

    String exportOffers();

}
