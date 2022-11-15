package bg.softuni.springdataautomappingobjects.repos;

import bg.softuni.springdataautomappingobjects.entities_exe3.Employee;
import bg.softuni.springdataautomappingobjects.entities_exe3.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findAllByBirthdayBefore(LocalDate date);

}
