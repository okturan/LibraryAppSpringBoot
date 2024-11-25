package com.alisimsek.LibraryManagementProject.service;

import com.alisimsek.LibraryManagementProject.dto.request.AuthorRequest;
import com.alisimsek.LibraryManagementProject.dto.response.AuthorResponse;
import com.alisimsek.LibraryManagementProject.entity.Author;
import com.alisimsek.LibraryManagementProject.mapper.AuthorMapper;
import com.alisimsek.LibraryManagementProject.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorResponse> findAll() {
        return authorMapper.asOutput(authorRepository.findAll());
    }

    public AuthorResponse getById(Long id) {
        return authorMapper.asOutput(authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author with ID " + id + " not found in the system.")));
    }

    public AuthorResponse create(AuthorRequest request) {
        Optional<Author> isAuthorExist = authorRepository.findByNameAndBirthDateAndCountry(request.getName(), request.getBirthDate(), request.getCountry());

        if (isAuthorExist.isEmpty()) {
            Author authorSaved = authorRepository.save(authorMapper.asEntity(request));
            return authorMapper.asOutput(authorSaved);
        }
        throw new RuntimeException("An author with these details already exists in the system.");
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        Optional<Author> authorFromDb = authorRepository.findById(id);

        Optional<Author> isAuthorExist = authorRepository.findByNameAndBirthDateAndCountry(request.getName(), request.getBirthDate(), request.getCountry());

        if (authorFromDb.isEmpty()) {
            throw new RuntimeException("The author you are trying to update could not be found in the system.");
        }

        if (isAuthorExist.isPresent()) {
            throw new RuntimeException("An author with these details already exists in the system.");
        }
        Author author = authorFromDb.get();
        authorMapper.update(author, request);
        return authorMapper.asOutput(authorRepository.save(author));
    }

    public void deleteById(Long id) {
        Optional<Author> authorFromDb = authorRepository.findById(id);
        if (authorFromDb.isPresent()) {
            authorRepository.delete(authorFromDb.get());
        } else {
            throw new RuntimeException("Author with ID " + id + " not found in the system.");
        }
    }
}
