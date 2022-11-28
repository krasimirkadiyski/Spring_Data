package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.repository.entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Apartment findByIdIs(Long id);

}
