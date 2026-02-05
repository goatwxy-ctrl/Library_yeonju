package com.example.Test_Book.service;

import com.example.Test_Book.dto.BookDTO;
import com.example.Test_Book.entity.BookEntity;
import com.example.Test_Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInterfaceImp implements BookInterface{

    @Autowired
    BookRepository bookRepository;

    @Override
    public void insert(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> allout() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity update(long number) {
        return bookRepository.findById(number).orElse(null);
    }

    @Override
    public void reupdate(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Override
    public void delete(long number) {
        bookRepository.deleteById(number);
    }

    @Override
    public BookEntity detail(long number) {
        return bookRepository.findById(number).orElse(null);
    }

    @Override
    public List<BookEntity> searchgo(String skey, String svalue) {
        return bookRepository.search(skey,svalue);
    }

    @Override
    public void count(long number) {
        bookRepository.readcount(number);
    }


}
