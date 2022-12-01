package exam.service;


import exam.model.entities.Shop;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

//ToDo - Implement all methods
public interface ShopService {

    boolean areImported();

    String readShopsFileContent() throws IOException;

    String importShops() throws JAXBException, FileNotFoundException;

    Boolean isDuplicated(String name);

    Shop getByName(String name);
}
