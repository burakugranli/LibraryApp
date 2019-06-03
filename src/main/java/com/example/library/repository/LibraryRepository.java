package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Book, Long> {

}
