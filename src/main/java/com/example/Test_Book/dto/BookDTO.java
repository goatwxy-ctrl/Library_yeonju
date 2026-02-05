package com.example.Test_Book.dto;

import com.example.Test_Book.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    long bunho;
    String name;
    String writer;
    String info;
    String image_name;
    MultipartFile image;
    int readcnt;

    public BookEntity toentity() {
        BookEntity ee = new BookEntity();
        ee.setBunho(bunho);
        ee.setName(name);
        ee.setWriter(writer);
        ee.setInfo(info);
        ee.setImage_name(image_name);
        ee.setReadcnt(readcnt);
        return ee;
    }

    public BookEntity reentity() {
        BookEntity ee = new BookEntity();
        ee.setBunho(bunho);
        ee.setName(name);
        ee.setWriter(writer);
        ee.setInfo(info);
        ee.setImage_name(image_name);
        ee.setReadcnt(readcnt);
        return ee;
    }
}
