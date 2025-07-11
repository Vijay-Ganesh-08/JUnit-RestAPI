package org.training.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.model.Book;
import org.training.repository.BookRepository;
import java.util.List;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = "/getAllBooks")
    public List<Book> getAllBooks() {
        log.info("getAllBooks Executing");
        return bookRepository.findAll();
    }

    @GetMapping(value = "/getBookById/{bookId}")
    public Optional<Book> getBookById(@PathVariable(value = "bookId") Long bookId) {
        log.info("getBookById Executing");
        return bookRepository.findById(bookId);
    }

    @PostMapping(value = "/createBook")
    public Book createBook(@RequestBody Book book) {
        log.info("createBook Executing");
        return bookRepository.save(book);
    }

    @PutMapping(value = "/updateBook")
    public Book updateBook(@RequestBody Book book) throws Exception {
        log.info("updateBook Executing");
        if (book == null) {
            throw new Exception("Book Record must not be null");
        }
        Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
        if(!optionalBook.isPresent()){
            throw new Exception("Book with ID: " + book.getBookId() + " does not exists");
        }
        Book existingBook = optionalBook.get();
        existingBook.setName(book.getName());
        existingBook.setSummary(book.getSummary());
        existingBook.setRating(book.getRating());

        return bookRepository.save(existingBook);

    }

    @DeleteMapping(value = "/deleteBook")
    public String deleteBook(@RequestBody Book book) throws Exception {
        log.info("deleteBook Executing");
        if (book == null) {
            throw new Exception("Book Record must not be null");
        }
        Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
        if(!optionalBook.isPresent()){
            throw new Exception("Book with ID: " + book.getBookId() + " does not exists");
        }
        Book existingBook = optionalBook.get();
        bookRepository.delete(existingBook);
        return "Successfully Deleted Book : " + book.getBookId();
    }

}
