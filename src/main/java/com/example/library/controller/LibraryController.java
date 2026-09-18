package com.example.library.controller;

import com.example.library.entity.Library;
import com.example.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<Library> create(@RequestBody Library library) {
        return new ResponseEntity<>(
                libraryService.create(library),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Library>> getAll() {
        return ResponseEntity.ok(libraryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Library library = libraryService.getById(id);

        if (library == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Library not found");
        }

        return ResponseEntity.ok(library);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Library library) {

        Library updatedLibrary = libraryService.update(id, library);

        if (updatedLibrary == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Library not found");
        }

        return ResponseEntity.ok(updatedLibrary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String result = libraryService.delete(id);

        if (result.equals("Library not found")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(result);
        }

        return ResponseEntity.ok(result);
    }
}