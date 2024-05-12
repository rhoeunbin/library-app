package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    // 2. BookService 연결 => 스프링 빈이 아닌 경우
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 1. postMapping 만들기 + @RestController 추가
    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }
}
