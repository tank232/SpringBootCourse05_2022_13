package com.av.controller;

import com.av.db.Book;
import com.av.dto.BookDto;
import com.av.exeptions.NotFoundException;
import com.av.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/book/all", produces = "application/json; charset=UTF-8")
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }


    @GetMapping(value = "/book/{title}", produces = "application/json; charset=UTF-8")
    public BookDto getBook(@PathVariable("title") String title) {
        return bookService.getBook(title);
    }

    @PostMapping(value = "/book/save", produces = "application/json; charset=UTF-8")
    public BookDto saveBook(BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @DeleteMapping(value = "/book/delete", produces = "application/json; charset=UTF-8")
    public void deketeBook(BookDto bookDto) {
        bookService.deleteBook(bookDto);
    }
}
