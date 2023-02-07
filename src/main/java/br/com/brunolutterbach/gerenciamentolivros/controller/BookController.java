package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import br.com.brunolutterbach.gerenciamentolivros.service.BookService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    final BookService bookService;
    private final BookRepository bookRepository;

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
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreationData bookCreationData) {
        var bookResponse = bookService.createBook(bookCreationData);
        return ResponseEntity.ok(bookResponse);
    }

}
