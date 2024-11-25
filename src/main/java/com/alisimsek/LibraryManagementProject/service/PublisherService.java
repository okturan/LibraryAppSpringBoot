package com.alisimsek.LibraryManagementProject.service;

import com.alisimsek.LibraryManagementProject.dto.response.PublisherResponse;
import com.alisimsek.LibraryManagementProject.entity.Publisher;
import com.alisimsek.LibraryManagementProject.mapper.PublisherMapper;
import com.alisimsek.LibraryManagementProject.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherResponse> findAll() {
        return publisherMapper.asOutput(publisherRepository.findAll());
    }

    public PublisherResponse getById(Long id) {
        Publisher p = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher with ID " + id + " not found in the system."));
        return publisherMapper.asOutput(p);
    }

    public Publisher create(Publisher request) {
        Optional<Publisher> isPublisherExist = publisherRepository.findByNameAndEstablishmentYear(request.getName(), request.getEstablishmentYear());

        if (isPublisherExist.isEmpty()) {
            return publisherRepository.save(request);
        }
        throw new RuntimeException("A publisher with these details already exists in the system.");
    }

    public Publisher update(Long id, Publisher request) {
        Optional<Publisher> publisherFromDb = publisherRepository.findById(id);

        Optional<Publisher> isPublisherExist = publisherRepository.findByNameAndEstablishmentYearAndIdNot(request.getName(), request.getEstablishmentYear(), id);

        if (publisherFromDb.isEmpty()) {
            throw new RuntimeException("The publisher you are trying to update could not be found in the system.");
        }

        if (isPublisherExist.isPresent()) {
            throw new RuntimeException("A publisher with these details already exists in the system.");
        }

        request.setId(id);
        return publisherRepository.save(request);
    }

    public void deleteById(Long id) {
        Optional<Publisher> publisherFromDb = publisherRepository.findById(id);
        if (publisherFromDb.isPresent()) {
            publisherRepository.delete(publisherFromDb.get());
        } else {
            throw new RuntimeException("Publisher with ID " + id + " not found in the system.");
        }
    }
}
