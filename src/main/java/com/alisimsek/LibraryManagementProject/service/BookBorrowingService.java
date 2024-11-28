package com.alisimsek.LibraryManagementProject.service;

import com.alisimsek.LibraryManagementProject.dto.request.BookBorrowingRequest;
import com.alisimsek.LibraryManagementProject.dto.request.BookBorrowingUpdateRequest;
import com.alisimsek.LibraryManagementProject.entity.Book;
import com.alisimsek.LibraryManagementProject.entity.BookBorrowing;
import com.alisimsek.LibraryManagementProject.mapper.BookBorrowingMapper;
import com.alisimsek.LibraryManagementProject.repository.BookBorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookBorrowingService {

    private final BookBorrowingRepository bookBorrowingRepository;
    private final BookService bookService;
    private final BookBorrowingMapper bookBorrowingMapper;

    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepository.findAll();
    }

    public BookBorrowing getById(Long id) {
        return bookBorrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Book borrowing record with ID " + id + " not found in the system."));
    }

    public BookBorrowing create(BookBorrowingRequest bookBorrowingRequest) {
        if (bookBorrowingRequest.getBookForBorrowingRequest().getStock() < 0) {
            throw new RuntimeException("The book you want to borrow is out of stock.");
        }

        Book book = bookService.getById(bookBorrowingRequest.getBookForBorrowingRequest().getId());
        book.setStock(book.getStock() - 1);

        Book bookUpdated = bookService.update(bookBorrowingRequest.getBookForBorrowingRequest().getId(), book);

        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBorrowerName(bookBorrowingRequest.getBorrowerName());
        bookBorrowing.setBorrowerMail(bookBorrowingRequest.getBorrowerMail());
        bookBorrowing.setBorrowingDate(bookBorrowingRequest.getBorrowingDate());
        bookBorrowing.setBook(bookUpdated);
        return this.bookBorrowingRepository.save(bookBorrowing);
    }

    public BookBorrowing update(Long id, BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        Optional<BookBorrowing> bookBorrowingFromDb = bookBorrowingRepository.findById(id);
        LocalDate returnDateFromDb = bookBorrowingFromDb.get().getReturnDate();
        System.out.println("returnDateFromDb :" + returnDateFromDb);
        System.out.println("bookBorrowingUpdateRequest.getReturnDate : " + bookBorrowingUpdateRequest.getReturnDate());
        
        if (bookBorrowingFromDb.isEmpty()) {
            throw new RuntimeException("The book borrowing record you are trying to update could not be found in the system.");
        }

        if (bookBorrowingUpdateRequest.getReturnDate() != null && returnDateFromDb == null)  {
            System.out.println("ifffffffff");
            Book book = bookBorrowingFromDb.get().getBook();
            book.setStock(book.getStock() + 1);


        }
        
        BookBorrowing bookBorrowing = bookBorrowingFromDb.get();
        bookBorrowingMapper.update(bookBorrowing, bookBorrowingUpdateRequest);
        return bookBorrowingRepository.save(bookBorrowing);
    }

    public void deleteById(Long id) {
        Optional<BookBorrowing> bookBorrowingFromDb = bookBorrowingRepository.findById(id);
        if (bookBorrowingFromDb.isPresent()) {
            BookBorrowing bookBorrowing = bookBorrowingFromDb.get();
            if (bookBorrowing.getReturnDate() == null) {
                Book book = bookBorrowing.getBook();
                book.setStock(book.getStock() + 1);
                Book bookUpdated = bookService.update(bookBorrowing.getBook().getId(), book);
                bookBorrowing.setBook(bookUpdated);
            }
            bookBorrowingRepository.delete(bookBorrowing);
        } else {
            throw new RuntimeException("Book borrowing record with ID " + id + " not found in the system.");
        }
    }
}
