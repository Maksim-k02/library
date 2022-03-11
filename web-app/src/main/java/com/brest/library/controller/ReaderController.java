package com.brest.library.controller;



import com.brest.library.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Root controller.
 */
@Controller
public class ReaderController {

    private ReaderDao readerDao;

    @Autowired
    public ReaderController(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @GetMapping(value = "/readers")
    public String getReaders(Model model){
        model.addAttribute("readers",readerDao.findAll());
        return "readers";
    }


}
