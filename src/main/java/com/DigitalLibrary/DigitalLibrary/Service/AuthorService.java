package com.DigitalLibrary.DigitalLibrary.Service;


import com.DigitalLibrary.DigitalLibrary.Exceptions.BadRequestException;
import com.DigitalLibrary.DigitalLibrary.Exceptions.DataNotFoundException;
import com.DigitalLibrary.DigitalLibrary.Model.Author;
import com.DigitalLibrary.DigitalLibrary.Repository.AuthorRepository;


import com.DigitalLibrary.DigitalLibrary.dto.UpdateAuthorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author addNewAuthor(Author author) throws Exception {
        return authorRepository.save(author);
    }

    public Author fetchAuthorDetailsByName(String authorName) {
        return authorRepository.findByAuthorName(authorName);
    }

    public List<Author> fetchAllAvailableAuthors() {
     return authorRepository.findAll();
    }

    public Author updateAuthorAddress(UpdateAuthorDto updateAuthorDto) {
        try {
            Author author=authorRepository.findById(updateAuthorDto.getAuthorId()).orElseThrow(
                    ()->new DataNotFoundException("Author Not Exist")
            );
            author.setAuthorAddress(updateAuthorDto.getAddress());
            return authorRepository.save(author);
        }
        catch(RuntimeException e)
        {
            log.error("Error is occured while working with updation operations with exception:{}",e.getMessage());
            throw new BadRequestException("Updation operation is failed due to exception:"+e.getMessage());
        }
    }

    public void deleteById(Integer authorId) {
        authorRepository.deleteById(authorId);
    }

    public void uploadAuthorsDataToDatabase(String fileContent) {
        List<String> authorsData=List.of(fileContent.split("\n"));
        List<Author> authors=new ArrayList<>();
        for (int i=1;i<authorsData.size();i++)
        {
            String[] row=authorsData.get(i).split(",");
            authors.add(Author.builder()
                    .authorId(Integer.valueOf(row[0]))
                    .authorName(row[1])
                    .authorAddress(row[2])
                    .build());
        }
       authorRepository.saveAll(authors);

    }
}