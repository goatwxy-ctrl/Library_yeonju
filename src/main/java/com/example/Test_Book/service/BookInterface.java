package com.example.Test_Book.service;

import com.example.Test_Book.dto.BookDTO;
import com.example.Test_Book.entity.BookEntity;

import java.util.List;

public interface BookInterface {
    void insert(BookEntity bookEntity);

    List<BookEntity> allout();

    BookEntity update(long number);

    void reupdate(BookEntity bookEntity);

    void delete(long number);

    BookEntity detail(long number);

    List<BookEntity> searchgo(String bkey, String bvalue);

    void count(long number);
}
