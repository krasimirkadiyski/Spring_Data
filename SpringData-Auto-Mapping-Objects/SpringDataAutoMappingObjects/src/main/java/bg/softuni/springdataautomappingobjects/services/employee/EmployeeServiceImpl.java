package bg.softuni.springdataautomappingobjects.services.employee;

import bg.softuni.springdataautomappingobjects.entities_exe3.Employee;
import bg.softuni.springdataautomappingobjects.entities_exe3.EmployeeDTO;
import bg.softuni.springdataautomappingobjects.repos.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeBornBefore(LocalDate date) {
        List<Employee> employees = employeeRepository.findAllByBirthdayBefore(date);
        PropertyMap<Employee,EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setManagerLastName(source.getManager().getLastName());
            }
        };
        mapper.addMappings(propertyMap);
        List<EmployeeDTO> employeeDTOS = employees.stream().map(e -> mapper.map(e, EmployeeDTO.class)).toList();
        return employeeDTOS;

    }


}

