package com.av.service;

import com.av.db.Book;
import com.av.dto.BookDto;
import com.av.exeptions.NotFoundException;
import com.av.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }


    public BookDto getBook(String title) {
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if(!bookByTitle.isPresent())
        {
            new NotFoundException(String.format("Не найдена книга {}",title));
        }
        return bookByTitle.map(BookDto::toDto).get();
    }

    @PreAuthorize("hasRole('USER') || #bookDto.title == 'ANY' ")
    public BookDto saveBook(BookDto bookDto) {
        Book book = bookRepository.save(BookDto.toBook(bookDto));
        return BookDto.toDto(book);
    }

    @RolesAllowed({"ADMIN"})
    public void deleteBook(BookDto bookDto) {
        bookRepository.delete(BookDto.toBook(bookDto));
    }

}
