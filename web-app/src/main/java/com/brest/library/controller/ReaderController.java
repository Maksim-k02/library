package com.brest.library.controller;



import com.brest.library.Book;
import com.brest.library.BookDao;
import com.brest.library.Reader;
import com.brest.library.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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


    @PostMapping(value = "/readers")
    public String createReader(Reader reader){
        readerDao.save(reader);
        return "redirect:/readers";
    }

    @GetMapping("/readers/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        readerDao.deleteById(id);
        return "redirect:/readers";
    }

    @GetMapping(value = "/newReader")
    public String addReader(Model model){
        model.addAttribute("books",bookDao.findAll());
        model.addAttribute("addreader",new Reader());
        return "newReader";
    }


}
