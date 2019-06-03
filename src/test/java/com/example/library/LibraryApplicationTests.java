package com.example.library;

import com.example.library.bookservice.BookService;
import com.example.library.bookservice.BookServiceImpl;
import com.example.library.controller.LibraryController;
import com.example.library.model.Book;
import com.example.library.repository.LibraryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {

    @Test
    public void test_getBooks() {

        LibraryRepository repoMock = Mockito.mock(LibraryRepository.class);
        Book expectedBook = new Book("bbb", "sss");
        Mockito.when(repoMock.findAll()).thenReturn(new ArrayList<>(Collections.singletonList(expectedBook)));

        BookService service = new BookServiceImpl(repoMock);
        List<Book> books = service.findBooks();

        Assert.assertEquals(1, books.size() );
        Assert.assertEquals(expectedBook.getAuthorName(), books.get(0).getAuthorName());
    }
}
