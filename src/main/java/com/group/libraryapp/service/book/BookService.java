package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookMemoryRepository;
import com.group.libraryapp.repository.book.BookMySqlRepository;
import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    // 2. repository에 연결
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. controller에 실행할 함수
    public void saveBook(){

        // 3. repository에 saveBook() 함수 만들고 코드 추가
        bookRepository.saveBook();

    }
}
