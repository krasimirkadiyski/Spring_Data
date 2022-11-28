package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.repository.entity.Agent;

import java.util.Set;


@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent findByFirstName(String name);

    @Query("""
SELECT a FROM Agent as a
JOIN a.offers as o
where o.apartment.apartmentType = 'three_rooms'
order by o.apartment.area desc, o.price asc 
""")
    Set<Agent> findAllAgentsWithTreeRoomsApartments();

}
