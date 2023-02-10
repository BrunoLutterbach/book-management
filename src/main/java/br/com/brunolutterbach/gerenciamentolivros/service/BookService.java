package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.model.Book;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    final BookRepository repository;

    public BookResponse getBookDetails(Long id) {
        var book = repository.getReferenceById(id);
        return new BookResponse(book);
    }

    public Page<BookResponse> listAllBooks(Pageable pageable) {
        return repository.findAll(pageable).map(BookResponse::new);
    }

    public BookResponse createBook(BookCreationData bookCreationData) {
        var book = new Book(bookCreationData);
        repository.save(book);
        return new BookResponse(book);
    }

    public BookResponse updateBook(BookUpdateData bookUpdateData, Long id) {
        var book = repository.getReferenceById(id);
        book.update(bookUpdateData);
        repository.save(book);
        return new BookResponse(book);

    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
