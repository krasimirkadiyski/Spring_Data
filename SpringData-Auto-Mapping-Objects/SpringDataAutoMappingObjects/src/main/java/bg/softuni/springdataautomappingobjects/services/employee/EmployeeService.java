package bg.softuni.springdataautomappingobjects.services.employee;

import bg.softuni.springdataautomappingobjects.entities_exe3.Employee;
import bg.softuni.springdataautomappingobjects.entities_exe3.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {
     void saveAll(List<Employee> employees);
     List<EmployeeDTO> getAllEmployeeBornBefore(LocalDate date);

}
