package com.DigitalLibrary.DigitalLibrary.Controller;

import com.DigitalLibrary.DigitalLibrary.Exceptions.BadRequestException;
import com.DigitalLibrary.DigitalLibrary.Model.Author;
import com.DigitalLibrary.DigitalLibrary.Service.AuthorService;

import com.DigitalLibrary.DigitalLibrary.dto.UpdateAuthorDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/authors",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @PostMapping
    public ResponseEntity<Author> addNewAuthor(@RequestBody @Valid Author author)
    {
        try {
            if (author != null) {
                return new ResponseEntity<>(authorService.addNewAuthor(author), HttpStatus.CREATED);
            }
        }
            catch(Exception e)
            {
                throw new BadRequestException(e.getMessage());
            }

        /*if(author!=null)
        {
            Author author1=authorService.addNewAuthor(author);
            if(author1 != null)
                return new ResponseEntity<>(author1, HttpStatus.CREATED);
        } */
        return  null;
    }
    @GetMapping("/{authorName}")
    public Author fetchAuthorByName(@PathVariable("authorName")String authorName)
    {
        if(!isBlank(authorName))
            return authorService.fetchAuthorDetailsByName(authorName);
        throw new BadRequestException("Please send author name");
    }
    @GetMapping
    public List<Author> fetchAllAvailableAuthors()
    {
        return authorService.fetchAllAvailableAuthors();
    }

    @GetMapping("/usingParam")
    public Author fetchAuthorByNameUsingParam(@RequestParam("authorName")String authorName)
    {
        if (!isBlank(authorName))
            return authorService.fetchAuthorDetailsByName(authorName);
        throw  new BadRequestException("please send author name");
    }
    boolean isBlank(String string)
    {
        return string==null || string.isBlank();
    }
    @PutMapping
    public Author updateAuthorAddress(@RequestBody @Valid UpdateAuthorDto updateAuthorDto) {
        return authorService.updateAuthorAddress(updateAuthorDto);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthorById(@PathVariable("authorId")Integer authorId) {
        authorService.deleteById(authorId);
    }

    @PostMapping("/upload-csv")
    public void uploadAuthorsDataToDatabase(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String fileContent = new String(multipartFile.getBytes());
        authorService.uploadAuthorsDataToDatabase(fileContent);
    }
}
