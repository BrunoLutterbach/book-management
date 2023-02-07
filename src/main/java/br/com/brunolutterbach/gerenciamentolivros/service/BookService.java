package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    final BookRepository repository;

    public BookResponse getBookDetails(Long id) {
        var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return new BookResponse(book);
    }
}
