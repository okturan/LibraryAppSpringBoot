package com.alisimsek.LibraryManagementProject.service;

import com.alisimsek.LibraryManagementProject.entity.Book;
import com.alisimsek.LibraryManagementProject.entity.Category;
import com.alisimsek.LibraryManagementProject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found in the system."));
    }

    public Category create(Category request) {
        Optional<Category> isCategoryExist = categoryRepository.findByName(request.getName());

        if (isCategoryExist.isEmpty()) {
            return categoryRepository.save(request);
        }
        throw new RuntimeException("A category with this name already exists in the system.");
    }

    public Category update(Long id, Category request) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);
        Optional<Category> isCategoryExist = categoryRepository.findByNameAndIdNot(request.getName(), id);

        if (categoryFromDb.isEmpty()) {
            throw new RuntimeException("The category you are trying to update could not be found in the system.");
        }

        if (isCategoryExist.isPresent()) {
            throw new RuntimeException("A category with this name already exists in the system.");
        }
        request.setId(id);
        return categoryRepository.save(request);
    }

    public String deleteById(Long id) {
        Optional<Category> categoryFromDb = categoryRepository.findById(id);
        if (categoryFromDb.isEmpty()) {
            throw new RuntimeException("Category with ID " + id + " not found in the system.");
        }
        
        List<Book> booksInCategory = bookService.findByCategoryId(id);
        if (!booksInCategory.isEmpty()) {
            throw new RuntimeException("Cannot delete category with ID " + id + ". There are books registered in this category.");
        }
        
        categoryRepository.delete(categoryFromDb.get());
        return "Category deletion successful";
    }
}
