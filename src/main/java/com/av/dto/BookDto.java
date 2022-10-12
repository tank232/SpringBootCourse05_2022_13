package com.av.dto;

import com.av.db.Author;
import com.av.db.Book;
import com.av.db.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;
    private List<Author> authors;
    private String title;
    private String isbn;
    private short edition;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),book.getAuthors(),book.getTitle(),book.getIsbn(),book.getEdition());

    }

    public static Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.id);
        book.setAuthors(bookDto.authors);
        book.setTitle(bookDto.title);
        book.setIsbn(bookDto.isbn);
        book.setEdition(bookDto.edition);
        return book;
    }

}
