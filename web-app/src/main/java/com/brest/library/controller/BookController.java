package com.brest.library.controller;


import com.brest.library.Book;
import com.brest.library.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


/**
 * Root controller.
 */
@Controller
public class BookController {

    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    @GetMapping(value = "/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookDao.findAll());
        model.addAttribute("newbook",new Book());
        return "books";
    }

    @GetMapping("/books/add")
    public String addBooks(Model model){
        model.addAttribute("book",new Book());
        return "add";
    }

    @PostMapping
    public String createBook(@Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "add";
        }else {
            bookDao.save(book);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model){
        if (bookDao.findById(id).isPresent()){
            model.addAttribute("book",bookDao.findById(id).get());
            return "show";
        }
        model.addAttribute("book", new Book());
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        bookDao.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editFormById(@PathVariable("id") Long id, Model model){
        if (bookDao.findById(id).isPresent()){
            model.addAttribute("book",bookDao.findById(id).get());
            return "edit";
        }
        model.addAttribute("book", new Book());
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book){
        System.out.println(book);
        bookDao.save(book);
        return "redirect:/";
    }






}
