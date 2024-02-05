package booksManagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import booksManagment.entity.Book;
import booksManagment.service.BookService;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    // View methods
    @GetMapping("/")
    public String showBooksPage(Model model) {
        model.addAttribute("book", service.getAll());
        return "/book/list";
    }

    @GetMapping("/book/add")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "/book/form";
    }

    @RequestMapping("/book/edit/{id}")
    public String editBookPage(@PathVariable(name = "id") Long id, Model model) {

        Book book = service.get(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "/book/form";
        } else {
            return "redirect:/book/add";
        }
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(err -> System.out.println("hejj" + err.getDefaultMessage()));
            return "/book/form";
        }

        if (book.getId() == null) {
            service.addNew(book);
            redirectAttributes.addFlashAttribute("successMsg",
                    "'" + book.getTitle() + "' is added.");
            return "redirect:/book/add";
        } else {
            Book updateBook = service.save(book);
            redirectAttributes.addFlashAttribute("successMsg",
                    "Changes for '" + book.getTitle() + "' are saved successfully. ");
            return "redirect:/book/edit/" + updateBook.getId();
        }
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Book book = service.get(id);
        if (book != null) {
            service.delete(id);
        }
        return "redirect:/";
    }

    // API methods
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = service.getAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = service.get(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book, HttpServletRequest request) {
        Book createdBook = service.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @Valid @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = service.save(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @GetMapping("/api/books/photo/{id}")
    // @ResponseBody
    // public byte[] getPhoto(@PathVariable int id) {
    // Book book = service.getBookById(id);
    // return book.getCoverPhoto();
    // }

}