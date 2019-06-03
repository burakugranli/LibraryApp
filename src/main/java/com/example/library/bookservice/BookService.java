package com.example.library.bookservice;

import com.example.library.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findBooks();
    Book saveBook(Book book);
    Book updateBook(Long id, Book newBook);
    void deleteBook(Long id);
    Book getById(Long id);
    Book saveOrUpdateBook(Book productForm);
}
