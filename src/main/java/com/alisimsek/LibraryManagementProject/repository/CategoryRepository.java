package com.alisimsek.LibraryManagementProject.repository;

import com.alisimsek.LibraryManagementProject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameIn(List<String> names);

    Optional<Category> findByName(String name);

    Optional<Category> findByNameAndIdNot(String name, Long id);

}
