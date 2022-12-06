package com.example.football.repository;

import com.example.football.models.dto.ExportPlayerDto;
import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("SELECT new com.example.football.models.dto.ExportPlayerDto(p.firstName,p.lastName,p.position,p.team.name,p.team.stadiumName) " +
            "FROM Player as p JOIN p.stat as s WHERE p.birthDate" +
            " BETWEEN :low AND :high" +
            " ORDER BY p.stat.shooting desc, p.stat.passing desc, p.stat.endurance desc, p.lastName")
    Set<ExportPlayerDto> findAllWithBirthdayIn(LocalDate low, LocalDate high);
    Player findByEmail(String email);

}
