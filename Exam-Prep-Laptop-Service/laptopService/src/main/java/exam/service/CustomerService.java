package exam.service;

import exam.model.entities.Customer;

import java.io.IOException;

//ToDo - Implement all methods
public interface CustomerService {

    boolean areImported();

    String readCustomersFileContent() throws IOException;

    String importCustomers() throws IOException;

    Boolean isDuplicated(String email);

}
