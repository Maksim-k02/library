package com.brest.library.controller;
import com.brest.library.Book;
import com.brest.library.BookDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = {"/api"}, produces = "application/json")
public class HomeRestController {

     private BookDao bookDao;

     public HomeRestController(BookDao bookDao) {
          this.bookDao = bookDao;
     }

     @GetMapping
     public Iterable<Book> getAllBooks(){
          return bookDao.findAll();
     }

     @GetMapping("/{id}")
     public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
          Optional<Book> bookMaybe = bookDao.findById(id);
          if (bookMaybe.isPresent()){
               return new ResponseEntity<>(bookMaybe.get(),HttpStatus.OK);
          }
          return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
     }

     @PostMapping(consumes = "application/json")
     public Book postBook(@RequestBody Book book){
          return bookDao.save(book);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(code = HttpStatus.NO_CONTENT)
     public void deleteBookById(@PathVariable("id") Long id){
          try {
               bookDao.deleteById(id);
          } catch (EmptyResultDataAccessException e){}
     }

     @PutMapping("/{id}")
     public Book putBook (@RequestBody Book book){
          return bookDao.save(book);
     }

     @PatchMapping(path = "/{id}", consumes = "application/json")
     public Book patchBook(@PathVariable("id") Long id, @RequestBody Book bookPath){
          Book bookRefresh = bookDao.findById(id).get();
          if (bookPath.getAuthor() !=null){
               bookRefresh.setAuthor(bookPath.getAuthor());
          }
          if (bookPath.getName() !=null){
               bookRefresh.setName(bookPath.getName());
          }
          return bookDao.save(bookRefresh);

     }

}
