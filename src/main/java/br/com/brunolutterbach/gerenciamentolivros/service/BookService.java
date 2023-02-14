package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.model.Book;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import br.com.brunolutterbach.gerenciamentolivros.repository.UserPreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    final BookRepository bookRepository;
    final UserPreferenceRepository userPreferenceRepository;
    final EmailSenderService emailSenderService;

    public BookResponse getBookDetails(Long id) {
        var book = bookRepository.getReferenceById(id);
        return new BookResponse(book);
    }

    public Page<BookResponse> listAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(BookResponse::new);
    }

    public BookResponse createBook(BookCreationData bookCreationData) {
        var book = new Book(bookCreationData);
        bookRepository.save(book);
        List<String> emails = userPreferenceRepository.findAllEmailsWithGenre(book.getGenre());
        for (String email : emails) {
            String name = userPreferenceRepository.getNameByEmail(email);
            emailSenderService.sendEmail(email, "[" + book.getGenre().toUpperCase() + "]" + " Novo livro cadastrado",
                    "Olá, " + name + "! Um novo livro foi cadastrado com o gênero " + book.getGenre() + "!");
        }
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
