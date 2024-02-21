package com.DigitalLibrary.DigitalLibrary.Controller;

import com.DigitalLibrary.DigitalLibrary.Model.Author;
import com.DigitalLibrary.DigitalLibrary.Model.Book;
import com.DigitalLibrary.DigitalLibrary.Service.AuthorService;
import com.DigitalLibrary.DigitalLibrary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/books",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book book)
    {
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.CREATED);
    }
    @DeleteMapping("/{bookId}")
    public  void deleteExistingBook(@PathVariable("bookId") Integer bookId)
    {
        bookService.deleteBookById(bookId);
    }
    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable("authorName") String authorName)
    {
        return new ResponseEntity<>(bookService.findBookByAuthorName(authorName),HttpStatus.OK);
    }
}
