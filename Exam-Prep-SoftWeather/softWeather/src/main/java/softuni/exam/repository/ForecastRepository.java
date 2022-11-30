package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.util.DOW;

import java.util.Set;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast,Long> {
    @Query("""
SELECT f FROM Forecast as f
WHERE f.city.id = :cityId
AND f.dayOfWeek = :dow
""")
        Forecast findByCityAndDay(long cityId, DOW dow);
        @Query("""
        select f from Forecast as f
        where f.dayOfWeek = 'Sunday' and
        f.city.population < 150000
        order by f.maxTemperature desc, f.id asc
""")
        Set<Forecast> sundayForecast();
}
