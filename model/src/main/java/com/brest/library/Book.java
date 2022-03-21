package com.brest.library;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;

    @NotNull(message = "Author should not be empty")
    @Size(min=2, max=30, message = "Author should be form 2 to 30 symbol")
    private String author;

    @NotNull(message = "Name should not be empty")
    @Size(min=2, max=30, message = "Name should be form 2 to 30 symbol")
    private String name;

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
