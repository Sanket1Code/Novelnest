package com.sanket.novelnest.repository;

import com.sanket.novelnest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
