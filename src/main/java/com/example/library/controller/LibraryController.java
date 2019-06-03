package com.example.library.controller;

import com.example.library.bookservice.BookService;
import com.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class LibraryController {


    private BookService bookService;

    @Autowired
    public LibraryController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("book/list")
    public ModelAndView getAll(ModelMap model){
        model.addAttribute("books", bookService.findBooks());
        return new ModelAndView("book/list", model);
    }

    @RequestMapping("book/add")
    public ModelAndView addBook(ModelMap model){
        model.addAttribute("book", new Book());
        return new ModelAndView("book/bookform", model);
    }

    @RequestMapping("book/edit/{id}")
    public ModelAndView updateBook(@PathVariable Long id, ModelMap model) {
        Book book = bookService.getById(Long.valueOf(id));
        model.addAttribute("book", book);
        return new ModelAndView("book/bookform", model);
    }

    @RequestMapping("/book/delete/{id}")
    public ModelAndView deleteBook(@PathVariable Long id, ModelMap model){
        bookService.deleteBook(Long.valueOf(id));
        return new ModelAndView("book/list", model);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateProduct(@Valid Book book, BindingResult bindingResult, ModelMap model){

        if(bindingResult.hasErrors())
            return new ModelAndView("book/bookform", model);

        bookService.saveOrUpdateBook(book);

        return new ModelAndView("redirect:book/list", model);
    }
}
