package bg.softuni.springdataautomappingobjects;

import bg.softuni.springdataautomappingobjects.entities_exe1.Address_01;
import bg.softuni.springdataautomappingobjects.entities_exe1.Employee_01;
import bg.softuni.springdataautomappingobjects.entities_exe1.dtos.EmployeeDTO_01;
import bg.softuni.springdataautomappingobjects.entities_exe3.Address;
import bg.softuni.springdataautomappingobjects.entities_exe3.Employee;
import bg.softuni.springdataautomappingobjects.entities_exe3.EmployeeDTO;
import bg.softuni.springdataautomappingobjects.services.employee.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
    private final EmployeeService employeeService;
    @Autowired
    public Main(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Address_01 address = new Address_01("Sofia", "St Stefanov");
        Employee_01 employee = new Employee_01("Krasimir", "Petrov",
                BigDecimal.TEN, LocalDate.now(), address);
        EmployeeDTO_01 employeeDTO = new EmployeeDTO_01();


        //мапване с PropertyMap

//        PropertyMap<Employee,EmployeeDTO> employeeMap = new PropertyMap<Employee, EmployeeDTO>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//                map().setStreet(source.getAddress().getStreet());
//            }
//        };
//        mapper.addMappings(employeeMap).map(employee, employeeDTO);

        //мапване с TypeMap

//        TypeMap<Employee_01, EmployeeDTO> typeMap = mapper.createTypeMap(Employee_01.class, EmployeeDTO.class);
//        typeMap.addMappings(m -> m.map(src -> src.getAddress().getCity(), EmployeeDTO::setCity));
//        typeMap.addMappings(m -> m.map(src -> src.getAddress().getStreet(), EmployeeDTO::setStreet));
//        EmployeeDTO empDTO = typeMap.map(employee);


        Address address1 = new Address("Sofia", "st 1234");
        Address address2 = new Address("Sofia", "st 3434");

        Employee employee1 = new Employee("Ivan", "Ivanov", BigDecimal.valueOf(5646)
                , false, address1, LocalDate.parse("1989-05-05"));
        Employee employee2 = new Employee("Pesho", "Ivanov", BigDecimal.valueOf(3246)
                , false, address2, LocalDate.parse("1999-08-05"));

        employee1.setManager(employee2);
        employee2.setManager(employee1);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);


        employeeService.saveAll(employeeList);

        List<EmployeeDTO> allEmployeeBornBefore = employeeService.getAllEmployeeBornBefore(LocalDate.parse("1990-01-01"));

        allEmployeeBornBefore.forEach(System.out::println);
    }
}
