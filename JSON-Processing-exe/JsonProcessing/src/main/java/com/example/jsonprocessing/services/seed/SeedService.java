package com.example.jsonprocessing.services.seed;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUser() throws FileNotFoundException;
    void seedCategory() throws IOException;
    void seedProducts() throws IOException;

    default void seedAll() throws IOException {
        seedUser();
        seedCategory();
        seedProducts();
    }
}
