package com.example.springintro.service;

import com.example.springintro.model.dto.BookDto;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findGoldenEditionBooks();

    List<Book> findAllBooksPriceNotInRange(BigDecimal lower, BigDecimal higher);

    List<Book> findAllBooksRealiseDateIsNotLike(int date);

    List<Book> findAllBooksWithRealiseDateBefore(LocalDate date);

    List<Book> getAllTittlesContain(String text);

    List<Book> getAllBookWrittenByAuthorLastNameStartWith(String text);

    Integer getAllBooksWithTitleLongerThan(int bound);


    BookDto findBookByTitle(String title);
}
