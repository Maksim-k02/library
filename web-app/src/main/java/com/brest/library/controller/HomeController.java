package com.brest.library.controller;


import com.brest.library.Book;
import com.brest.library.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Struct;

@Controller
@RequestMapping("/")
public class HomeController {

    private BookDao bookDao;

    @Autowired
    public HomeController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping
    public String getBooks(Model model){
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("newbook", new Book());
        return "books";
    }

    @PostMapping
    public String createBook(Book book){
        bookDao.save(book);
        return "redirect:/";

    }
}
