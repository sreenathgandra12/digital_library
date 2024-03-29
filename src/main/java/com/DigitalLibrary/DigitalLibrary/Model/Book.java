package com.DigitalLibrary.DigitalLibrary.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="library_book")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Column(nullable = false,length = 200)
    private String bookName;
    @Column(nullable = false)
    private String publicationYear;
    @Column(nullable = false)
    private Double bookPrice;
    @Column(nullable = false,length = 10)
    private  String bookEdition;
    @Column(nullable = false)
    private String bookCategory;
    // relation attribute
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private Author author;

    @JsonBackReference
    @ManyToMany(mappedBy = "issuedBooks")
    private List<User> users =new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime creationTime;
    @UpdateTimestamp
    private LocalDateTime updationTime;



}
