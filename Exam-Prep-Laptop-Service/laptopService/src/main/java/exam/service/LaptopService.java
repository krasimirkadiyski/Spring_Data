package exam.service;

import exam.model.entities.Laptop;

import java.io.IOException;

//ToDo - Implement all methods
public interface LaptopService {
    boolean areImported();

    String readLaptopsFileContent() throws IOException;

    String importLaptops() throws IOException;

    String exportBestLaptops();

    Boolean isDuplicated(String macAddress);

}
