package com.example.jsonprocessing.repos;

import com.example.jsonprocessing.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select * from categories order by RAND() limit 1" ,nativeQuery = true)
    Category getRandomCategory();
@Query("select c from Category as c")
    Set<Category> getAll();
}
