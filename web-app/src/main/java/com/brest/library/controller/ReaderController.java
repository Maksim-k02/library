package com.brest.library.controller;



import com.brest.library.BookDao;
import com.brest.library.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

    @GetMapping("/readers/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        readerDao.deleteById(id);
        return "redirect:/readers";
    }


}
