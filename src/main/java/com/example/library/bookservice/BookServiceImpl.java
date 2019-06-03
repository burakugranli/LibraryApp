package com.example.library.bookservice;

import com.example.library.model.Book;
import com.example.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    public LibraryRepository libraryRepository;

    @Autowired
    public BookServiceImpl(LibraryRepository repository){
        this.libraryRepository = repository;
    }

    @Override
    public List<Book> findBooks() {
        List<Book> books = new ArrayList<>();
        libraryRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book saveBook(Book book) {
        libraryRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Long id, Book newBook) {
        return libraryRepository.findById(id)
                .map(book -> {
                    book.setAuthorName(newBook.getAuthorName());
                    book.setBookName(newBook.getBookName());
                    return libraryRepository.save(book);
                }).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id.toString()));
    }

    @Override
    public void deleteBook(Long id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public Book getById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveOrUpdateBook(Book book) {
        Book savedBook = saveBook(book);
        System.out.println("Saved Book Id: " + savedBook.getId());
        return savedBook;
    }
}
