package bg.softuni.automapping.repos;

import bg.softuni.automapping.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    @Transactional
    @Modifying
    @Query("""
 UPDATE Game as g
 SET g.title = :title
 WHERE g.id = :id
""")
    void editGameTitle(Long id, String title);
    @Transactional
    @Modifying
    @Query("""
    UPDATE Game as g
    SET g.price = :price
    WHERE g.id = :id
""")
    void editGamePrice(Long id, BigDecimal price);

    @Modifying
    @Transactional
    @Query("""
UPDATE Game as g
SET g.size = :size
WHERE g.id = :id
""")
    void editGameSize(Long id, double size);

    @Modifying
    @Transactional
    void deleteGameById(long id);

    Game findById(long id);

    Game findByTitle(String title);


    List<Game> findAllByUserId(long id);

}
