package com.DigitalLibrary.DigitalLibrary.Service;

import com.DigitalLibrary.DigitalLibrary.Model.Author;
import com.DigitalLibrary.DigitalLibrary.Model.Book;
import com.DigitalLibrary.DigitalLibrary.Repository.AuthorRepository;
import com.DigitalLibrary.DigitalLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public Book addNewBook(Book book){
        Author author = authorRepository.findById(book.getAuthor().getAuthorId()).get();/* orElse(null); */
       // if(author != null) {
            book.setAuthor(author);
            return bookRepository.save(book);
       /*
        }
        return null;
       */
    }
    public void deleteBookById(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
    public List<Book> findBookByAuthorName(String authorName) {
        return bookRepository.findByAuthorAuthorName(authorName);
    }
}