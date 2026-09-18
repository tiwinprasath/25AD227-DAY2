package com.example.library.service;

import com.example.library.entity.Library;
import com.example.library.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Library create(Library library) {
        return repository.save(library);
    }

    // READ ALL
    public List<Library> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Library getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    public Library update(Long id, Library library) {

        Library existingLibrary = repository.findById(id).orElse(null);

        if (existingLibrary != null) {

            existingLibrary.setName(library.getName());
            existingLibrary.setLocation(library.getLocation());

            return repository.save(existingLibrary);
        }

        return null;
    }

    // DELETE
    public String delete(Long id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

            return "Library deleted successfully";
        }

        return "Library not found";
    }
}