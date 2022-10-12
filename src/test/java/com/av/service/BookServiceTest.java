package com.av.service;

import com.av.db.Book;
import com.av.dto.BookDto;
import com.av.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

    @Autowired
    BookService bookService;
    @MockBean
    BookRepository bookRepository;

    @Test
    @WithMockUser(username="user",roles={"TEST"})
    void saveNOTANYBook() {
        BookDto book =new BookDto();
        book.setTitle("NOTANY");
        when(bookRepository.save(any())).thenReturn(new Book());
        assertThrows(AccessDeniedException.class,()->bookService.saveBook(book));
    }

    @Test
    @WithMockUser(username="user",roles={"USER"})
    void saveBook() {
        BookDto book =new BookDto();
        book.setTitle("book");
        when(bookRepository.save(any())).thenReturn(new Book());
        assertDoesNotThrow(()->bookService.saveBook(book));
    }


    @Test
    @WithMockUser(username="user",roles={"TEST"})
    void saveANYBook() {
        BookDto book =new BookDto();
        book.setTitle("ANY");
        when(bookRepository.save(any())).thenReturn(new Book());
        assertDoesNotThrow(()->bookService.saveBook(book));
    }


    @Test
    @WithMockUser("user")
    void deleteBook() {
        BookDto book =new BookDto();
        assertThrows(AccessDeniedException.class,()->bookService.deleteBook(book));
    }


    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void deleteOkBook() {
        BookDto book =new BookDto();
        assertDoesNotThrow(()->bookService.deleteBook(book));
    }
}