package com.DigitalLibrary.DigitalLibrary.Repository;

import com.DigitalLibrary.DigitalLibrary.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByAuthorName(String authorName);


    void deleteById(Integer authorId);
}
