package com.brest.library;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name should not be empty")
    @Size(min=2, max=30, message = "Name should be form 2 to 30 symbol")
    private String name;

    @NotNull(message = "Email should not be empty")
    @Email(message = "Incorrect format email")
    private String email;

    private Long bookid;

    @NotBlank(message = "Data should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;

    public Reader() {
    }

    public Reader(String name, String email, Long bookid, String date) {
        this.name = name;
        this.email = email;
        this.bookid = bookid;
        this.date = date;
    }
}
