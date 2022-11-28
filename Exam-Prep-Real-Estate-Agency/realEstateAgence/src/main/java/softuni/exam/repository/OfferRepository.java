package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.repository.entity.Offer;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("""
SELECT o FROM Offer as o
JOIN o.apartment as a
WHERE a.apartmentType = 'three_rooms'
ORDER BY a.area desc, o.price asc
""")
    Set<Offer> findAllWithTreeRoomsApartments();

}
