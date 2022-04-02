package com.brest.library.controller;



import com.brest.library.Book;
import com.brest.library.BookDao;
import com.brest.library.Reader;
import com.brest.library.ReaderDao;
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
public class ReaderController {

    private ReaderDao readerDao;
    private BookDao bookDao;

    @Autowired
    public ReaderController(ReaderDao readerDao, BookDao bookDao) {
        this.readerDao = readerDao;
        this.bookDao=bookDao;
    }

    @GetMapping(value = "/readers")
    public String getReaders(Model model){
        model.addAttribute("readers",readerDao.findAll());
        model.addAttribute("books",bookDao.findAll());
        return "readers";
    }


    @PostMapping(value = "/readers/newReader")
    public String createReader(Model model, @Valid Reader reader, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("books",bookDao.findAll());
            return "newReader";

        } else {
            readerDao.save(reader);
            return "redirect:/readers";
        }
    }

    @GetMapping("/readers/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        readerDao.deleteById(id);
        return "redirect:/readers";
    }

    @GetMapping(value = "/readers/newReader")
    public String addReader(Model model){
        model.addAttribute("books",bookDao.findAll());
        model.addAttribute("reader",new Reader());
        return "newReader";
    }

    @GetMapping("/readers/{id}/editReader")
    public String editFormById(@PathVariable("id") Long id, Model model){
        if (readerDao.findById(id).isPresent()){
            model.addAttribute("books",bookDao.findAll());
            model.addAttribute("reader",readerDao.findById(id).get());
            return "editReader";
        }
        model.addAttribute("reader", new Reader());
        return "editReader";
    }

    @PostMapping("/readers/{id}")
    public String updateReader(@PathVariable("id") Long id, Reader reader){
        System.out.println(reader);
        readerDao.save(reader);
        return "redirect:/readers";
    }




}
