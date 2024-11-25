package com.alisimsek.LibraryManagementProject.service;

import com.alisimsek.LibraryManagementProject.entity.Book;
import com.alisimsek.LibraryManagementProject.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found in the system."));
    }

    @Transactional
    public Book create(Book book) {
        Optional<Book> isBookExist = bookRepository.findByNameAndAuthor(book.getName(), book.getAuthor());

        if (isBookExist.isEmpty()) {
            return this.bookRepository.save(book);
        }
        throw new RuntimeException("A book with this name and author already exists in the system.");
    }

    public Book update(Long id, Book book) {
        Optional<Book> bookFromDb = bookRepository.findById(id);

        if (bookFromDb.isEmpty()) {
            throw new RuntimeException("The book you are trying to update with ID " + id + " could not be found in the system.");
        }

        book.setId(id);
        return this.bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            bookRepository.delete(bookFromDb.get());
        } else {
            throw new RuntimeException("Book with ID " + id + " not found in the system.");
        }
    }

    public List<Book> findByCategoryId(Long id) {
        return bookRepository.findByCategoryId(id);
    }
}
