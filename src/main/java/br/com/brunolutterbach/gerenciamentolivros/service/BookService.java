package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.model.Book;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    final BookRepository bookRepository;
    final ApplicationEventPublisher eventPublisher;

    public BookResponse getBookDetails(Long id) {
        var book = bookRepository.getReferenceById(id);
        return new BookResponse(book);
    }

    public Page<BookResponse> listAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookResponse::new);
    }

    public BookResponse createBook(BookCreationData bookCreationData)  {
        var book = new Book(bookCreationData);
        bookRepository.save(book);
        this.eventPublisher.publishEvent(book);
        return new BookResponse(book);
    }

    public BookResponse updateBook(BookUpdateData bookUpdateData, Long id) {
        var book = bookRepository.getReferenceById(id);
        book.update(bookUpdateData);
        bookRepository.save(book);
        return new BookResponse(book);

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
