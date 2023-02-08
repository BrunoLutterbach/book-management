package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.BookUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import br.com.brunolutterbach.gerenciamentolivros.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookDetails(@PathVariable Long id) {
        var bookResponse = bookService.getBookDetails(id);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping()
    public ResponseEntity<Page<BookResponse>> listAllBooks(Pageable pageable) {
        var bookResponse = bookService.listAllBooks(pageable);
        return ResponseEntity.ok(bookResponse);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreationData bookCreationData, UriComponentsBuilder builder) {
        var bookResponse = bookService.createBook(bookCreationData);
        var uri = builder.path("/api/books/{id}").buildAndExpand(bookResponse.id()).toUri();
        return ResponseEntity.created(uri).body(bookResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookUpdateData bookUpdateData, @PathVariable Long id) {
        var bookResponse = bookService.updateBook(bookUpdateData, id);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
