package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookDetails(@PathVariable Long id) {
        BookResponse bookResponse = bookService.getBookDetails(id);
        return ResponseEntity.ok(bookResponse);
    }

}
