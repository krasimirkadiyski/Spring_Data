package com.example.jsonAndXmlProcessing.repos;

import com.example.jsonAndXmlProcessing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from users order by RAND() limit 1", nativeQuery = true)
    User getRandomUser();


    Set<User> findAllByOrderByLastNameAscFirstNameAsc();

    List<User> findAll();



}
