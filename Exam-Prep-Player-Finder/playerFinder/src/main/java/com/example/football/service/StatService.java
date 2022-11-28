package com.example.football.service;

import com.example.football.models.entity.Stat;

import javax.xml.bind.JAXBException;
import java.io.IOException;

//ToDo - Implement all methods
public interface StatService {
    boolean areImported();

    String readStatsFileContent() throws IOException;

    String importStats() throws JAXBException;
    Stat getByPassingAndShootingAndEndurance(double passing, double shooting, double endurance);
    Stat getById(long id);

}
