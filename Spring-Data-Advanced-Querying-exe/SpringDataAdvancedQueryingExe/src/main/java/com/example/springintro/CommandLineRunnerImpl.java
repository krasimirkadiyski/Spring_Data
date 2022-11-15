package com.example.springintro;

import com.example.springintro.model.dto.BookDto;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

        //1
//        List<Book> allBooksByAgeRestriction = bookService.findAllBooksByAgeRestriction(AgeRestriction.MINOR);
//        allBooksByAgeRestriction.forEach(System.out::println);
        //2
//        List<Book> goldenEditionBooks = bookService.findGoldenEditionBooks();
//        goldenEditionBooks.forEach(System.out::println);
        //3
//        List<Book> allBooksPriceNotInRange = bookService.findAllBooksPriceNotInRange(BigDecimal.valueOf(5.0), BigDecimal.valueOf(40));
//        allBooksPriceNotInRange.forEach(b -> System.out.println(String.format("%s %.2f",b.getTitle(),b.getPrice())));
        //4
//        List<Book> allBooksRealiseDateIsNotLike = bookService.findAllBooksRealiseDateIsNotLike(2000);
//        allBooksRealiseDateIsNotLike.forEach(b -> System.out.println(b.getTitle() + " " + b.getReleaseDate()));
        //5
//        List<Book> allBooksWithRealiseDateBefore = bookService.findAllBooksWithRealiseDateBefore(LocalDate.of(2000, 1, 1));
//        allBooksWithRealiseDateBefore.forEach(b -> System.out.printf("BOOK: %s-%s-%.2f%n",b.getTitle(),b.getEditionType(),b.getPrice()));
        //6
//        List<Author> resultAuthors = authorService.getAuthorsFirstNameEndingWith("e");
//        resultAuthors.forEach(a -> System.out.printf("Author: %s %s%n",a.getFirstName(),a.getLastName()));
        //7
//        List<Book> books = bookService.getAllTittlesContain("sk");
//        books.forEach(b -> System.out.println(b.getTitle()));
        //8
//        List<Book> books = bookService.getAllBookWrittenByAuthorLastNameStartWith("gr");
//        books.forEach(b -> System.out.printf("%s (%s %s)%n",b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()));
        //9
//        Integer count = bookService.getAllBooksWithTitleLongerThan(12);
//        System.out.println(count);
        //10
//        Map<Author, Integer> authorIntegerMap = AuthorAndTotalCopies();
//        for (Map.Entry<Author, Integer> entry : authorIntegerMap.entrySet()) {
//            System.out.printf("%s %s - %d%n", entry.
//                            getKey().getFirstName(),
//                    entry.getKey().getLastName(),entry.getValue());
//        }
        //11
        BookDto book = bookService.findBookByTitle("Things Fall Apart");
        System.out.println(book);

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }

    private Map<Author, Integer> AuthorAndTotalCopies() {
        Map<Author, Integer> rSMap = new HashMap<>();
        List<Author> authors = authorService.findAllById();
        for (Author author : authors) {
            int totalCopies = 0;
            Set<Book> books = author.getBooks();
            for (Book book : books) {
                totalCopies += book.getCopies();
            }
            rSMap.put(author, totalCopies);
        }
        LinkedHashMap<Author, Integer> result = rSMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return result;
    }
}