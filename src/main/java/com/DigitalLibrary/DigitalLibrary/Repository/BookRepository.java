package com.DigitalLibrary.DigitalLibrary.Repository;

import com.DigitalLibrary.DigitalLibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
  //  @Query(value = "SELECT a FROM Book a WHERE a.author.authorName=?1")
    List<Book> findByAuthorAuthorName(String authorName);
}
