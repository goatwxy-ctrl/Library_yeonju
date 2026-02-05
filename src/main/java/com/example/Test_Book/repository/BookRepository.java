package com.example.Test_Book.repository;

import com.example.Test_Book.entity.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    @Query(value = """
            
            select * from book1224
            where
            (:bkey ='name' and name like '%' || :bvalue || '%') or
            (:bkey ='writer' and writer like '%' || :bvalue || '%')
            
            """,nativeQuery = true)
    List<BookEntity> search(String bkey, String bvalue);

    @Transactional
    @Modifying

    @Query(value = "update book1224 set readcnt = readcnt+1 " +
            " where bunho=:number", nativeQuery = true)
    void readcount(long number);
}
