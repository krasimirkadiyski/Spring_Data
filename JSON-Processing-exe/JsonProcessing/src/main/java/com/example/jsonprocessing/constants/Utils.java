package com.example.jsonprocessing.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;


public enum Utils {
    ;
    public static final ModelMapper MAPPER = new ModelMapper();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void writeJsonIntoFile(Collection<?> objects, Path filePath) throws IOException {
       final FileWriter fileWriter = new FileWriter(filePath.toFile());
       GSON.toJson(objects,fileWriter);
       fileWriter.flush();
       fileWriter.close();
    }
}
