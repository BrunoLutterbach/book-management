package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.BookUpdateData;
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

    /**
     * Localiza um livro pelo id
     * @param id
     * @return BookResponse
     */
    public BookResponse getBookDetails(Long id) {
        var book = repository.getReferenceById(id);
        return new BookResponse(book);
    }

    /**
      * Localiza todos os livros
     * @param pageable
     * @return Page<BookResponse>
     */
    public Page<BookResponse> listAllBooks(Pageable pageable) {
        return repository.findAll(pageable).map(BookResponse::new);
    }

    /**
     * Cria um novo livro
     * @param bookCreationData
     * @return BookResponse
     */
    public BookResponse createBook(BookCreationData bookCreationData) {
        var book = new Book(bookCreationData);
        repository.save(book);
        return new BookResponse(book);
    }

    /**
     * Atualiza um livro
     * @param bookUpdateData
     * @param id
     * @return BookResponse
     */
    public BookResponse updateBook(BookUpdateData bookUpdateData, Long id) {
        var book = repository.getReferenceById(id);
        book.update(bookUpdateData);
        repository.save(book);
        return new BookResponse(book);

    }

    /**
     * Deleta um livro
     * @param id
     * @return void
     */
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
