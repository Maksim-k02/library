package com.brest.library.controller;


import com.brest.library.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private BookDao bookDao;

    @Autowired
    public HomeController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping("/")
    public String getIndex(Model model){
        model.addAttribute("books", bookDao.findAll());
        return "books";
    }
}
