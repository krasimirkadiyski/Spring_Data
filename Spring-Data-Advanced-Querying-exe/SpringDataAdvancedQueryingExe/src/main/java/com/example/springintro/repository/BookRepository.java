package com.example.springintro.repository;

import com.example.springintro.model.dto.BookDto;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesIsBefore(int copies);


    @Query("""
    SELECT b FROM Book as b
    WHERE b.price NOT BETWEEN :lower AND :higher
""")
    List<Book> findBooksByPriceNotBetween(BigDecimal lower, BigDecimal higher);
    @Query("""
      SELECT b FROM Book as b
      WHERE YEAR(b.releaseDate) <> :date
""")
    List<Book> findBooksByReleaseDateIsNot(int date);

    List<Book> findBooksByReleaseDateBefore(LocalDate date);

    @Query("""
SELECT b FROM Book as b
WHERE b.title LIKE %:text%
""")
    List<Book> findAllTitlesContain(String text);
    @Query("""
SELECT b FROM Book as b
JOIN b.author as a
WHERE a.lastName LIKE :text%
""")
    List<Book> findAllBookWrittenByAuthorLastNameStartWith(String text);
    @Query("""
    SELECT count(b) FROM Book as b
    WHERE length( b.title) > :bound
""")
    Optional<Integer>  countBooksWithTitleLongerThan(int bound);

    @Query("""
    SELECT new com.example.springintro.model.dto.BookDto(b.title,b.ageRestriction,b.editionType,b.price) FROM Book as b
    WHERE b.title = :title
""")
   Optional<BookDto> findBooksByTitle(String title);
}
