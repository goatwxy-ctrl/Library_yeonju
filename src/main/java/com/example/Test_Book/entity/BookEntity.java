package com.example.Test_Book.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book1224")
@SequenceGenerator(

        name="testbook",
        sequenceName = "testbook_seq",
        allocationSize = 1,
        initialValue = 1000
)

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testbook")
    @Column
    long bunho;
    @Column
    String name;
    @Column
    String writer;
    @Column
    String info;
    @Column
    String image_name;
    @Column
    int readcnt;
}
