package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.ImportCustomerDto;
import exam.model.entities.Customer;
import exam.model.entities.Town;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidChecker;
import exam.util.ValidCheckerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static exam.util.Paths.IMPORT_CUSTOMERS_PATH;
import static exam.util.outputMessages.INVALID_CUSTOMER;
import static exam.util.outputMessages.SUCCESSFULLY_IMPORTED_CUSTOMER;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final TownService townService;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper mapper, TownService townService) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_CUSTOMERS_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        ValidChecker validator = new ValidCheckerImpl();
        StringBuilder sb = new StringBuilder();
        ImportCustomerDto[] dtos = gson.fromJson(readCustomersFileContent(), ImportCustomerDto[].class);
        for (ImportCustomerDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getEmail())){
                sb.append(INVALID_CUSTOMER)
                        .append(System.lineSeparator());
                continue;
            }
            Customer customer = mapper.map(dto, Customer.class);
            Town town = townService.getByName(dto.getTown().getName());
            customer.setTown(town);
            this.customerRepository.saveAndFlush(customer);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_CUSTOMER,customer.getFirstName(),customer.getLastName(),customer.getEmail()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String email) {
        return customerRepository.findByEmail(email) != null;
    }
}
