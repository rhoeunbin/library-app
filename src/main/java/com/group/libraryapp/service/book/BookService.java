package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    // 2. repository에 연결
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    // 1. controller에 실행할 함수
    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        // 필요한 기능 :  대출 중인지 아닌지 확인
        // 1.  책 정보 가져옴
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출 기록 정보를 확인해 대출 중인지 확인
        // 3. 만약에 확인했는데 대출 중이면 예외 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("이미 대출되어있는 책");
        }

        // 4. 유저 정보 가져오기
        User user = userRepository.findByname(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
        user.loanBook(book.getName());
        // userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        // 1. 유저 정보 찾아서 유저 가져오기
        User user = userRepository.findByname(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        System.out.println("Hello");
        user.returnBook(request.getBookName());
    }
}
